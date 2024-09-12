package fr.eni.comptebanquaire.bll;

import fr.eni.comptebanquaire.bo.Compte;
import fr.eni.comptebanquaire.bo.ComptePK;

import java.util.Optional;

public interface CompteService {

    void ajouterCompte(Compte compte);
    void crediter(Compte compte, Double montant);
    void debiter(Compte compte, Double montant) throws Exception;
    void virement( Compte emetteur, Compte debiteur, Double montant) throws Exception;

    Optional<Compte> getCompteByNumCompte( String numCompte);
    Optional<Compte> getCompteById( ComptePK comptePKid);
}
