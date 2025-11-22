package graphql.service;

import graphql.Entites.EmpruntEntity;
import graphql.Entites.EtudiantEntity;
import graphql.Entites.LivreEntity;
import graphql.repository.EmpruntRepository;
import graphql.repository.EtudiantRepository;
import graphql.repository.LivreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final EtudiantRepository etudiantRepository;
    private final LivreRepository livreRepository;

    public List<EmpruntEntity> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public EmpruntEntity getEmpruntById(Long id) {
        return empruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé avec l'ID: " + id));
    }

    public List<EmpruntEntity> getEmpruntsByEtudiant(Long etudiantId) {
        return empruntRepository.findByEtudiantId(etudiantId);
    }

    public List<EmpruntEntity> getEmpruntsActifs() {
        return empruntRepository.findByRetourne(false);
    }

    @Transactional
    public EmpruntEntity createEmprunt(Long etudiantId, Long livreId, Integer joursEmprunt) {
        EtudiantEntity etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID: " + etudiantId));

        LivreEntity livre = livreRepository.findById(livreId)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'ID: " + livreId));

        if (empruntRepository.existsByLivreIdAndRetourneFalse(livreId)) {
            throw new RuntimeException("Ce livre est déjà emprunté");
        }

        LocalDate dateEmprunt = LocalDate.now();
        LocalDate dateRetour = dateEmprunt.plusDays(joursEmprunt != null ? joursEmprunt : 14);

        EmpruntEntity emprunt = EmpruntEntity.builder()
                .etudiant(etudiant)
                .livre(livre)
                .dateEmprunt(dateEmprunt)
                .dateRetour(dateRetour)
                .retourne(false)
                .build();

        return empruntRepository.save(emprunt);
    }

    @Transactional
    public EmpruntEntity retournerLivre(Long empruntId) {
        EmpruntEntity emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé avec l'ID: " + empruntId));

        if (emprunt.getRetourne()) {
            throw new RuntimeException("Ce livre a déjà été retourné");
        }

        emprunt.setRetourne(true);
        return empruntRepository.save(emprunt);
    }

    public Boolean deleteEmprunt(Long id) {
        if (empruntRepository.existsById(id)) {
            empruntRepository.deleteById(id);
            return true;
        }
        return false;
    }
}