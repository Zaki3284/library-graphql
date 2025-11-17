package graphql.service;

import graphql.Entites.EtudiantEntity;
import graphql.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final EtudiantRepository etudiantRepository;

    // Récupérer tous les étudiants
    public List<EtudiantEntity> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    // Récupérer un étudiant par ID
    public Optional<EtudiantEntity> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    // Créer un nouvel étudiant
    public EtudiantEntity createEtudiant(String name, String email) {
        if (etudiantRepository.existsByEmail(email)) {
            throw new RuntimeException("Un étudiant avec cet email existe déjà");
        }

        EtudiantEntity etudiant = EtudiantEntity.builder()
                .name(name)  // Utiliser 'name' au lieu de 'nom'
                .email(email)
                .build();

        return etudiantRepository.save(etudiant);
    }

    // Mettre à jour un étudiant
    public EtudiantEntity updateEtudiant(Long id, String name, String email) {
        EtudiantEntity etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID: " + id));

        // Utiliser 'name' au lieu de 'nom'
        etudiant.setName(name);
        etudiant.setEmail(email);

        return etudiantRepository.save(etudiant);
    }

    // Supprimer un étudiant
    public boolean deleteEtudiant(Long id) {
        if (etudiantRepository.existsById(id)) {
            etudiantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Rechercher par email
    public Optional<EtudiantEntity> findByEmail(String email) {
        return etudiantRepository.findByEmail(email);
    }
}