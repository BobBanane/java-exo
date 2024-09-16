package fr.eni.cave_a_vin.dal;

import fr.eni.cave_a_vin.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

    @Query("SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo")
    Optional<Utilisateur> searchByPseudo(@Param("pseudo") String pseudo);

    @Query("SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo AND u.password = :password")
    Optional<Utilisateur> searchByPseudoAndPassword( @Param("pseudo") String pseudo, @Param("password") String password);

}
