package fr.eni.cave_a_vin;

import fr.eni.cave_a_vin.bo.Client;
import fr.eni.cave_a_vin.dal.ClientRepository;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestClientEntity {
    static private StringBuilder output = new StringBuilder("");

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @Commit
    @Order(1)
    public void testSaveClient() {
        output.append(1);

        Client client = Client.builder()
                .pseudo("Bob")
                .password("password")
                .prenom("Bob")
                .nom("Banane")
                .build();

        Client clientDB = clientRepository.save(client);

        Assertions.assertThat(clientDB).isNotNull();
        Assertions.assertThat(clientDB.getPseudo()).isEqualTo("Bob");
        System.out.println(clientDB);
    }

    @Test
    @Order(2)
    public void testEqualsClient() {
        output.append(2);
        final String pseudo = "Bob";

        Client client = Client.builder()
                .pseudo("Bob")
                .password("password")
                .prenom("Bob")
                .nom("Banane")
                .build();

        Optional<Client> clientOpt = clientRepository.findById(pseudo);

        Assertions.assertThat(clientOpt.isPresent()).isTrue();
        clientOpt.ifPresent(p -> {
            System.out.println(p);
            System.out.println(client);
            Assertions.assertThat((p.equals(client))).isTrue();
        });
    }

    @Test
    @Order(3)
    public void testSupprimerClient() {

        output.append(3);

        final String pseudo = "Bob";

        Optional<Client> clientOpt = clientRepository.findById(pseudo);

        Assertions.assertThat(clientOpt.isPresent()).isTrue();
        clientRepository.deleteById(pseudo);

        clientOpt = clientRepository.findById(pseudo);

        Assertions.assertThat(clientOpt.isPresent()).isFalse();
    }

    @AfterAll
    public static void testOrder() {
        System.out.println(output);
    }
}
