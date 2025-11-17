package graphql.repository;

import graphql.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByEtudiantId(Long etudiantId);
    List<Emprunt> findByLivreId(Long livreId);
}
