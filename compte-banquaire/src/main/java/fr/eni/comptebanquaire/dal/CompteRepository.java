package fr.eni.comptebanquaire.dal;

import fr.eni.comptebanquaire.bo.Compte;
import fr.eni.comptebanquaire.bo.ComptePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, ComptePK> {

    Optional<Compte> findOneByNumCompte(String numCompte);

}
