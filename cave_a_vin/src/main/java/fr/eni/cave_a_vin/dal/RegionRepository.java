package fr.eni.cave_a_vin.dal;

import fr.eni.cave_a_vin.bo.Couleur;
import fr.eni.cave_a_vin.bo.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Query("SELECT r FROM Region r WHERE r.nom = :region")
    Optional<Region> searchByRegion( @Param("region") String region);

}
