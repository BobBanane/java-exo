package fr.eni.demowebservice.dal;


import fr.eni.demowebservice.entities.Bonhomme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonHommeRepository extends JpaRepository<Bonhomme, Long> {



}
