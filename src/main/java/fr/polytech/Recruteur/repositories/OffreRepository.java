package fr.polytech.Recruteur.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.Recruteur.models.Etablissement;
import fr.polytech.Recruteur.models.Offre;

public interface OffreRepository extends JpaRepository<Offre, Long> {
}
