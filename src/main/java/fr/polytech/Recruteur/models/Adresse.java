package fr.polytech.Recruteur.models;

import jakarta.persistence.*;

@Entity
@Table(name = "adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "adresse_id_adresse_seq")
    @SequenceGenerator(name = "adresse_id_adresse_seq", sequenceName = "adresse_id_adresse_seq", allocationSize = 1)
    @Column(name = "id_adresse")
    private Long id_adresse;

    @Column(name = "numero_voie")
    private Integer numero_voie;

    @Column(name = "nom_voie")
    private String nom_voie;

    @Column(name = "code_postal", length = 5)
    private String code_postal;

    @Column(name = "ville")
    private String ville;


    public Adresse(Long id_adresse, Integer numero_voie, String nom_voie, String code_postal, String ville) {
        this.id_adresse = id_adresse;
        this.numero_voie = numero_voie;
        this.nom_voie = nom_voie;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    public Adresse() {

    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id_adresse=" + id_adresse +
                ", numero_voie=" + numero_voie +
                ", nom_voie='" + nom_voie + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

    public Long getId_adresse() {
        return id_adresse;
    }

    public void setId_adresse(Long id_adresse) {
        this.id_adresse = id_adresse;
    }

    public Integer getNumero_voie() {
        return numero_voie;
    }

    public void setNumero_voie(Integer numero_voie) {
        this.numero_voie = numero_voie;
    }

    public String getNom_voie() {
        return nom_voie;
    }

    public void setNom_voie(String nom_voie) {
        this.nom_voie = nom_voie;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}