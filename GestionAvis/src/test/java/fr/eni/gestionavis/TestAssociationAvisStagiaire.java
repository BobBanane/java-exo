package fr.eni.gestionavis;
import static org.assertj.core.api.Assertions.assertThat;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.bo.Stagiaire;
import fr.eni.gestionavis.dal.AvisRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestAssociationAvisStagiaire {



    @Autowired
    AvisRepository avisRepository;



    @Test

    void test01_save_avis_stagiaire() {

        final Stagiaire stagiaire = Stagiaire
                .builder()
                .immatriculation("ENI_CAMPUS_202311987")
                .promotion("EDMW0001")
                .build();

        final Avis avis = Avis
                .builder()
                .notePedagogie(4)
                .noteCours(2)
                .commentaireCours("commentaire sur la pédagogie")
                .commentairePedagogie("commentaire sur la pédagogie")
                .build();

//Association
        avis.setStagiaire(stagiaire);
        final Avis avisDB = avisRepository.save(avis);

// Vérifier que l'identifiant n'est pas nul
        assertThat(avisDB.getId()).isNotNull();
        assertThat(avisDB.getId()).isNotBlank();
// Vérifier que la référence embarquée existe
        final Stagiaire stagiaireDB = avisDB.getStagiaire();
        assertThat(stagiaireDB).isNotNull();
        assertThat(stagiaireDB).isEqualTo(stagiaire);

        System.out.println(avisDB);

    }

    @Test
    public void test_recuperer_les_donnees() {

        Optional<Avis> avisOpt = avisRepository.findById("66e92cde32e03c4977c640d1");

        assertThat(avisOpt).isPresent();
        Avis avis = avisOpt.get();

        System.out.println(avis);

    }

}
