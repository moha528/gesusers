-- Schéma de la base gesusers
-- Exécuté automatiquement par MySQL au premier démarrage du conteneur
-- (placé dans /docker-entrypoint-initdb.d via docker-compose).

CREATE DATABASE IF NOT EXISTS gesusers
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE gesusers;

CREATE TABLE IF NOT EXISTS utilisateurs (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nom      VARCHAR(100) NOT NULL,
    prenom   VARCHAR(100) NOT NULL,
    login    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(10)  NOT NULL DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Quelques utilisateurs de démonstration
INSERT INTO utilisateurs (nom, prenom, login, password, role) VALUES
    ('Admin',  'Super',  'admin',   'admin',   'ADMIN'),
    ('Diop',   'Awa',    'adiop',   'pass123', 'USER'),
    ('Ndiaye', 'Moussa', 'mndiaye', 'pass123', 'USER'),
    ('Fall',   'Fatou',  'ffall',   'pass123', 'USER');
