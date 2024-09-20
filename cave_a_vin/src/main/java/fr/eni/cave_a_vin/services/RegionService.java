package fr.eni.cave_a_vin.services;


import fr.eni.cave_a_vin.bo.Panier;
import fr.eni.cave_a_vin.bo.Region;

import java.util.List;
import java.util.Optional;

public interface RegionService {

	List<Region> chargerRegions();
		
	Region chargerRegion( Integer id);

}
