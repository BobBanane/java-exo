package fr.eni.cave_a_vin.services.impl;

import fr.eni.cave_a_vin.bo.Bouteille;
import fr.eni.cave_a_vin.bo.Couleur;
import fr.eni.cave_a_vin.bo.Region;
import fr.eni.cave_a_vin.dal.BouteilleRepository;
import fr.eni.cave_a_vin.dal.CouleurRepository;
import fr.eni.cave_a_vin.dal.RegionRepository;
import fr.eni.cave_a_vin.services.BouteilleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BouteilleServiceImpl implements BouteilleService {
	private BouteilleRepository bRepository;
	private RegionRepository rRepository;
	private CouleurRepository cRepository;

	@Override
	public List<Bouteille> chargerToutesBouteilles() {
		return bRepository.findAll();
	}

	@Override
	public Optional<Bouteille> chargerBouteilleParId(int idBouteille) {
		// Validation de l'identifiant
		if (idBouteille <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Bouteille> opt = bRepository.findById(idBouteille);
		return opt;
	}

	@Override
	public List<Bouteille> chargerBouteillesParRegion(int idRegion) {
		final Region rDB = validerRegion(idRegion);

		final List<Bouteille> listeDB = bRepository.findByRegion(rDB);
		if (listeDB == null || listeDB.isEmpty()) {
			throw new RuntimeException("Aucune bouteille ne correspond");
		}
		return listeDB;		
	}

	private Region validerRegion(int idRegion) {
		// Valider la Region
		if (idRegion <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Region> opt = rRepository.findById(idRegion);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucune région ne correspond");
	}

	@Override
	public List<Bouteille> chargerBouteillesParCouleur(int idCouleur) {
		final Couleur cDB = validerCouleur(idCouleur);

		final List<Bouteille> listeDB = bRepository.findByCouleur(cDB);
		if (listeDB == null || listeDB.isEmpty()) {
			throw new RuntimeException("Aucune bouteille ne correspond");
		}
		return listeDB;		
	}

	private Couleur validerCouleur(int idCouleur) {
		// Valider la Couleur
		if (idCouleur <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Couleur> opt = cRepository.findById(idCouleur);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucune couleur de vin ne correspond");
	}

	public Bouteille save(Bouteille bouteille) {

		if(bouteille.getMillesime() == null || bouteille.getMillesime().isBlank()) {
			throw new RuntimeException("Bouteille invalide");
		}

		try {
			return bRepository.save(bouteille);
		} catch ( JpaSystemException e ) {
			throw new RuntimeException("Erreur lors de l'enregistrement");
		}
	}

	public Bouteille update(Bouteille bouteille) {

		if(bouteille.getMillesime() == null || bouteille.getMillesime().isBlank()) {
			throw new RuntimeException("Bouteille invalide");
		}

		try {
			return bRepository.save(bouteille);
		} catch ( JpaSystemException e ) {
			throw new RuntimeException("Erreur lors de l'enregistrement");
		}
	}

	public void deleteId(Integer id) {

		if(id < 0) {
			throw new RuntimeException("L'identifiant doite être positif");
		}

		if(!bRepository.findById(id).isPresent()) {
			throw new RuntimeException("La bouteille n'existe pas");
		}

		try {
			bRepository.deleteById(id);
		} catch ( Exception e ) {
			throw new RuntimeException("Erreur lors de l'enregistrement" + e.getCause().toString());
		}
	}
}
