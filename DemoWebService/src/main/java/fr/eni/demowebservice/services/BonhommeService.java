package fr.eni.demowebservice.services;

import fr.eni.demowebservice.dal.BonHommeRepository;
import fr.eni.demowebservice.entities.Bonhomme;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BonhommeService {

    private BonHommeRepository bonHommeRepository;

    public BonhommeService(BonHommeRepository bonHommeRepository) {
        this.bonHommeRepository = bonHommeRepository;
    }

    public List<Bonhomme> getBonHommes() {
        return bonHommeRepository.findAll();
    }

    public Optional<Bonhomme> getBonHomme(Long id) {
        return bonHommeRepository.findById(id);
    }

    public Bonhomme save(Bonhomme bonhomme) {

        if(bonhomme.getNom() == null || bonhomme.getNom().isBlank())  {
            throw new RuntimeException("Il faut renseigner le nom");
        }

        return bonHommeRepository.save(bonhomme);
    }

}
