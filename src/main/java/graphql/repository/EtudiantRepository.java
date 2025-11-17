package graphql.repository;

import graphql.Entites.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<EtudiantEntity, Long> {
    Optional<EtudiantEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}