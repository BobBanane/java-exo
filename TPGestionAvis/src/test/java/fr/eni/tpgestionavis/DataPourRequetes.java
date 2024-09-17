package fr.eni.tpgestionavis;

import fr.eni.tpgestionavis.bo.Avis;
import fr.eni.tpgestionavis.bo.Bouteille;
import fr.eni.tpgestionavis.bo.BouteilleId;
import fr.eni.tpgestionavis.bo.Client;
import fr.eni.tpgestionavis.dal.AvisRepository;
import fr.eni.tpgestionavis.dal.BouteilleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataPourRequetes {

	@Autowired
	BouteilleRepository bouteilleRepository;

	@Autowired
	AvisRepository avisRepository;

	void insertion_Bouteille_DB() {
		final List<Bouteille> listeBouteilles = new ArrayList<>();
		// Création de 3 Bouteille

		listeBouteilles.add(Bouteille
				.builder()
				.id(BouteilleId
						.builder()
						.idBouteille(18298)
						.idRegion(3)
						.idCouleur(1)
						.build())
				.nom("Vin ENI Edition")
				.build());

		listeBouteilles.add(Bouteille
				.builder()
				.id(BouteilleId
						.builder()
						.idBouteille(1298)
						.idRegion(3)
						.idCouleur(2)
						.build())
				.nom("Vin ENI Service")
				.build());

		listeBouteilles.add(Bouteille
				.builder()
				.id(BouteilleId
						.builder()
						.idBouteille(1999)
						.idRegion(2)
						.idCouleur(3)
						.build())
				.nom("Vin ENI Ecole")
				.build());

		listeBouteilles.forEach(b -> {
			bouteilleRepository.save(b);
		});
	}

	void insertion_Avis_DB() {
		// Récupération depuis la base des Bouteille
		final List<Bouteille> listeBouteilles = bouteilleRepository.findAll();
		assertThat(listeBouteilles).isNotNull();
		assertThat(listeBouteilles).isNotEmpty();
		assertThat(listeBouteilles.size()).isEqualTo(3);

		// Liste de Client
		final List<Client> listeClients = new ArrayList<>();
		// Création de 3 Client
		listeClients.add(Client
				.builder()
				.pseudo("bobeponge@email.fr")
				.quantiteCommandee(11)
				.build());
		listeClients.add(Client
				.builder()
				.pseudo("patricketoile@email.fr")
				.quantiteCommandee(12)
				.build());
		listeClients.add(Client
				.builder()
				.pseudo("carlotentacule@email.fr")
				.quantiteCommandee(25)
				.build());

		// Ajout d'Avis par Client sur chaque Bouteille
		// Faire varier la note
		int note = 2;
		
		
		for (Client c : listeClients) {
			//Faire varier la date :
			LocalDateTime ldf = LocalDateTime.of(2023, 7, 13, 15, 28);
			//Attention, en base l'heure sera en GMT (Heure Française - 2)

			for (int i = 0; i < listeBouteilles.size(); i++) {
				final Bouteille b = listeBouteilles.get(i);

				// Faire varier la quantite du Client selon la note
				c.setQuantiteCommandee(c.getQuantiteCommandee() * note);
				final Avis avis = Avis
						.builder()
						.note(note)
						.commentaire("Commentaire (" + note + ")")
						.bouteille(b)
						.client(c)
						.date(ldf)
						.build();
				// Sauvegarde de Avis
				avisRepository.save(avis);
				// incrémenter la date
				ldf = ldf.plusDays(10);
			}
			// incrémenter la note
			note++;
		}
	}

	@Test
	@Commit
	void test_insertion_DB() {

		avisRepository.deleteAll();
		bouteilleRepository.deleteAll();

		insertion_Bouteille_DB();
		final List<Bouteille> listeBouteilles = bouteilleRepository.findAll();
		assertThat(listeBouteilles).isNotNull();
		assertThat(listeBouteilles).isNotEmpty();
		assertThat(listeBouteilles.size()).isEqualTo(3);

		insertion_Avis_DB();
		final List<Avis> listeAvis = avisRepository.findAll();
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isEqualTo(9);
	}
}
