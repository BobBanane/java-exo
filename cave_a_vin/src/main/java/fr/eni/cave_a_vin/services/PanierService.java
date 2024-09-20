package fr.eni.cave_a_vin.services;


import fr.eni.cave_a_vin.bo.Panier;

import java.util.List;

public interface PanierService {

	Panier chargerPanier( int idPanier);
		
	List<Panier> chargerCommandes(String idclient);

	List<Panier> chargerPaniersNonPayes( String idclient);

	Panier ajouterOuMAJPanier(Panier p);
	
	Panier passerCommande(Panier panier);
}
