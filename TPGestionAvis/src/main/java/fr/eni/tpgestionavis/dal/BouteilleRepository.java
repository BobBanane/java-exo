package fr.eni.tpgestionavis.dal;

import fr.eni.tpgestionavis.bo.Bouteille;
import fr.eni.tpgestionavis.bo.BouteilleId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BouteilleRepository extends MongoRepository<Bouteille, BouteilleId> {



}
