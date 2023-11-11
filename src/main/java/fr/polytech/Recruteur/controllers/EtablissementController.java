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

import fr.polytech.Recruteur.models.Etablissement;
import fr.polytech.Recruteur.services.EtablissementService;

@RestController
@RequestMapping("/etablissements")
public class EtablissementController {
    @Autowired
    private EtablissementService etablissementService;

    @GetMapping("/{id}")
    public ResponseEntity<Etablissement> getEtablissement(@PathVariable("id") Long id) {
        Etablissement etablissement = etablissementService.getEtablissement(id);
        HttpStatus status = etablissement == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(etablissement, status);
    }

    @GetMapping("/")
    public ResponseEntity<List<Etablissement>> getEtablissements() {
        List<Etablissement> etablissements = etablissementService.getAllEtablissements();
        return new ResponseEntity<>(etablissements, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Etablissement> addEtablissement(@RequestBody Etablissement etablissement) {
        Etablissement newEtablissement = etablissementService.addEtablissement(etablissement);
        return new ResponseEntity<>(newEtablissement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etablissement> updateEtablissement(@PathVariable Long id, @RequestBody Etablissement etablissement) {
        Etablissement updatedEtablissement = etablissementService.updateEtablissement(id, etablissement);
        HttpStatus status = updatedEtablissement == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(updatedEtablissement, status);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Etablissement> patchEtablissement(@PathVariable Long id, @RequestBody Etablissement etablissement) {
        Etablissement patchedEtablissement = etablissementService.updateEtablissement(id, etablissement);
        HttpStatus status = patchedEtablissement == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(patchedEtablissement, status);
    }


    //TODO : Verifier les droits pour supprimer
    @DeleteMapping("/{id}")
    public ResponseEntity<Etablissement> deleteEtablissement(@PathVariable Long id) {
        Etablissement etablissement = etablissementService.deleteEtablissement(id);
        HttpStatus status = etablissement == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(etablissement, status);
    }
}
