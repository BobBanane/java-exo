package fr.eni.gestionavis.dal;

import fr.eni.gestionavis.bo.Cours;
import fr.eni.gestionavis.bo.CoursId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CoursRepository extends MongoRepository<Cours, CoursId> {



}
