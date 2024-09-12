package fr.eni.cave_a_vin;

import fr.eni.cave_a_vin.bo.Bouteille;
import fr.eni.cave_a_vin.bo.Couleur;
import fr.eni.cave_a_vin.bo.Region;
import fr.eni.cave_a_vin.dal.BouteilleRepository;
import fr.eni.cave_a_vin.dal.CouleurRepository;
import fr.eni.cave_a_vin.dal.RegionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestManyToOne {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	BouteilleRepository bouteilleRepository;

	@Autowired
	CouleurRepository couleurRepository;

	@Autowired
	RegionRepository regionRepository;

	Couleur rouge;
	Couleur blanc;
	Couleur rose;

	Region grandEst;
	Region paysDeLaLoire;
	Region nouvelleAquitaine;

	@BeforeEach
	public void initDB() {
		rouge = Couleur
				.builder()
				.nom("Rouge")
				.build();

		blanc = Couleur
				.builder()
				.nom("Blanc")
				.build();

		rose = Couleur
				.builder()
				.nom("Rosé")
				.build();

		couleurRepository.save(rouge);
		couleurRepository.save(blanc);
		couleurRepository.save(rose);

		grandEst =
				Region
				.builder()
				.nom("Grand Est")
				.build();

		paysDeLaLoire =
				Region
				.builder()
				.nom("Pays de la Loire")
				.build();

		nouvelleAquitaine =
				Region
				.builder()
				.nom("Nouvelle Aquitaine")
				.build();

		regionRepository.save(grandEst);
		regionRepository.save(paysDeLaLoire);
		regionRepository.save(nouvelleAquitaine);
	}

	//TODO

	private List<Bouteille> jeuDeDonnees() {
		List<Bouteille> bouteilles = new ArrayList<>();
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
		return bouteilles;
	}

	@Test
	@Order(1)
	public void test_save() {

		Bouteille bouteilleRandom = jeuDeDonnees().get(0);

		Bouteille bouteilleBDD = bouteilleRepository.save(bouteilleRandom);
		Assertions.assertThat(bouteilleBDD).isNotNull();

	}

	@Test
	@Order(2)
	public void test_save_bouteilles_regions_couleurs() {

		List<Bouteille> bouteilles = jeuDeDonnees();

		List<Bouteille> bouteillesBDD = bouteilleRepository.saveAll(bouteilles);
		Assertions.assertThat(bouteillesBDD.size()).isEqualTo(bouteilles.size());

		bouteillesBDD.forEach(System.out::println);

	}

	@Test
	@Order(3)
	public void test_delete() {

		List<Bouteille> bouteillesBDD = bouteilleRepository.saveAll(jeuDeDonnees());
		Bouteille bouteilleRandom = bouteillesBDD.get(0);
		Couleur couleur = bouteilleRandom.getCouleur();
		Region region = bouteilleRandom.getRegion();

		// vérifie que la bouteille à tester est dans la BDD
		Optional<Bouteille> bouteilleOpt = bouteilleRepository.findById(bouteilleRandom.getId());
		Assertions.assertThat(bouteilleOpt).isPresent();

		// vérifie que la bouteille a bien été supprimée
		bouteilleRepository.delete(bouteilleRandom);
		bouteilleOpt = bouteilleRepository.findById(bouteilleRandom.getId());
		Assertions.assertThat(bouteilleOpt).isNotPresent();

		// Vérifie que la couleur et la région n'ont pas été supprimées
		Optional<Couleur> couleurOpt = couleurRepository.findById(couleur.getId());
		Assertions.assertThat(couleurOpt).isPresent();

		Optional<Region> regionOpt = regionRepository.findById(region.getId());
		Assertions.assertThat(regionOpt).isPresent();

	}

}
