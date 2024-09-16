package fr.eni.cave_a_vin.dal;

import fr.eni.cave_a_vin.bo.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CouleurRepository extends JpaRepository<Couleur, Integer> {

    @Query("SELECT c FROM Couleur c WHERE c.nom = :couleur")
    Optional<Couleur> searchByCouleur( @Param("couleur") String couleur);

}
