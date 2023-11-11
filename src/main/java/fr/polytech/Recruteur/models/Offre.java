package fr.polytech.Recruteur.models;


import jakarta.persistence.*;

@Entity
@Table(name = "offre")
public class Offre {

    @Id
    @Column(name = "id_offre")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "offre_id_offre_seq")
    @SequenceGenerator(name = "offre_id_offre_seq", sequenceName = "offre_id_offre_seq", allocationSize = 1)
    private Long id_offre;

    @Column(name = "titre")
    private String titre;

    @Column(name = "descr")
    private String descr;

    @Column(name = "competences")
    private String competences;

    @Column(name = "periode")
    private String periode;

    @Column(name = "salaire")
    private String salaire;

    @Column(name = "avantages")
    private String avantages;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_etablissement", referencedColumnName = "id_etablissement")
    private Etablissement etablissement;


    public Offre() { }

    public Offre(Long id_offre, String titre, String descr, String competences, String periode, String salaire, String avantages, Etablissement etablissement) {
        this.id_offre = id_offre;
        this.titre = titre;
        this.descr = descr;
        this.competences = competences;
        this.periode = periode;
        this.salaire = salaire;
        this.avantages = avantages;
        this.etablissement = etablissement;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "id_offre=" + id_offre +
                ", titre='" + titre + '\'' +
                ", descr='" + descr + '\'' +
                ", competences='" + competences + '\'' +
                ", periode='" + periode + '\'' +
                ", salaire='" + salaire + '\'' +
                ", avantages='" + avantages + '\'' +
                ", etablissement=" + etablissement +
                '}';
    }

    public Long getId_offre() {
        return id_offre;
    }

    public void setId_offre(Long id_offre) {
        this.id_offre = id_offre;
    }

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

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

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
