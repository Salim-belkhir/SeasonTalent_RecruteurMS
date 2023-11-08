package fr.polytech.Recruteur.services;


import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.repositories.AdresseRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    public Adresse findAdresseById(Long id) {
        return adresseRepository.findById(id).orElse(null);
    }

    public List<Adresse> findAllAdresses() {
        return adresseRepository.findAll();
    }

    public Adresse addAdresse(Adresse adresse) {
        if(adresse == null || adresse.getNumero_voie() == null || adresse.getNom_voie() == null 
            || adresse.getCode_postal() == null || adresse.getVille() == null){
            return null;
        }
        return adresseRepository.save(adresse);
    }

    public Adresse updateAdresse(Long idAdresse, Adresse adresse) {
        Adresse adresseToUpdate = adresseRepository.findById(idAdresse).orElse(null);
        if(adresseToUpdate == null){
            return null;
        }

        if(adresse.getNumero_voie() != null){
            adresseToUpdate.setNumero_voie(adresse.getNumero_voie());
        }
        if(adresse.getNom_voie() != null){
            adresseToUpdate.setNom_voie(adresse.getNom_voie());
        }
        if(adresse.getCode_postal() != null){
            adresseToUpdate.setCode_postal(adresse.getCode_postal());
        }
        if(adresse.getVille() != null){
            adresseToUpdate.setVille(adresse.getVille());
        }

        return adresseRepository.save(adresseToUpdate);
    }

    public Boolean deleteAdresse(Long id) {
        Boolean isDeleted = false;
        if(adresseRepository.existsById(id)){
            adresseRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }
}
