package fr.eni.demowebservice.controllers;


import fr.eni.demowebservice.entities.Crayon;
import fr.eni.demowebservice.services.EniService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eniecole")
public class EniController {

    private EniService eniService;

    public EniController( EniService service) {
        this.eniService = service;
    }

    @GetMapping
    public String welcome() {
        return "Bienvenue sur l'API ENI Ecole";
    }

    // créé automatiquement un json
    @GetMapping(path = "/crayons")
    public ResponseEntity<?> getCrayons() {
        List<Crayon> resultats = eniService.listerCrayon();

        if (resultats == null || resultats.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(resultats);
    }

    @GetMapping(path = "/crayons/{id}")
    public ResponseEntity<?> getCrayonById(
            @PathVariable int id
    ) {
        Optional<Crayon> crayonOpt = eniService.getCrayonById(id);
        if(!crayonOpt.isPresent())  return ResponseEntity.noContent().build();

        return ResponseEntity.ok(crayonOpt.get());
    }

}
