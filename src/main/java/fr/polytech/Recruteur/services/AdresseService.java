package fr.polytech.Recruteur.services;


import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.repositories.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    public Adresse findAdresseById(Long id) {
        return adresseRepository.findById(id).orElse(null);
    }
}
