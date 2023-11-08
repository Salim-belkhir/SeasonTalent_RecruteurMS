package fr.polytech.Recruteur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.polytech.Recruteur.models.Offre;
import fr.polytech.Recruteur.repositories.OffreRepository;

@Service
public class OffreService {
    @Autowired
    private OffreRepository offreRepository;

    public Offre findOffreById(Long id) {
        return offreRepository.findById(id).orElse(null);
    }

    public List<Offre> findAllOffres() {
        return offreRepository.findAll();
    }

    //TODO : Voir ce qu'on vérifie
    public Offre addOffre(Offre offre) {
        if(offre == null || offre.getTitre() == null || offre.getDescr() == null 
            || offre.getEtablissement() == null){
            return null;
        }
        return offreRepository.save(offre);
    }

    public Offre updateOffre(Long idOffre, Offre offre) {
        Offre offreToUpdate = offreRepository.findById(idOffre).orElse(null);
        
        if(offreToUpdate == null){
            return null;
        }

        if(offre.getTitre() != null){
            offreToUpdate.setTitre(offre.getTitre());
        }
        if(offre.getDescr() != null){
            offreToUpdate.setDescr(offre.getDescr());
        }
        if(offre.getEtablissement() != null){
            offreToUpdate.setEtablissement(offre.getEtablissement());
        }

        return offreRepository.save(offreToUpdate);
    }

    public Boolean deleteOffre(Long id) {
        Boolean isDeleted = false;
        if(offreRepository.existsById(id)){
            offreRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public List<Offre> findAllOffresByEtablissement(Long id) {
        return offreRepository.findAll()
            .stream()
            .filter(offre -> offre.getEtablissement().getId_etablissement() == id)
            .toList();
    }
}
