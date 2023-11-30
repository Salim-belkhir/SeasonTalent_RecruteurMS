package fr.polytech.Recruteur.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.time.LocalDate;

@Entity
@Table(name = "offre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offre {

    @Id
    @Column(name = "idOffre")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "offre_id_offre_seq")
    @SequenceGenerator(name = "offre_id_offre_seq", sequenceName = "offre_id_offre_seq", allocationSize = 1)
    private Long idOffre;

    @Column(name = "titre")
    private String titre;

    @Column(name = "descr")
    private String descr;

    @Column(name = "competences")
    private String competences;

    @Column(name = "dateDebut")
    private LocalDate dateDebut;

    @Column(name = "dateFin")
    private LocalDate dateFin;

    @Column(name = "salaire")
    private String salaire;

    @Column(name = "avantages")
    private String avantages;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEtablissement", referencedColumnName = "idEtablissement")
    private Etablissement etablissement;



    public Offre() { }

    public Offre(Long idOffre, String titre, String descr, String competences, LocalDate dateDebut, LocalDate dateFin, String salaire, String avantages, Etablissement etablissement) {
        this.idOffre = idOffre;
        this.titre = titre;
        this.descr = descr;
        this.competences = competences;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.salaire = salaire;
        this.avantages = avantages;
        this.etablissement = etablissement;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "idOffre=" + idOffre +

                ", titre='" + titre + '\'' +
                ", descr='" + descr + '\'' +
                ", competences='" + competences + '\'' +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", salaire='" + salaire + '\'' +
                ", avantages='" + avantages + '\'' +
                ", etablissement=" + etablissement +
                '}';
    }

}
