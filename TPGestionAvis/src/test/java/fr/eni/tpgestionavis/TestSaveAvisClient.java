package fr.eni.tpgestionavis;

import fr.eni.tpgestionavis.bo.Avis;
import fr.eni.tpgestionavis.bo.Client;
import fr.eni.tpgestionavis.dal.AvisRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSaveAvisClient {

    @Autowired
    AvisRepository avisRepository;

    @Test
    @Order(1)
    public void testSaveAvisClient() {

        final int quantiteCommandde = 1527;

        Client client  = Client.builder()
                .pseudo("Bob")
                .quantiteCommandee(quantiteCommandde)
                .build();

        Avis avis = Avis.builder()
                .note(1)
                .date(LocalDateTime.now())
                .commentaire("Meh")
                .client(client)
                .build();

        Avis savedAvis = avisRepository.save(avis);

        assertThat(savedAvis.getId()).isNotNull();
        assertThat(savedAvis.getClient()).isNotNull();
        assertThat(savedAvis.getClient().getQuantiteCommandee()).isEqualTo(quantiteCommandde);

        System.out.println(savedAvis);
    }

}
