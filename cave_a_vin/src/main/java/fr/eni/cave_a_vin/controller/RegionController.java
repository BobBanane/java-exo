package fr.eni.cave_a_vin.controller;

import fr.eni.cave_a_vin.services.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.ResponseAPDU;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/caveavin/regions")
public class RegionController {

    private RegionService regionService;

    @GetMapping("")
    public ResponseEntity<?> getRegions() {
        return ResponseEntity.ok().body(regionService.chargerRegions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegions(@PathVariable int id) {
        return ResponseEntity.ok().body(regionService.chargerRegion(id));
    }
}
