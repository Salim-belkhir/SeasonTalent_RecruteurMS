package fr.polytech.Recruteur.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "adresse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "adresse_id_adresse_seq")
    @SequenceGenerator(name = "adresse_id_adresse_seq", sequenceName = "adresse_id_adresse_seq", allocationSize = 1)
    @Column(name = "idAdresse")
    private Long idAdresse;

    @Column(name = "numeroVoie")
    private Integer numeroVoie;

    @Column(name = "nomVoie")
    private String nomVoie;

    @Column(name = "codePostal", length = 5)
    private String codePostal;

    @Column(name = "ville")
    private String ville;


    @Override
    public String toString() {
        return "Adresse{" +
                "idAdresse=" + idAdresse +
                ", numeroVoie=" + numeroVoie +
                ", nomVoie='" + nomVoie + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

}