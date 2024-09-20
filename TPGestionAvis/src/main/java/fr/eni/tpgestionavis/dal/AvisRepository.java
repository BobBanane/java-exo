package fr.eni.tpgestionavis.dal;

import fr.eni.tpgestionavis.bo.Avis;
import fr.eni.tpgestionavis.bo.Bouteille;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(path = "avis", collectionResourceRel = "avis")
public interface AvisRepository extends MongoRepository<Avis, String> {

    // Partie 1
    List<Avis> findByNoteLessThan( int note );
    List<Avis> findByNoteGreaterThanEqual( int note );
    List<Avis> findByBouteille( Bouteille bouteille );


    /*** Spring data rest friendly ***/
    List<Avis> findByBouteilleId( @Param("id") Integer id );

    // Partie 2
    List<Avis> findByClient_Pseudo( String pseudo );
    List<Avis> findByClient_QuantiteCommandeeGreaterThan( int quantiteCommandee );

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    List<Avis> findByDateBetween(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    );

}
