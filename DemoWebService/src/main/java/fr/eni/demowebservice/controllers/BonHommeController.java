package fr.eni.demowebservice.controllers;

import fr.eni.demowebservice.dal.BonHommeRepository;
import fr.eni.demowebservice.entities.Bonhomme;
import fr.eni.demowebservice.services.BonhommeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bonhommes")
public class BonHommeController {

    private final BonHommeRepository bonHommeRepository;
    private BonhommeService bonhommeService;

    public BonHommeController( BonhommeService bonhommeService, BonHommeRepository bonHommeRepository ) {
        this.bonhommeService = bonhommeService;
        this.bonHommeRepository = bonHommeRepository;
    }

    @GetMapping(path ="")
    public ResponseEntity<?> getbonHommes() {
        List<Bonhomme> bonhommeList = bonhommeService.getBonHommes();

        if(bonhommeList.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(bonhommeList);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getbonHomme(
            @PathVariable("id") String id
    ) {
        Long idBonHomme;

        // vérifie que le param d'entré est valide
        try {
            idBonHomme = Long.getLong(id);
        } catch(NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        Optional<Bonhomme> bonHommeOpt = bonhommeService.getBonHomme(idBonHomme);
        if(!bonHommeOpt.isPresent()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bonHommeOpt.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Bonhomme bonhomme) {

        try {
            bonhommeService.save(bonhomme);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch( RuntimeException e ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }

}
