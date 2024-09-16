package fr.eni.cave_a_vin.dal;

import fr.eni.cave_a_vin.bo.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprioRepository extends JpaRepository<Proprietaire, String> {
}
