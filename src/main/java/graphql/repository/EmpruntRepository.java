package graphql.repository;

import graphql.Entites.EmpruntEntity;
import graphql.Entites.EtudiantEntity;
import graphql.Entites.LivreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<EmpruntEntity, Long> {
    List<EmpruntEntity> findByEtudiantId(Long etudiantId);
    List<EmpruntEntity> findByLivreId(Long livreId);
    List<EmpruntEntity> findByEtudiant(EtudiantEntity etudiant);
    List<EmpruntEntity> findByLivre(LivreEntity livre);
}
