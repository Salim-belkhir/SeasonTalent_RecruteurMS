package fr.polytech.Recruteur.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.polytech.Recruteur.repositories.EtablissementRepository;

@Service
public class EtablissementService {
    
    @Autowired
    private EtablissementRepository etablissementRepository;
}
