package fr.eni.comptebanquaire.bll;

import fr.eni.comptebanquaire.bo.Compte;
import fr.eni.comptebanquaire.bo.ComptePK;
import fr.eni.comptebanquaire.dal.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CompteServiceImpl implements CompteService {

    @Autowired
    private CompteRepository compteRepository;

    @Override
    public void ajouterCompte( Compte compte ) {
        //TODO verification
        try {
            compteRepository.save(compte);
        } catch (Exception e) {}
    }

    @Override
    public void crediter( Compte compte, Double montant ) {
        //TODO verification
        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);
    }

    @Override
    public void debiter( Compte compte, Double montant ) throws Exception {
        //TODO verification
        if(compte.getSolde() < montant) throw new Exception("Solde insuffisant");

        compte.setSolde(compte.getSolde() - montant);
        compteRepository.save(compte);
    }

    @Transactional

    @Override
    public void virement( Compte emetteur, Compte beneficaire, Double montant ) throws Exception {
        debiter(emetteur, montant);
        crediter(beneficaire, montant);
    }

    @Override
    public Optional<Compte> getCompteByNumCompte( String numCompte ) {
        System.out.println(numCompte);
        return compteRepository.findOneByNumCompte(numCompte);
    }

    @Override
    public Optional<Compte> getCompteById( ComptePK id ) {
        return compteRepository.findById(id);
    }
}
