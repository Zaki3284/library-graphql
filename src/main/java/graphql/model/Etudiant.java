package graphql.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "etudiants")
@Data @NoArgsConstructor @AllArgsConstructor
public class Etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
}
