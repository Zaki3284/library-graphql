package graphql.service;

import graphql.Entites.EmpruntEntity;
import graphql.Entites.EtudiantEntity;
import graphql.Entites.LivreEntity;
import graphql.repository.EmpruntRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final EtudiantService etudiantService;
    private final LivreService livreService;

    public List<EmpruntEntity> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Optional<EmpruntEntity> getEmpruntById(Long id) {
        return empruntRepository.findById(id);
    }

    public List<EmpruntEntity> getEmpruntsByEtudiantId(Long etudiantId) {
        return empruntRepository.findByEtudiantId(etudiantId);
    }

    @Transactional
    public EmpruntEntity createEmprunt(Long etudiantId, Long livreId, LocalDateTime dateRetourPrevue) {
        EtudiantEntity etu = etudiantService.getEtudiantById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID: " + etudiantId));

        LivreEntity livre = livreService.getLivreById(livreId)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'ID: " + livreId));

        if (Boolean.FALSE.equals(livre.getAvailable())) {
            throw new RuntimeException("Le livre n'est pas disponible pour emprunt.");
        }

        livreService.markAsBorrowed(livre);

        EmpruntEntity emprunt = EmpruntEntity.builder()
                .etudiant(etu)
                .livre(livre)
                .dateEmprunt(LocalDateTime.now())
                .dateRetourPrevue(dateRetourPrevue)
                .build();

        return empruntRepository.save(emprunt);
    }

    @Transactional
    public EmpruntEntity markReturned(Long empruntId, LocalDateTime returnedAt) {
        EmpruntEntity emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé avec l'ID: " + empruntId));

        if (emprunt.getDateRetour() != null) {
            throw new RuntimeException("Emprunt déjà retourné.");
        }

        emprunt.setDateRetour(returnedAt == null ? LocalDateTime.now() : returnedAt);

        LivreEntity livre = emprunt.getLivre();
        livreService.markAsReturned(livre);

        return empruntRepository.save(emprunt);
    }

    @Transactional
    public EmpruntEntity updateEmpruntDates(Long empruntId, LocalDateTime newDateRetourPrevue) {
        EmpruntEntity emprunt = empruntRepository.findById(empruntId)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé avec l'ID: " + empruntId));
        emprunt.setDateRetourPrevue(newDateRetourPrevue);
        return empruntRepository.save(emprunt);
    }

    @Transactional
    public boolean deleteEmprunt(Long id) {
        if (!empruntRepository.existsById(id)) return false;
        empruntRepository.deleteById(id);
        return true;
    }
}
