package fr.polytech.Recruteur.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "gerer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GestionEtab {
    @Id
    private Long idUtilisateur;
    @Id
    private Long idEtablissement;
}
