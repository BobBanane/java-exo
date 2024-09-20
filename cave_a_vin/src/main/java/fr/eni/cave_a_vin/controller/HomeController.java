package fr.eni.cave_a_vin.controller;

import fr.eni.cave_a_vin.bo.Bouteille;
import fr.eni.cave_a_vin.services.BouteilleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class HomeController {

    public HomeController( ) {

    }

    @GetMapping(path = "/")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok("coucou");
    }

}
