package fr.polytech.Recruteur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.Recruteur.models.Etablissement;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
    
}
