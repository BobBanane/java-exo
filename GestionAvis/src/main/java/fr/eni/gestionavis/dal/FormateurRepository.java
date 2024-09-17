package fr.eni.gestionavis.dal;

import fr.eni.gestionavis.bo.Formateur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormateurRepository extends MongoRepository<Formateur, String> {



}
