# Springboot_project_TestDrivenDevelopment
Springboot project TDD (Test Driven Development) concept client gestion B2C

## Contexte du projet

Afin de réaliser un plan de test et pour but de simplicité, on va réaliser une simple microservice permettant la gestion des clients d’une entreprise B2C.

Un client se caractérise par un :

 - identifiant (générer par une séquence) 
 - email (unique et non nul)
 - numéro de téléphone (unique, non null et valide pour un numéro de téléphone marocain)
 - nom complet (non null)
 - age (valide)
 - sex (valide, enumeration)
 - isActive (boolean, default true)
 

### Votre 1ʳᵉ mission est de préparer L’API REST qui expose les “endpoints” suivantes :

- “/clients/save” → Ajouter un client ou une liste de clients.
- “/clients/” → Sélectionner l’ensemble des clients (utilisée la pagination).
- “/clients/{id}” → Chercher un client par identifiant.
- “/clients/{email}” → Chercher un client par email.
- “/clients/all/{sex}” → Chercher l’ensemble des clients par sex.
- “/clients/{id}” → Supprimer un client (désactiver).
- “/clients/{id}” → Modifier un client.
- … vous pouvez ajouter d’autres “endpoints” (optional)

### Votre 2ᵉ mission est de préparer ainsi qu’exécuter les plans de tests de l’ensemble des couches/fonctionnalités de cette microservice.


    - “Controller Layer”
    - “Service Layer”
    - “Repository Layer”
    - All layers (end to end).

### Votre 3ᵉ mission serait un bonus (optional). Mise en place d’un pipeline CI/CD pour le but d’automatisation des tests.

#### Contraintes :

Utiliser Maven.

Utiliser les spring boot starters depuis https://start.spring.io/:

   - Spring Web : RESTful
   - Spring Data JPA
   - MySQL Driver | PostgeSQL Driver
   - Lombok: générer les POJOS.

Utiliser Junit5 et Mockito.

Utiliser GithubActions. (optional)

## Critères de performance

Utilisée l’approache “TDD” durant votre développement de l’application.

Un test unitaire doit respecter les normes suivantes :

   - Ne dois pas communiquer avec la base de données.
   - Ne dois pas communiquer dans le réseau.
   - Ne dois pas communiquer avec des fichiers stockés sur votre machine.
   - Ne dois pas s’exécuter en même temps que d’autres tests.
   - N'a pas besoin d’une configuration spécial pour s’exécuter.
   - Doit s’exécuter rapidement, sans avoir besoin de préparer une infrastructure pour fonctionner.

Un test d’intégration dans notre cas doit respecter les normes suivantes :

    Doit tester la totalité de l’application (end to end tests).
    
## Modalités d'évaluation

Vous serez amené à expliquer les notions suivantes :

     - La notion du “TDD” et son utilité.
     - Les différents types de tests logiciels existants.
     - Les critères d’un test unitaire correct.
     - Les critères d’un test d’intégration correct.
     - La notion du “Mocking” et son utilité. 
  


