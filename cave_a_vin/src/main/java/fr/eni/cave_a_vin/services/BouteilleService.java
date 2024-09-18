package fr.eni.cave_a_vin.services;

import fr.eni.cave_a_vin.bo.Bouteille;

import java.util.List;
import java.util.Optional;


public interface BouteilleService {
	List<Bouteille> chargerToutesBouteilles();
	
	Optional<Bouteille> chargerBouteilleParId( int idBouteille);

	List<Bouteille> chargerBouteillesParRegion(int idRegion);

	List<Bouteille> chargerBouteillesParCouleur(int idCouleur);

	Bouteille save(Bouteille bouteille);

	Bouteille update(Bouteille bouteille);

	void deleteId(Integer id);
}
