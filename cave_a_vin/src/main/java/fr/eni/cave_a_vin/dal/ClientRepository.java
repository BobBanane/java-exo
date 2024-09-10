package fr.eni.cave_a_vin.dal;

import fr.eni.cave_a_vin.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {



}
