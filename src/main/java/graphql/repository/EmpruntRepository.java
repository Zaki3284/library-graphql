package graphql.repository;

import graphql.Entites.EmpruntEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<EmpruntEntity, Long> {

    List<EmpruntEntity> findByEtudiantId(Long etudiantId);

    List<EmpruntEntity> findByLivreId(Long livreId);

    List<EmpruntEntity> findByRetourne(Boolean retourne);

    boolean existsByLivreIdAndRetourneFalse(Long livreId);
}