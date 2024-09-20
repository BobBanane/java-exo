package fr.eni.tpgestionavis;

import fr.eni.tpgestionavis.bo.Avis;
import fr.eni.tpgestionavis.bo.Bouteille;
import fr.eni.tpgestionavis.bo.BouteilleId;
import fr.eni.tpgestionavis.bo.Client;
import fr.eni.tpgestionavis.dal.AvisRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAssociationAvisBouteilleClient {

    @Autowired
    AvisRepository avisRepository;

    @Test
    public void test_save_avis() {

        final int quantiteCommandde = 1527;
        final String nomBouteille = "Bob";
        final String commentary = "Meh";

        Client client  = Client.builder()
                .pseudo("Bob")
                .quantiteCommandee(quantiteCommandde)
                .build();

        BouteilleId bouteilleId = BouteilleId.builder()
                .idBouteille(1)
                .idCouleur(1)
                .idRegion(1)
                .build();

        Bouteille bouteille = Bouteille.builder()
                .id(1)
//                .id(bouteilleId)
                .nom(nomBouteille)
                .build();

        Avis avis = Avis.builder()
                .note(1)
                .date(LocalDateTime.now())
                .commentaire(commentary)
                .client(client)
                .bouteille(bouteille)
                .build();

        Avis savedAvis = avisRepository.save(avis);

        assertThat(savedAvis.getId()).isNotNull();
        assertThat(savedAvis.getCommentaire()).isEqualTo(commentary);

        assertThat(savedAvis.getClient().getPseudo()).isNotNull();
        assertThat(savedAvis.getClient().getQuantiteCommandee()).isEqualTo(quantiteCommandde);
        assertThat(savedAvis.getClient()).isEqualTo(client);

        assertThat(savedAvis.getBouteille().getId()).isNotNull();
        assertThat(savedAvis.getBouteille().getNom()).isEqualTo(nomBouteille);
        assertThat(savedAvis.getBouteille()).isEqualTo(bouteille);

        System.out.println(savedAvis);

    }

}
