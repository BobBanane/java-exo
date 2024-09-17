package fr.eni.tpgestionavis.dal;

import fr.eni.tpgestionavis.bo.Avis;
import fr.eni.tpgestionavis.bo.Bouteille;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvisRepository extends MongoRepository<Avis, String> {

    // Partie 1
    List<Avis> findByNoteLessThan( int note );
    List<Avis> findByNoteGreaterThanEqual( int note );
    List<Avis> findByBouteille( Bouteille bouteille );

    // Partie 2
    List<Avis> findByClient_Pseudo( String pseudo );
    List<Avis> findByClient_QuantiteCommandeeGreaterThan( int quantiteCommandee );
    List<Avis> findByDateBetween( LocalDate from, LocalDate to );

}
