package graphql.controller;

public class LivreController {

    /**
     * Controller GraphQL dédié à la gestion des Livres.
     *
     * Rôle :
     * - Déclarer les Query et Mutation GraphQL liées à l’entité Livre.
     * - Exposer les opérations permettant de récupérer, ajouter ou mettre à jour des livres.
     * - Utiliser @SchemaMapping pour résoudre les relations d’un Livre
     *   (ex : liste des emprunts associés).
     *
     * Importance dans l’architecture :
     * - C’est une couche d’exposition : aucune logique métier.
     * - Toutes les opérations sont déléguées au LivreService.
     * - Sert de point d’entrée GraphQL permettant au client de communiquer avec l’application.
     */

}
