package graphql.repository;

import graphql.Entites.LivreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<LivreEntity, Long> {

}
