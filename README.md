# gesusers

Application web Jakarta EE de gestion d'utilisateurs : CRUD complet,
authentification par session et gestion de rôles (ADMIN / USER).

Développée en Java 17 avec les Servlets, les JSP et JDBC, sans aucun
framework, afin de mettre en évidence les fondamentaux de la plateforme
Jakarta EE.

## Stack technique

- Java 17
- Jakarta Servlet + JSP + Filter
- JDBC (mysql-connector-j 8.4.0)
- MySQL 8.4 (conteneur Docker)
- Apache Tomcat 11
- Eclipse (Dynamic Web Project)

## Architecture

```
beans/          → Utilisateur (POJO)
dao/            → UtilisateurDao, DBConnection (JDBC)
servlets/       → LoginServlet, LogoutServlet, HomeServlet,
                  AddUser, ListUser, UpdateUser, RemoveUser
filters/        → AuthFilter (protection des routes)
webapp/WEB-INF/ → JSPs (login, accueil, navbar, CRUD)
webapp/css/     → feuille de style
db/init.sql     → schéma + données de démo
```

- **Présentation** : JSPs sous `/WEB-INF/` (non accessibles directement),
  atteintes par `RequestDispatcher.forward`. Un fragment `navbar.jsp` est
  inclus dans toutes les pages authentifiées.
- **Contrôle** : servlets annotés `@WebServlet`.
- **Sécurité** : un `AuthFilter` intercepte toutes les requêtes. Il
  redirige vers `/login` si la session est absente et réserve les pages
  `/list`, `/add`, `/update`, `/delete` aux utilisateurs de rôle ADMIN.
- **Données** : DAO `UtilisateurDao` avec des méthodes statiques et un
  helper `DBConnection` qui lit les paramètres depuis les variables
  d'environnement (`DB_URL`, `DB_USER`, `DB_PASSWORD`) avec un fallback
  sur les valeurs par défaut de `docker-compose.yml`.

## Prérequis

- JDK 17
- Apache Tomcat 11
- Docker (pour la base MySQL)
- Eclipse IDE for Enterprise Java and Web Developers

## Démarrage

1. Lancer la base de données :

   ```bash
   docker compose up -d
   ```

   Le schéma et les données de démonstration sont chargés automatiquement
   depuis `db/init.sql` au premier démarrage du conteneur.

2. Importer le projet dans Eclipse :
   `File → Import → General → Existing Projects into Workspace`, puis
   sélectionner ce dossier.

3. Vérifier qu'Apache Tomcat v11 est configuré dans
   `Window → Preferences → Server → Runtime Environments`.

4. Clic droit sur le projet → `Run As → Run on Server` (Tomcat 11).

5. Ouvrir <http://localhost:8080/gesusers/> — l'application redirige
   automatiquement vers la page de connexion.

## Comptes de démonstration

| Login     | Password  | Rôle  |
|-----------|-----------|-------|
| `admin`   | `admin`   | ADMIN |
| `adiop`   | `pass123` | USER  |
| `mndiaye` | `pass123` | USER  |
| `ffall`   | `pass123` | USER  |

- Un utilisateur **USER** ne voit que sa page d'accueil avec ses propres
  informations.
- Un utilisateur **ADMIN** a en plus accès au listing et aux formulaires
  d'ajout / modification / suppression.

## Schéma de la base

Table unique `utilisateurs` :

| Colonne  | Type         | Contrainte             |
|----------|--------------|------------------------|
| id       | INT          | PK, AUTO_INCREMENT     |
| nom      | VARCHAR(100) | NOT NULL               |
| prenom   | VARCHAR(100) | NOT NULL               |
| login    | VARCHAR(100) | NOT NULL, UNIQUE       |
| password | VARCHAR(255) | NOT NULL               |
| role     | VARCHAR(10)  | NOT NULL, DEFAULT USER |

## Routes

| Méthode | URL        | Accès | Description                       |
|---------|------------|-------|-----------------------------------|
| GET     | `/login`   | Tous  | Formulaire de connexion           |
| POST    | `/login`   | Tous  | Authentification                  |
| GET     | `/logout`  | Tous  | Déconnexion                       |
| GET     | `/home`    | Auth  | Page d'accueil de l'utilisateur   |
| GET     | `/list`    | ADMIN | Liste des utilisateurs            |
| GET     | `/add`     | ADMIN | Formulaire d'ajout                |
| POST    | `/add`     | ADMIN | Création d'un utilisateur         |
| GET     | `/update`  | ADMIN | Formulaire de modification        |
| POST    | `/update`  | ADMIN | Mise à jour                       |
| GET     | `/delete`  | ADMIN | Suppression                       |
