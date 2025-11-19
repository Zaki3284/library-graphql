package graphql.service;

import graphql.Entites.LivreEntity;
import graphql.repository.LivreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivreService {

    private final LivreRepository livreRepository;

    public List<LivreEntity> getAllLivres() {
        return livreRepository.findAll();
    }

    public LivreEntity getLivreById(Long id) {
        return livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'ID: " + id));
    }

    public LivreEntity createLivre(String name) {
        LivreEntity livre = LivreEntity.builder()
                .name(name)
                .build();
        return livreRepository.save(livre);
    }

    public LivreEntity updateLivre(Long id, String name) {
        LivreEntity livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'ID: " + id));

        livre.setName(name);

        return livreRepository.save(livre);
    }

    public Boolean deleteLivre(Long id) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
