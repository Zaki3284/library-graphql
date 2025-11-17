package model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "livres")
@Data @NoArgsConstructor @AllArgsConstructor
public class Livre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String auteur;
    private String isbn;
    private boolean disponible = true; // available
}
