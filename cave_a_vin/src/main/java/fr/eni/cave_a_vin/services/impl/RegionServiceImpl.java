package fr.eni.cave_a_vin.services.impl;

import fr.eni.cave_a_vin.bo.Client;
import fr.eni.cave_a_vin.bo.LignePanier;
import fr.eni.cave_a_vin.bo.Panier;
import fr.eni.cave_a_vin.bo.Region;
import fr.eni.cave_a_vin.dal.ClientRepository;
import fr.eni.cave_a_vin.dal.LignePanierRepository;
import fr.eni.cave_a_vin.dal.PanierRepository;
import fr.eni.cave_a_vin.dal.RegionRepository;
import fr.eni.cave_a_vin.services.PanierService;
import fr.eni.cave_a_vin.services.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Proprio peut accéder à toutes les requêtes **/
@AllArgsConstructor
@Service
public class RegionServiceImpl implements RegionService {

	private RegionRepository regionRepository;

	/** Permet au Client (et au Proprio) de voir un certain Panier **/
	@Override
	public List<Region> chargerRegions() {
		return regionRepository.findAll();
	}

	/** Permet au Client (et au Proprio) de voir les commandes d'un Client **/
	@Override
	public Region chargerRegion( Integer id) {
		// Validation du client
		if (id < 0) {
			throw new RuntimeException("Identifiant invalide");
		}

		Optional<Region> regionOpt = regionRepository.findById(id);

		if(!regionOpt.isPresent()) {
			throw new RuntimeException("Aucune Région trouvée à cet identifiant");
		}

		return regionOpt.get();
	}
}
