package fr.eni.cave_a_vin;

import fr.eni.cave_a_vin.bo.Client;
import fr.eni.cave_a_vin.bo.Proprietaire;
import fr.eni.cave_a_vin.bo.Utilisateur;
import fr.eni.cave_a_vin.dal.ClientRepository;
import fr.eni.cave_a_vin.dal.ProprioRepository;
import fr.eni.cave_a_vin.dal.UtilisateurRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestHeritage {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	ProprioRepository proprioRepository;

	@Autowired
	ClientRepository clientRepository;
    @Autowired
    private TestEntityManager testEntityManager;

	@BeforeEach
	public void initDB() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		utilisateurs.add(Utilisateur.builder()
				.pseudo("harrisonford@email.fr")
				.password("IndianaJones3")
				.nom("Ford")
				.prenom("Harrison")
				.build());

		utilisateurs.add(Proprietaire.builder()
				.pseudo("georgelucas@email.fr")
				.password("Réalisateur&Producteur")
				.nom("Lucas")
				.prenom("George")
				.siret("12345678901234")
				.build());

		utilisateurs.add(Client.builder()
				.pseudo("natalieportman@email.fr")
				.password("MarsAttacks!")
				.nom("Portman")
				.prenom("Natalie")
				.build());

		// Contexte de la DB
		utilisateurs.forEach(e -> {
			entityManager.persist(e);
		});
	}

	@Test
	public void findAll_Utilisteur() {
		List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
		Assertions.assertThat(utilisateurs).hasSize(3);

		utilisateurs.forEach(System.out::println);
	}

	@Test
	public void findAll_Proprietaire() {
		List<Proprietaire> proprietaires = proprioRepository.findAll();
		Assertions.assertThat(proprietaires).hasSize(1);

		proprietaires.forEach(System.out::println);
	}

	@Test
	public void findAll_Client() {
		List<Client> clients = clientRepository.findAll();
		Assertions.assertThat(clients).hasSize(1);

		clients.forEach(System.out::println);
	}


	//TODO
}
