package fr.eni.demoavismongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvisRepository extends MongoRepository<Avis, String> {

}
