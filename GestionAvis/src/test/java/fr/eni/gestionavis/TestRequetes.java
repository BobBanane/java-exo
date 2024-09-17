package fr.eni.gestionavis;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;

import fr.eni.gestionavis.bo.*;
import fr.eni.gestionavis.dal.AvisRepository;
import fr.eni.gestionavis.dal.CoursRepository;
import fr.eni.gestionavis.dal.FormateurRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestRequetes {

    @Autowired
    CoursRepository coursRepository;

    @Autowired
    FormateurRepository formateurRepository;

    @Autowired
    AvisRepository avisRepository;

    static void  insertion_Formateur_Cours_DB(
            AvisRepository avisRepository,
            FormateurRepository formateurRepository,
            CoursRepository coursRepository
    ) {
// Création de Formateur
        final List<Formateur> listeFormateurs = new ArrayList<>();
        listeFormateurs.add(Formateur
                .builder()
                .email("pmontembault@campus-eni.fr")
                .nom("MONTEMBAULT")
                .prenom("Philippe")
                .build());

        listeFormateurs.add(Formateur
                .builder()
                .email("fdelachesnais@campus-eni.frr")
                .nom("DELACHESNAIS")
                .prenom("Frédéric")
                .build());

        // Enregistrement en base
        listeFormateurs.forEach(formateur -> formateurRepository.save(formateur));
        // Création de Cours

        final List<Cours> listeCours = new ArrayList<>();
        listeCours.add(Cours
                .builder()
                .id(CoursId
                        .builder()
                        .reference("M030")
                        .filiere("Développement")
                        .build())
                .titre("Web Client")
                .duree(5)
                .build());

        listeCours.add(Cours
                .builder()
                .id(CoursId
                        .builder()
                        .reference("M070")
                        .filiere("Développement")
                        .build())
                .titre("POO")
                .duree(10)
                .build());

        // Enregistrement en base
        listeCours.forEach(cours -> coursRepository.save(cours));
    }

    static void insertion_Avis_DB(
            AvisRepository avisRepository,
            FormateurRepository formateurRepository,
            CoursRepository coursRepository
    ) {

// Récupération depuis la base des Formateur et des Cours
        // on a dit pas de test dans les insert !!!
        final List<Formateur> listeFormateurs = formateurRepository.findAll();
//        assertThat(listeFormateurs).isNotNull();
//        assertThat(listeFormateurs).isNotEmpty();
//        assertThat(listeFormateurs.size()).isEqualTo(2);
//
//
        final List<Cours> listeCours = coursRepository.findAll();
//        assertThat(listeCours).isNotNull();
//        assertThat(listeCours).isNotEmpty();
//        assertThat(listeCours.size()).isEqualTo(2);

// Ajout d'Avis pour chaque Formateur avec chaque Cours
        for (int i = 0; i < listeFormateurs.size(); i++) {
    // Faire varier la note
            int note = 2;
            final Formateur f = listeFormateurs.get(i);

            for (int j = 0; j < listeCours.size(); j++) {
                final Cours c = listeCours.get(i);
                final Avis avis = Avis
                        .builder()
                        .notePedagogie(note)
                        .commentairePedagogie("Commentaire sur la pédagogie (" + note + ")")
                        .noteCours(note)
                        .commentaireCours("Commentaire du cours (" + note + ")")
                        .cours(c)
                        .formateur(f)
                        .stagiaire(Stagiaire
                                .builder()
                                .immatriculation("ENI_1253" + j)
                                .promotion("CDA1234" + j)
                                .build())
                        .build();

                // Sauvegarde de Avis
                avisRepository.save(avis);

                // incrémenter la note
                note++;
            }
        }
    }


@BeforeAll
//    @Test Hérésie -> @Test c'est pour les test
    static void test00_insertion_DB(
            @Autowired AvisRepository avisRepository,
            @Autowired FormateurRepository formateurRepository,
            @Autowired CoursRepository coursRepository
) {
        // Purge de la base
        formateurRepository.deleteAll();
        avisRepository.deleteAll();
        coursRepository.deleteAll();

        insertion_Formateur_Cours_DB(avisRepository, formateurRepository, coursRepository);
        insertion_Avis_DB(avisRepository, formateurRepository, coursRepository);
    }

    @Test
    void test01_findByNoteCours_3() {

        List<Avis> listeAvis = avisRepository.findByNoteCours(3);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isGreaterThan(1);

        System.out.println("Le nombre d'Avis avec une note = 3 est de : " + listeAvis.size());
    }

    @Test
    void test02_findByNoteCoursGreaterThan_3() {

        List<Avis> listeAvis = avisRepository.findByNoteCoursGreaterThan(2);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isGreaterThan(1);

        System.out.println("Le nombre d'Avis avec une note > 3 est de : " + listeAvis.size());

    }

    @Test
    void test03_findByNoteCoursLessThan_3() {

        List<Avis> listeAvis = avisRepository.findByNoteCoursLessThan(3);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isGreaterThan(1);

        System.out.println("Le nombre d'Avis avec une note < 3 est de : " + listeAvis.size());
    }

    @Test
    void test04_findByStagiaire() {

        final Stagiaire stagiaire = Stagiaire
                .builder()
                .immatriculation("ENI_12531")
                .promotion("CDA12341")
                .build();



        List<Avis> listeAvis = avisRepository.findByStagiaire(stagiaire);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isGreaterThan(1);

        System.out.println("Nb Avis du Stagiaire (" + stagiaire.toString() + ") : " + listeAvis.size());
        System.out.println(listeAvis);

    }

    @Test
    void test05_findByFormateur() {
        final Formateur philippe = Formateur
                .builder()
                .email("pmontembault@campus-eni.fr")
                .nom("MONTEMBAULT")
                .prenom("Philippe")
                .build();

        List<Avis> listeAvis = avisRepository.findByFormateur(philippe);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isGreaterThan(1);

        System.out.println("Nb Avis sur le Formateur (" + philippe.toString() + ") : " + listeAvis.size());
        System.out.println(listeAvis);

    }

    @Test
    void test06_findByCours() {
        final Cours cours = Cours
                .builder()
                .id(CoursId
                        .builder()
                        .reference("M070")
                        .filiere("Développement")
                        .build())
                .titre("POO")
                .duree(10)
                .build();

        List<Avis> listeAvis = avisRepository.findByCours(cours);
        assertThat(listeAvis).isNotNull();
        assertThat(listeAvis).isNotEmpty();
        assertThat(listeAvis.size()).isGreaterThan(1);

        System.out.println("Nb Avis sur le Cours (" + cours.toString() + ") : " + listeAvis.size());
        System.out.println(listeAvis);

    }

}