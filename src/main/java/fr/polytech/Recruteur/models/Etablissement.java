package fr.polytech.Recruteur.models;


import jakarta.persistence.*;

//TODO : Voir ce qu'on fait pour la table "gerer" pour savoir qui gère un établissement
@Entity
@Table(name = "etablissement")
public class Etablissement {

    @Id
    @Column(name = "id_etablissement")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "etablissement_id_etablissement_seq")
    @SequenceGenerator(name = "etablissement_id_etablissement_seq", sequenceName = "etablissement_id_etablissement_seq", allocationSize = 1)
    private Long id_etablissement;

    @Column(name = "nom_etablissement")
    private String nom_etablissement;

    @Column(name = "logo")
    private String logo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse")
    private Adresse adresse;


    public Etablissement(Long id_etablissement, String nom_etablissement, String logo, Adresse adresse) {
        this.id_etablissement = id_etablissement;
        this.nom_etablissement = nom_etablissement;
        this.logo = logo;
        this.adresse = adresse;
    }

    public Etablissement() {

    }

    @Override
    public String toString() {
        return "Etablissement{" +
                "id_etablissement=" + id_etablissement +
                ", nom_etablissement='" + nom_etablissement + '\'' +
                ", logo='" + logo + '\'' +
                ", adresse=" + adresse +
                '}';
    }

    public Long getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(Long id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    public String getNom_etablissement() {
        return nom_etablissement;
    }

    public void setNom_etablissement(String nom_etablissement) {
        this.nom_etablissement = nom_etablissement;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
