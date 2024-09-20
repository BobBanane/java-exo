package fr.eni.cave_a_vin.controller;

import fr.eni.cave_a_vin.bo.Bouteille;
import fr.eni.cave_a_vin.services.BouteilleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/caveavin")
public class BouteilleController {

    private BouteilleService bouteilleService;

    public BouteilleController(BouteilleService bouteilleService) {
        this.bouteilleService = bouteilleService;
    }

    @GetMapping(path = "/bouteilles")
    public ResponseEntity<?> getBouteilles() {
        List<Bouteille> bouteilles = bouteilleService.chargerToutesBouteilles();

        if(bouteilles == null ||bouteilles.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(bouteilles);
    }

    @GetMapping(path = "/bouteilles/{id}")
    public ResponseEntity<?> getBouteillesByid( @PathVariable("id") String id) {
        try {
            Optional<Bouteille> bouteilleOpt = bouteilleService.chargerBouteilleParId(Integer.parseInt(id));
            if(!bouteilleOpt.isPresent()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(bouteilleOpt.get());
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Format de l'identifiant est incorrect");
        }
    }

    @GetMapping(path = "/bouteilles/region/{idRegion}")
    public ResponseEntity<?> getBouteillesByRegion( @PathVariable("idRegion") String idRegion) {
        try {

            List<Bouteille> bouteilles = bouteilleService.chargerBouteillesParRegion(Integer.parseInt(idRegion));
            if(bouteilles == null || bouteilles.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(bouteilles);

        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Format de l'identifiant est incorrect");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }

    @GetMapping(path = "/bouteilles/couleur/{idCouleur}")
    public ResponseEntity<?> getBouteillesByCouleur( @PathVariable("idCouleur") String idCouleur) {
        try {
            Integer id = Integer.parseInt(idCouleur);

            List<Bouteille> bouteilles = bouteilleService.chargerBouteillesParCouleur(id);
            if(bouteilles == null || bouteilles.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(bouteilles);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Format de l'identifiant est incorrect");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }

    @PostMapping("/bouteilles")
    public ResponseEntity<?> save(@Valid @RequestBody Bouteille bouteille) {

        try {
            Bouteille b = bouteilleService.save(bouteille);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @PutMapping("/bouteilles/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") /*String*/ int id , // test de cast géré par le exceptionHandler
            @Valid @RequestBody Bouteille bouteille
    ) {
//        Integer idBouteille;
//
//        try  {
//            idBouteille = Integer.parseInt(id);
//        } catch ( NumberFormatException e ) {
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Format de l'identifiant est incorrect");
//        }

        if(id < 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'identifiant doite être positif");
        }

        try {
            bouteille.setId(id);
            Bouteille b = bouteilleService.update(bouteille);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @DeleteMapping("/bouteilles/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        Integer idBouteille;

        try  {
            idBouteille = Integer.parseInt(id);
        } catch ( NumberFormatException e ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Format de l'identifiant est incorrect");
        }

        try {
            bouteilleService.deleteId(idBouteille);
            return ResponseEntity.ok().body("La suppression a bien été effectuée");
        } catch ( RuntimeException e ) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
