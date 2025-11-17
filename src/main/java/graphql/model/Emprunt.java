package graphql.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprunts")
@Data @NoArgsConstructor @AllArgsConstructor
public class Emprunt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "livre_id")
    private Livre livre;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour; // nullable
}
