package fr.eni.gestionavis.dal;

import fr.eni.gestionavis.bo.Avis;
import fr.eni.gestionavis.bo.Cours;
import fr.eni.gestionavis.bo.Formateur;
import fr.eni.gestionavis.bo.Stagiaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvisRepository extends MongoRepository<Avis, String> {

    List<Avis> findByNoteCours( @Param("noteCours") int noteCours );
    List<Avis> findByNoteCoursLessThan( @Param("noteCours") int noteCours );
    List<Avis> findByNoteCoursGreaterThan( @Param("noteCours") int noteCours );

    List<Avis> findByStagiaire( @Param("stagiaire") Stagiaire stagiaire);
    List<Avis> findByFormateur( @Param("formateur") Formateur formateur);
    List<Avis> findByCours( @Param("cours") Cours cours);

}
