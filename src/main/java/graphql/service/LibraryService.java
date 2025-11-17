package graphql.service;



public class LibraryService {

    /**
     * Service principal contenant la logique métier de la bibliothèque.
     *
     * Rôle :
     * - Gérer l’ajout de livres.
     * - Inscrire les étudiants.
     * - Gérer un emprunt : vérifier disponibilité, enregistrer emprunt, mettre à jour le statut du livre.
     * - Fournir les données aux résolveurs GraphQL.
     *
     * Importance :
     * - C’est la couche “business logic".
     * - Le controller GraphQL ne fait qu'appeler ce service.
     * - Permet de centraliser toute la logique métier pour garder une architecture propre.
     */

}
