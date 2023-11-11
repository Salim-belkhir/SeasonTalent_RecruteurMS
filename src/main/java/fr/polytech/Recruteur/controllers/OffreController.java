package fr.polytech.Recruteur.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.Recruteur.models.Offre;
import fr.polytech.Recruteur.services.OffreService;

@RestController
@RequestMapping("/offres")
public class OffreController {
    @Autowired
    private OffreService offreService;

    @GetMapping("/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable Long id) {
        Offre offre = offreService.findOffreById(id);
        HttpStatus status = (offre == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(offre, status);
    }


    @GetMapping("/")
    public ResponseEntity<List<Offre>> getAllOffres() {
        List<Offre> offres = offreService.findAllOffres();
        HttpStatus status = (offres.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(offres, status);
    }

    @GetMapping("/etablissement/{id}")
    public ResponseEntity<List<Offre>> getAllOffresByEtablissement(@PathVariable Long id) {
        List<Offre> offres = offreService.findAllOffresByEtablissement(id);
        HttpStatus status = (offres.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(offres, status);
    }


    @PostMapping("/")
    public ResponseEntity<Offre> addOffre(@RequestBody Offre offre) {
        Offre newOffre = offreService.addOffre(offre);
        HttpStatus status = (newOffre == null) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(newOffre, status);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable Long id, @RequestBody Offre offre) {
        Offre newOffre = offreService.updateOffre(id, offre);
        HttpStatus status = (newOffre == null) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(newOffre, status);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Offre> patchOffre(@PathVariable Long id, @RequestBody Offre offre) {
        Offre newOffre = offreService.updateOffre(id, offre);
        HttpStatus status = (newOffre == null) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(newOffre, status);
    }


    //TODO : verifier que l'annonce appartient bien à l'utilisateur ou c'est l'admin
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOffre(@PathVariable Long id) {
        Boolean isDeleted = offreService.deleteOffre(id);
        HttpStatus status = (isDeleted == null) ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(isDeleted, status);
    }
}
