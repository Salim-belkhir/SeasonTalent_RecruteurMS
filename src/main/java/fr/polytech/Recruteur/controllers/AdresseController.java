package fr.polytech.Recruteur.controllers;


import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.services.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (adresse == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(adresse, HttpStatus.OK);
        }
    }
}
