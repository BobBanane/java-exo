package fr.eni.cave_a_vin;

import fr.eni.cave_a_vin.bo.*;
import fr.eni.cave_a_vin.dal.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestRequetes {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	BouteilleRepository bouteilleRepository;

	@Autowired
	CouleurRepository couleurRepository;

	Region paysDeLaLoire;
	Couleur blanc;
	List<Bouteille> bouteilles;
    @Autowired
    private RegionRepository regionRepository;

	@BeforeEach
	void initDB() {
		jeuDeDonneesBouteilles();
		jeuDeDonneesUtilisateur();
	}

	private void jeuDeDonneesBouteilles() {
		final Couleur rouge = Couleur
				.builder()
				.nom("Rouge")
				.build();

		blanc = Couleur
				.builder()
				.nom("Blanc")
				.build();

		final Couleur rose = Couleur
				.builder()
				.nom("Rosé")
				.build();

		entityManager.persist(rouge);
		entityManager.persist(blanc);
		entityManager.persist(rose);
		entityManager.flush();

		final Region grandEst = Region
				.builder()
				.nom("Grand Est")
				.build();

		paysDeLaLoire = Region
				.builder()
				.nom("Pays de la Loire")
				.build();

		final Region nouvelleAquitaine = Region
				.builder()
				.nom("Nouvelle-Aquitaine")
				.build();

		entityManager.persist(grandEst);
		entityManager.persist(paysDeLaLoire);
		entityManager.persist(nouvelleAquitaine);
		entityManager.flush();

		bouteilles = new ArrayList<>();
		bouteilles.add(Bouteille
				.builder()
				.nom("Blanc du DOMAINE ENI Ecole")
				.millesime("2022")
				.prix(23.95f)
				.quantite(1298)
				.region(paysDeLaLoire)
				.couleur(blanc)
				.build());
		bouteilles.add(Bouteille
				.builder()
				.nom("Rouge du DOMAINE ENI Ecole")
				.millesime("2018")
				.prix(11.45f)
				.quantite(987)
				.region(paysDeLaLoire)
				.couleur(rouge)
				.build());
		bouteilles.add(Bouteille
				.builder()
				.nom("Blanc du DOMAINE ENI Service")
				.millesime("2022")
				.prix(34)
				.petillant(true)
				.quantite(111)
				.region(grandEst)
				.couleur(blanc)
				.build());
		bouteilles.add(Bouteille
				.builder()
				.nom("Rouge du DOMAINE ENI Service")
				.millesime("2012")
				.prix(8.15f)
				.quantite(344)
				.region(paysDeLaLoire)
				.couleur(rouge)
				.build());
		bouteilles.add(Bouteille
				.builder()
				.nom("Rosé du DOMAINE ENI")
				.millesime("2020")
				.prix(33)
				.quantite(1987)
				.region(nouvelleAquitaine)
				.couleur(rose)
				.build());

		bouteilles.forEach(e -> {
			entityManager.persist(e);
			// Vérification de l'identifiant
			assertThat(e.getId()).isGreaterThan(0);
		});
		entityManager.flush();

	}

	private void jeuDeDonneesUtilisateur() {
		final List<Utilisateur> utilisateurs = new ArrayList<>();
		utilisateurs.add(Utilisateur
				.builder()
				.pseudo("harrisonford@email.fr")
				.password("IndianaJones3")
				.nom("Ford")
				.prenom("Harrison")
				.build());

		utilisateurs.add(Proprietaire
				.builder()
				.pseudo("georgelucas@email.fr")
				.password("Réalisateur&Producteur")
				.nom("Lucas")
				.prenom("George")
				.build());

		utilisateurs.add(Client
				.builder()
				.pseudo("natalieportman@email.fr")
				.password("MarsAttacks!")
				.nom("Portman")
				.prenom("Natalie")
				.build());

		// Contexte de la DB
		utilisateurs.forEach(e -> {
			entityManager.persist(e);
		});
		entityManager.flush();
	}

	@Test
	public void testSearchByRegion() {
		Optional<Region> regionOpt = regionRepository.searchByRegion("Nouvelle-Aquitaine");
		assertThat(regionOpt).isPresent();

		List<Bouteille> bouteilles = bouteilleRepository.searchByRegion(regionOpt.get().getId());
		assertThat(bouteilles).isNotEmpty();
		System.out.println(bouteilles);
	}

	@Test
	public void testSearchByCouleur() {
		Optional<Couleur> couleurOpt = couleurRepository.searchByCouleur("Rouge");
		assertThat(couleurOpt).isPresent();

		List<Bouteille> bouteilles = bouteilleRepository.searchByCouleur(couleurOpt.get().getId());
		assertThat(bouteilles).isNotEmpty();
		System.out.println(bouteilles);
	}

	@Test
	public void testSearchUtilisateurByPseudo() {
		String pseudo = "harrisonford@email.fr";
		String password = "IndianaJones3";

		Optional<Utilisateur> utilisateurOpt = utilisateurRepository.searchByPseudo(pseudo);
		assertThat(utilisateurOpt).isPresent();
		
		utilisateurOpt = utilisateurRepository.searchByPseudoAndPassword(pseudo, password);
		assertThat(utilisateurOpt).isPresent();
	}


}
