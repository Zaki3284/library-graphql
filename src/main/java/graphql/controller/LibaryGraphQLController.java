package graphql.controller;


import org.springframework.stereotype.Controller;



public class LibaryGraphQLController {

/**
 * Controller GraphQL de la bibliothèque.
 *
 * Cette classe expose les endpoints GraphQL (Query, Mutation)
 * permettant d'interagir avec les données du domaine : Livre, Étudiant, Emprunt.
 *
 * Rôle principal :
 * - Définir les résolveurs GraphQL pour les requêtes (getLivre, getEtudiant, getEmprunts…)
 * - Définir les mutations (ajouterLivre, inscrireEtudiant, emprunterLivre…)
 * - Appeler le service LibraryService qui contient la logique métier.
 *
 * Importance :
 * - C’est le point d'entrée GraphQL du projet.
 * - Il reçoit les requêtes GraphQL, transmet au service, et renvoie une réponse structurée.
 */

}
