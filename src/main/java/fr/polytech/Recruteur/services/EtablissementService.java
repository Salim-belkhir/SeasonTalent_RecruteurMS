package fr.polytech.Recruteur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.polytech.Recruteur.models.Etablissement;
import fr.polytech.Recruteur.repositories.EtablissementRepository;

@Service
public class EtablissementService {
    
    @Autowired
    private EtablissementRepository etablissementRepository;

    public Etablissement getEtablissement(Long id) {
        return etablissementRepository.findById(id).orElse(null);
    }

    public List<Etablissement> getAllEtablissements() {
        return etablissementRepository.findAll();
    }

    public Etablissement addEtablissement(Etablissement etablissement) {
        if(etablissement == null || etablissement.getNomEtablissement() == null || etablissement.getNomEtablissement().isBlank()
            || etablissement.getLogo() == null || etablissement.getLogo().isBlank()){
            return null;
        }
        return etablissementRepository.save(etablissement);
    }

    public Etablissement updateEtablissement(Long id, Etablissement etablissement) {
        if(id == null || etablissement == null){
            return null;
        }
        Etablissement etablissementToUpdate = etablissementRepository.findById(id).orElse(null);
        if(etablissementToUpdate == null){
            return null;
        }

        if(etablissement.getNomEtablissement() != null && !etablissement.getNomEtablissement().isBlank()){
            etablissementToUpdate.setNomEtablissement(etablissement.getNomEtablissement());
        }
        if(etablissement.getLogo() != null && !etablissement.getLogo().isBlank()){
            etablissementToUpdate.setLogo(etablissement.getLogo());
        }
        if(etablissement.getAdresse() != null){
            if(etablissement.getAdresse().getNumeroVoie() != null){
                etablissementToUpdate.getAdresse().setNumeroVoie(etablissement.getAdresse().getNumeroVoie());
            }
            if(etablissement.getAdresse().getVille() != null && !etablissement.getAdresse().getVille().isBlank()){
                etablissementToUpdate.getAdresse().setVille(etablissement.getAdresse().getVille());
            }
            if(etablissement.getAdresse().getVille() != null && !etablissement.getAdresse().getVille().isBlank()){
                etablissementToUpdate.getAdresse().setVille(etablissement.getAdresse().getVille());
            }
            if(etablissement.getAdresse().getCodePostal() != null){
                etablissementToUpdate.getAdresse().setCodePostal(etablissement.getAdresse().getCodePostal());
            }
            etablissementToUpdate.setAdresse(etablissement.getAdresse());
        }

        return etablissementRepository.save(etablissementToUpdate);
    }

    public Etablissement deleteEtablissement(Long id) {
        if(id == null){
            return null;
        }
        Etablissement etablissementToDelete = etablissementRepository.findById(id).orElse(null);
        if(etablissementToDelete == null){
            return null;
        }
        etablissementRepository.delete(etablissementToDelete);
        return etablissementToDelete;
    }
}
