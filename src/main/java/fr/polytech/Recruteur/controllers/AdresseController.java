package fr.polytech.Recruteur.controllers;


import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.services.AdresseService;

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

@RestController
@RequestMapping("/adresses")
public class AdresseController {
    @Autowired
    private AdresseService adresseService;

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        Adresse adresse = adresseService.findAdresseById(id);
        HttpStatus status = (adresse == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(adresse, status);
    }


    @GetMapping("/")
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        List<Adresse> adresses = adresseService.findAllAdresses();
        HttpStatus status = (adresses.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(adresses, status);
    }

    @PostMapping("/")
    public ResponseEntity<Adresse> addAdresse(@RequestBody Adresse adresse) {
        Adresse newAdresse = adresseService.addAdresse(adresse);
        HttpStatus status = (newAdresse == null) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(newAdresse, status);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Long idAdresse, @RequestBody Adresse adresse) {
        Adresse newAdresse = adresseService.updateAdresse(idAdresse, adresse);
        HttpStatus status = (newAdresse == null) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(newAdresse, status);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Adresse> patchAdresse(@PathVariable Long idAdresse, @RequestBody Adresse adresse) {
        Adresse newAdresse = adresseService.updateAdresse(idAdresse, adresse);
        HttpStatus status = (newAdresse == null) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(newAdresse, status);
    }

    //TODO: Gerer ici le cas de si l'utilisateur est autorisé à supprimer l'adresse, si pas authorisé retourné le status unauthorized
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        Boolean isDeleted = adresseService.deleteAdresse(id);
        HttpStatus status = (isDeleted) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }
}
