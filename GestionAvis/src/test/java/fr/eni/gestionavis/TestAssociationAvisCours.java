package fr.eni.gestionavis;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.bo.Cours;
import fr.eni.gestionavis.bo.Formateur;
import fr.eni.gestionavis.bo.Stagiaire;
import fr.eni.gestionavis.dal.AvisRepository;
import fr.eni.gestionavis.dal.CoursRepository;
import fr.eni.gestionavis.dal.FormateurRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestAssociationAvisCours {

    @Autowired
    AvisRepository avisRepository;

    @Autowired
    CoursRepository coursRepository;

    @Test
    void test01_save_avis_formateur() {

        // Récupération des Cours en base
        final List<Cours> listeCoursDB = coursRepository.findAll();
        assertThat(listeCoursDB).isNotNull();
        assertThat(listeCoursDB).isNotEmpty();

        final Cours coursDB = listeCoursDB.get(0);

        final Avis avis = Avis
                .builder()
                .notePedagogie(5)
                .commentairePedagogie("commentaire sur la pédagogie")
                .noteCours(4)
                .commentaireCours("commentaire sur le cours")
                .stagiaire(Stagiaire
                        .builder()
                        .immatriculation("ENI_CAMPUS_202311356")
                        .promotion("EDMW0001")
                        .build())
                .build();

        // Association avec Cours
        avis.setCours(coursDB);

        // Sauver
        final Avis avisDB = avisRepository.save(avis);

        // Vérifier que l'identifiant n'est pas nul
        assertThat(avisDB.getId()).isNotNull();
        assertThat(avisDB.getId()).isNotBlank();

        // Vérifier que le Cours est complet
        assertThat(avisDB.getCours().getId()).isNotNull();
        assertThat(avisDB.getCours()).isEqualTo(coursDB);

        System.out.println(avisDB.toString());
    }

}