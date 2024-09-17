package fr.eni.gestionavis;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.bo.Formateur;
import fr.eni.gestionavis.bo.Stagiaire;
import fr.eni.gestionavis.dal.AvisRepository;
import fr.eni.gestionavis.dal.FormateurRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestAssociationAvisFormateur {

    @Autowired
    AvisRepository avisRepository;

    @Autowired
    FormateurRepository formateurRepository;

    final String emailFormateur = "abaille@campus-eni.fr";

    @Test
    void test01_save_avis_formateur() {
        final Formateur formateur = Formateur
                .builder()
                .email(emailFormateur)
                .build();

        final Avis avis = Avis
                .builder()
                .notePedagogie(4)
                .commentairePedagogie("commentaire sur la pédagogie")
                .noteCours(3)
                .commentaireCours("commentaire sur le cours")
                .stagiaire(Stagiaire
                        .builder()
                        .immatriculation("ENI_CAMPUS_202312456")
                        .promotion("EDMW0002")
                        .build())
                .build();

        //Association avec Formateur

        avis.setFormateur(formateur);

        //Sauver
        final Avis avisDB = avisRepository.save(avis);
        // Vérifier que l'identifiant n'est pas nul
        assertThat(avisDB.getId()).isNotNull();
        assertThat(avisDB.getId()).isNotBlank();

        // Vérifier que l'identifiant du Formateur n'est pas nul
        assertThat(avisDB.getFormateur().getEmail()).isNotNull();
        final String emailDB = avisDB.getFormateur().getEmail();
        assertThat(emailDB).isEqualTo(formateur.getEmail());
        System.out.println(avisDB.toString());
    }
}