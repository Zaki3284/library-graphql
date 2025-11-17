package graphql.controller;

import graphql.Entites.EtudiantEntity;
import graphql.Entites.LivreEntity;
import graphql.Entites.EmpruntEntity;
import graphql.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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
 * - C'est le point d'entrée GraphQL du projet.
 * - Il reçoit les requêtes GraphQL, transmet au service, et renvoie une réponse structurée.
 */

@Controller
@RequiredArgsConstructor
public class LibraryGraphQLController {

    private final LibraryService libraryService;

    // ==================== QUERIES (Lecture de données) ====================

    /**
     * Récupérer tous les étudiants.
     *
     * GraphQL Query :
     * query {
     *   etudiants {
     *     id
     *     nom
     *     email
     *   }
     * }
     */
    @QueryMapping
    public List<EtudiantEntity> etudiants() {
        return libraryService.getAllEtudiants();
    }

    /**
     * Récupérer un étudiant par son ID.
     *
     * GraphQL Query :
     * query {
     *   etudiant(id: 1) {
     *     id
     *     nom
     *     email
     *     emprunts {
     *       id
     *     }
     *   }
     * }
     */
    @QueryMapping
    public EtudiantEntity etudiant(@Argument Long id) {
        return libraryService.getEtudiantById(id);
    }

    /**
     * Récupérer tous les livres.
     *
     * TODO: Implémenter une fois LivreEntity et les méthodes du service sont prêtes
     *
     * GraphQL Query :
     * query {
     *   livres {
     *     id
     *     titre
     *     auteur
     *     disponible
     *   }
     * }
     */
//    @QueryMapping
//    public List<LivreEntity> livres() {
//        // TODO: Implémenter
//        return libraryService.getAllLivres();
//    }

    /**
     * Récupérer tous les emprunts en cours.
     *
     * TODO: Implémenter
     *
     * GraphQL Query :
     * query {
     *   empruntsEnCours {
     *     id
     *     dateEmprunt
     *     etudiant { nom }
     *     livre { titre }
     *   }
     * }
     */
//    @QueryMapping
//    public List<EmpruntEntity> empruntsEnCours() {
//        // TODO: Implémenter
//        return libraryService.getEmpruntsEnCours();
    }

    // ==================== MUTATIONS (Modification de données) ====================

    /**
     * Inscrire un nouvel étudiant.
     *
     * GraphQL Mutation :
     * mutation {
     *   inscrireEtudiant(nom: "Jean Dupont", email: "jean@example.com") {
     *     id
     *     nom
     *     email
     *   }
     * }
     */
    @MutationMapping
    public EtudiantEntity inscrireEtudiant(@Argument String nom, @Argument String email) {
        return libraryService.inscrireEtudiant(nom, email);
    }

    /**
     * Ajouter un nouveau livre au catalogue.
     *
     * TODO: Implémenter une fois LivreEntity est prêt
     *
     * GraphQL Mutation :
     * mutation {
     *   ajouterLivre(titre: "1984", auteur: "George Orwell", isbn: "123456") {
     *     id
     *     titre
     *     auteur
     *   }
     * }
     */


    /**
     * Créer un nouvel emprunt (emprunter un livre).
     *
     * TODO: Implémenter
     *
     * GraphQL Mutation :
     * mutation {
     *   emprunterLivre(etudiantId: 1, livreId: 2) {
     *     id
     *     dateEmprunt
     *     etudiant { nom }
     *     livre { titre }
     *   }
     * }
     */
    @MutationMapping


    /**
     * Retourner un livre (terminer un emprunt).
     *
     * TODO: Implémenter
     *
     * GraphQL Mutation :
     * mutation {
     *   retournerLivre(empruntId: 1) {
     *     id
     *     dateRetour
     *     livre { disponible }
     *   }
     * }
     */
    @MutationMapping
    public EmpruntEntity retournerLivre(@Argument Long empruntId) {
        // TODO: Implémenter
        return libraryService.retournerLivre(empruntId);
    }
}
