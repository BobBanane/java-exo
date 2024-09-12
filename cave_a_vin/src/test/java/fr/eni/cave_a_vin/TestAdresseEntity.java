package fr.eni.cave_a_vin;


import fr.eni.cave_a_vin.bo.Adresse;
import fr.eni.cave_a_vin.bo.Client;
import fr.eni.cave_a_vin.dal.AdresseRepository;
import fr.eni.cave_a_vin.dal.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("Test")
public class TestAdresseEntity {

    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Order(1)
    @Commit
    public void testAdresseSave() {

        Adresse adresse = Adresse.builder()
                .rue("Chez Bob")
                .codePostal("12345")
                .ville("BobLand")
                .build();

        Client client = Client.builder()
                .pseudo("Bob")
                .password("password")
                .prenom("Bob")
                .nom("Banane")
                .adresse(adresse)
                .build();

        // Ajoute le client et son adresse à la BDD
        Client clientBDD = clientRepository.save(client);

        Assertions.assertThat(clientBDD).isNotNull();
        Optional<Adresse> adresseOpt = adresseRepository.findById(clientBDD.getAdresse().getId());
        Assertions.assertThat(adresseOpt).isPresent();

        System.out.println(clientBDD);
        System.out.println(adresseOpt.get());

    }

    @Test
    @Order(2)
    public void testClientAdresseDetacher() {
        String idClient = "Bob";

        // Récupère et vérifie si le client est bien en BDD
        Optional<Client> clientOpt = clientRepository.findById(idClient);
        Assertions.assertThat(clientOpt).isPresent();

        // Récupère l'adresse pour pouvoir vérifier si elle este toujours présente en BDD
        // après la suppression du client
        Adresse adresse = clientOpt.get().getAdresse();

        // On détache l'adresse du client
        clientOpt.get().setAdresse(null);
        clientRepository.saveAndFlush(clientOpt.get());

        // Vérifie que l'adresse a bien été supprimée
        Optional<Adresse> adresseOpt = adresseRepository.findById(adresse.getId());
        Assertions.assertThat(adresseOpt).isNotPresent();
    }

    @Test
    @Order(3)
    public void testAdresseSuppression() {

        // recréé une adresse
        Adresse adresse = Adresse.builder()
                .rue("Chez Bob")
                .codePostal("12345")
                .ville("BobLand")
                .build();

        // l'ajoute à la base
        Adresse adresseBDD = adresseRepository.save(adresse);
        Assertions.assertThat(adresseBDD).isNotNull();

        // Test si l'adresse à bien été supprimée
        adresseRepository.delete(adresseBDD);
        Optional<Adresse> adresseOpt = adresseRepository.findById(adresse.getId());
        Assertions.assertThat(adresseOpt).isNotPresent();

    }


}
