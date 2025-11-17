package graphql.Entité;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entité Etudiant représentant un utilisateur de la bibliothèque.
 *
 * Attributs :
 * - id : identifiant unique
 * - nom : nom complet de l'étudiant
 * - email : adresse email
 *
 * Importance :
 * - Sert pour l'inscription et la gestion des emprunts dans le système.
 * - Représenté également comme type dans GraphQL.
 */

@Entity
@Table(name = "etudiants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    // Relation OneToMany avec EmpruntEntity
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmpruntEntity> emprunts;
}
