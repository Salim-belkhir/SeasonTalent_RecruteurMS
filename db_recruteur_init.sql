-- Création des tables pour le microservice Recruteur

DROP TABLE IF EXISTS offre;
DROP TABLE IF EXISTS gerer;
DROP TABLE IF EXISTS etablissement;
DROP TABLE IF EXISTS adresse;


CREATE TABLE adresse (
    id_adresse SERIAL PRIMARY KEY,
    numero_voie INT,
    nom_voie varchar(255),
    code_postal varchar(5),
    ville varchar(255),
    pays varchar(255)
);

CREATE TABLE etablissement (
    id_etablissement SERIAL PRIMARY KEY,
    nom_etablissement varchar(255),
    logo varchar(255),
    id_adresse INT,
    FOREIGN KEY (id_adresse) REFERENCES adresse(id_adresse)
);

CREATE TABLE gerer (
    id_etablissement INT,
    id_utilisateur INT,
    PRIMARY KEY (id_etablissement, id_utilisateur),
    FOREIGN KEY (id_etablissement) REFERENCES etablissement(id_etablissement)
);

CREATE TABLE offre (
    id_offre SERIAL PRIMARY KEY,
    titre varchar(255),
    descr varchar(255),
    competences varchar(255),
    periode varchar(255),
    salaire varchar(255),
    avantages varchar(255),
    id_utilisateur INT,
    id_etablissement INT,
    FOREIGN KEY (id_etablissement) REFERENCES etablissement(id_etablissement)
);

-- Insertion de données dans les tables

INSERT INTO adresse (numero_voie, nom_voie, code_postal, ville, pays)
VALUES
    (7, 'Place de la Comédie', '34000', 'Montpellier', 'France'),
    (4, 'Rue des Artisans', '34000', 'Montpellier', 'France'),
    (789, 'Boulevard Saint-Germain', '75007', 'Paris', 'France');

INSERT INTO etablissement (nom_etablissement, logo, id_adresse)
VALUES
    ('McDonald''s Comédie', 'mcdo.png', 1),
    ('Lidl Voltaire', 'lidl.png', 2),
    ('Etablissement C', 'logo_c.png', 3);

INSERT INTO gerer (id_etablissement, id_utilisateur)
VALUES
    (1, 2),
    (2, 2),
    (3, 3);

INSERT INTO offre (titre, descr, competences, periode, salaire, avantages, id_utilisateur, id_etablissement)
VALUES
    ('Second de cuisine', 'Accompagner l''équipe de cuisine', 'Dynamique', 'Janvier 2024 - Mars 2024', '1600 EUR', 'Hébergement', 2, 1),
    ('Offre 2', 'Description de l''offre 2', 'Compétences requises pour l''offre 2', 'Février 2023 - Mars 2023', '3500 EUR', 'Avantages de l''offre 2', 2, 2),
    ('Offre 3', 'Description de l''offre 3', 'Compétences requises pour l''offre 3', 'Mars 2023 - Avril 2023', '4000 EUR', 'Avantages de l''offre 3', 3, 3);