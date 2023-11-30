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
        if(adresse == null || adresse.getNumeroVoie() == null || adresse.getNomVoie() == null 
            || adresse.getCodePostal() == null || adresse.getVille() == null){
            return null;
        }
        return adresseRepository.save(adresse);
    }

    public Adresse updateAdresse(Long idAdresse, Adresse adresse) {
        Adresse adresseToUpdate = adresseRepository.findById(idAdresse).orElse(null);
        if(adresseToUpdate == null){
            return null;
        }

        if(adresse.getNumeroVoie() != null){
            adresseToUpdate.setNumeroVoie(adresse.getNumeroVoie());
        }
        if(adresse.getNomVoie() != null){
            adresseToUpdate.setNomVoie(adresse.getNomVoie());
        }
        if(adresse.getCodePostal() != null){
            adresseToUpdate.setCodePostal(adresse.getCodePostal());
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
