package fr.eni.gestionavis.dal;

import fr.eni.gestionavis.bo.Avis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvisRepository extends MongoRepository<Avis, Integer> {



}
