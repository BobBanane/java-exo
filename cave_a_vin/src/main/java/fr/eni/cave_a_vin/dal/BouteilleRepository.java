package fr.eni.cave_a_vin.dal;

import fr.eni.cave_a_vin.bo.Bouteille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {

    @Query(value = "SELECT b.* FROM cav_bottle b WHERE region_id = :idRegion ", nativeQuery = true)
    List<Bouteille> searchByRegion( @Param ("idRegion") Integer idRegion);

    @Query(value = "SELECT b.* FROM cav_bottle b WHERE region_id = :idCouleur ", nativeQuery = true)
    List<Bouteille> searchByCouleur( @Param ("idCouleur") Integer idCouleur);

}
