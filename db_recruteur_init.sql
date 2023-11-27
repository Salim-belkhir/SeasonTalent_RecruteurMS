-- Création des tables pour le microservice Recruteur

DROP TABLE IF EXISTS offre;
DROP TABLE IF EXISTS gerer;
DROP TABLE IF EXISTS etablissement;
DROP TABLE IF EXISTS adresse;


CREATE TABLE adresse (
    idAdresse SERIAL PRIMARY KEY,
    numeroVoie INT,
    nomVoie varchar(255),
    codePostal varchar(5),
    ville varchar(255),
    pays varchar(255)
);

CREATE TABLE etablissement (
    idEtablissement SERIAL PRIMARY KEY,
    nomEtablissement varchar(255),
    logo varchar(255),
    idAdresse INT,
    FOREIGN KEY (idAdresse) REFERENCES adresse(idAdresse) ON DELETE SET NULL
);

CREATE TABLE gerer (
    idEtablissement INT,
    idUtilisateur INT,
    PRIMARY KEY (idEtablissement, idUtilisateur),
    FOREIGN KEY (idEtablissement) REFERENCES etablissement(idEtablissement) ON DELETE CASCADE
);

CREATE TABLE offre (
    idOffre SERIAL PRIMARY KEY,
    titre varchar(255),
    descr varchar(255),
    competences varchar(255),
    dateDebut DATE,
    dateFin DATE,
    salaire varchar(255),
    avantages varchar(255),
    idUtilisateur INT,
    idEtablissement INT,
    FOREIGN KEY (idEtablissement) REFERENCES etablissement(idEtablissement) ON DELETE SET NULL
);

-- Insertion de données dans les tables

INSERT INTO adresse (numeroVoie, nomVoie, codePostal, ville, pays)
VALUES
    (7, 'Place de la Comédie', '34000', 'Montpellier', 'France'),
    (4, 'Rue des Artisans', '34000', 'Montpellier', 'France'),
    (789, 'Boulevard Saint-Germain', '75007', 'Paris', 'France');

INSERT INTO etablissement (nomEtablissement, logo, idAdresse)
VALUES
    ('McDonald''s Comédie', 'mcdo.png', 1),
    ('Lidl Voltaire', 'lidl.png', 2),
    ('Etablissement C', 'logo_c.png', 3);

INSERT INTO gerer (idEtablissement, idUtilisateur)
VALUES
    (1, 2),
    (2, 2),
    (3, 3);

INSERT INTO offre (titre, descr, competences, dateDebut, dateFin, salaire, avantages, idUtilisateur, idEtablissement)
VALUES
    ('Second de cuisine', 'Accompagner l''équipe de cuisine', 'Dynamique', TO_DATE('01-01-2024', 'DD-MM-YYYY'), TO_DATE('01-03-2024', 'DD-MM-YYYY'), '1600 EUR', 'Hébergement', 2, 1),
    ('Offre 2', 'Description de l''offre 2', 'Compétences requises pour l''offre 2', TO_DATE('01-02-2023', 'DD-MM-YYYY'), TO_DATE('01-03-2023', 'DD-MM-YYYY'), '3500 EUR', 'Avantages de l''offre 2', 2, 2),
    ('Offre 3', 'Description de l''offre 3', 'Compétences requises pour l''offre 3', TO_DATE('01-03-2023', 'DD-MM-YYYY'), TO_DATE('01-04-2023', 'DD-MM-YYYY'), '4000 EUR', 'Avantages de l''offre 3', 3, 3);
