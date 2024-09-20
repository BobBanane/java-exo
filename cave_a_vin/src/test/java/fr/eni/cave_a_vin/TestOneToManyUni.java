package fr.eni.cave_a_vin;

import fr.eni.cave_a_vin.bo.LignePanier;
import fr.eni.cave_a_vin.bo.Panier;
import fr.eni.cave_a_vin.dal.LignePanierRepository;
import fr.eni.cave_a_vin.dal.PanierRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("Test")
public class TestOneToManyUni {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PanierRepository repository;

    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private LignePanierRepository lignePanierRepository;

	@Test
	@Order(1)
	@Commit
	public void test_save_nouvelleLigne_nouveauPanier() {
		final Panier panier = new Panier();
		final LignePanier lp = LignePanier
				.builder()
				.quantite(3)
				.prix(3 * 11.45f)
				.build();
		panier.getLignesPanier().add(lp);
		panier.setPrixTotal(lp.getPrix());

		panierRepository.save(panier);

		Assertions.assertThat(panier.getId()).isGreaterThan(0);

		System.out.println(panier);
	}

	@Test
	@Order(2)
	public void test_save_nouvelleLigne_Panier() {
		final Integer idPanier = 1;
		final LignePanier lp = LignePanier
				.builder()
				.quantite(10)
				.prix(23.95f)
				.build();

		Optional<Panier> panierOpt = panierRepository.findById(idPanier);
		Assertions.assertThat(panierOpt).isPresent();
		Panier panier = panierOpt.get();

		// Garde les anciennes valeurs pour le nombre de lignes et le total de prix pour les tests
		int oldLignesPanierSize = panierOpt.get().getLignesPanier().size();
		float oldTotal = panier.getPrixTotal();
		panier.addLignePanier(lp);

		panier = panierRepository.save(panier);

		Assertions.assertThat(panier.getLignesPanier().size()).isEqualTo(oldLignesPanierSize + 1);
		Assertions.assertThat(panier.getPrixTotal()).isEqualTo(oldTotal + lp.getTolalPrice());

		System.out.println(panierOpt);
	}

	@Test
	@Order(3)
	public void test_orphanRemoval() {
		final Integer idPanier = 1;

		Optional<Panier> panierOpt = panierRepository.findById(idPanier);
		Assertions.assertThat(panierOpt).isPresent();

		// Si il exite au moins une ligne, récupère le première
		Assertions.assertThat(panierOpt.get().getLignesPanier()).isNotEmpty();
		final LignePanier lignePanier = panierOpt.get().getLignesPanier().get(0);

		// Supprime la première ligne et applique les modifications à la BDD
		panierOpt.get().getLignesPanier().remove(lignePanier);
		panierRepository.saveAndFlush(panierOpt.get());

		Optional<LignePanier> ligneOpt = lignePanierRepository.findById(lignePanier.getId());
		Assertions.assertThat(ligneOpt).isNotPresent();
	}

	@Test
	@Order(4)
	public void test_delete() {
		final Integer idPanier = 1;

		Optional<Panier> panierOpt = panierRepository.findById(idPanier);
		Assertions.assertThat(panierOpt).isPresent();
		Panier panier = panierOpt.get();

		final LignePanier lp = LignePanier
				.builder()
				.quantite(3)
				.prix(3 * 11.45f)
				.build();
		panier.getLignesPanier().add(lp);
		panier.setPrixTotal(lp.getPrix());

		// save l'ajout de la nouvelle ligne
		Panier panierBDD = panierRepository.save(panier);

		// supprime le panier complet
		panierRepository.delete(panier);

		panierOpt = panierRepository.findById(idPanier);
		Assertions.assertThat(panierOpt).isNotPresent();

		List<LignePanier> lignePanierList = lignePanierRepository.findAll();
		Assertions.assertThat(lignePanierList).isEmpty();
	}


}
