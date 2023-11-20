package fr.polytech.Recruteur.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "offre")
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
    private Date dateDebut;

    @Column(name = "dateFin")
    private Date dateFin;

    @Column(name = "salaire")
    private String salaire;

    @Column(name = "avantages")
    private String avantages;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_etablissement", referencedColumnName = "id_etablissement")
    private Etablissement etablissement;


    public Offre() { }

    public Offre(Long idOffre, String titre, String descr, String competences, Date dateDebut, Date dateFin, String salaire, String avantages, Etablissement etablissement) {
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

    public Long getIdOffre() { return idOffre; }

    public void setIdOffre(Long idOffre) { this.idOffre = idOffre; }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public Date getDateDebut() { return dateDebut;}

    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut;}

    public Date getDateFin() { return dateFin;}

    public void setDateFin(Date dateFin) { this.dateFin = dateFin;}

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getAvantages() {
        return avantages;
    }

    public void setAvantages(String avantages) {
        this.avantages = avantages;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
}
