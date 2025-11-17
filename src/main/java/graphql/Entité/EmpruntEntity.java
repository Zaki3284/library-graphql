package graphql.Entité;



public class EmpruntEntity {

    /**
     * Entité Emprunt représentant l'opération d'emprunt d’un livre par un étudiant.
     *
     * Attributs :
     * - id : identifiant unique
     * - etudiant : étudiant ayant emprunté le livre (relation ManyToOne)
     * - livre : livre emprunté (relation ManyToOne)
     * - dateEmprunt : date de début de l’emprunt
     * - dateRetour : date prévue de retour
     *
     * Importance :
     * - Relation clé entre Livre et Etudiant.
     * - Permet de suivre l'historique des emprunts.
     */

}
