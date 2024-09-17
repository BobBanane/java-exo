package fr.eni.tpgestionavis;

import fr.eni.tpgestionavis.bo.Bouteille;
import fr.eni.tpgestionavis.bo.BouteilleId;
import fr.eni.tpgestionavis.dal.BouteilleRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class TpGestionAvisApplicationTests {

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Test
    void testSaveBottle() {

        BouteilleId bouteilleId = BouteilleId.builder()
                .idBouteille(1)
                .idCouleur(1)
                .idRegion(1)
                .build();

        Bouteille bouteille = Bouteille.builder()
                .id(bouteilleId)
                .nom("Bob")
                .build();

        Bouteille savedBouteille = bouteilleRepository.save(bouteille);

        assertThat(savedBouteille.getId()).isNotNull();
        assertThat(savedBouteille.getId()).isEqualTo(bouteilleId);
        assertThat(savedBouteille.getNom()).isEqualTo("Bob");

        System.out.println(savedBouteille);

    }

}
