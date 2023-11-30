package fr.polytech.Recruteur.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO : Voir ce qu'on fait pour la table "gerer" pour savoir qui gère un établissement
@Entity
@Table(name = "etablissement")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Etablissement {

    @Id
    @Column(name = "idEtablissement")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "etablissement_id_etablissement_seq")
    @SequenceGenerator(name = "etablissement_id_etablissement_seq", sequenceName = "etablissement_id_etablissement_seq", allocationSize = 1)
    private Long idEtablissement;

    @Column(name = "nomEtablissement")
    private String nomEtablissement;

    @Column(name = "logo")
    private String logo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAdresse", referencedColumnName = "idAdresse")
    private Adresse adresse;


    @Override
    public String toString() {
        return "Etablissement{" +
                "id_etablissement=" + idEtablissement +
                ", nom_etablissement='" + nomEtablissement + '\'' +
                ", logo='" + logo + '\'' +
                ", adresse=" + adresse +
                '}';
    }

}
