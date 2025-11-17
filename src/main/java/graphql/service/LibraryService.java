package service;

import model.*;
import repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final EtudiantRepository etudiantRepo;
    private final LivreRepository livreRepo;
    private final EmpruntRepository empruntRepo;

    public LibraryService(EtudiantRepository e, LivreRepository l, EmpruntRepository em) {
        this.etudiantRepo = e;
        this.livreRepo = l;
        this.empruntRepo = em;
    }

    public List<Etudiant> getAllEtudiants() { return etudiantRepo.findAll(); }
    public List<Livre> getAllLivres() { return livreRepo.findAll(); }
    public List<Emprunt> getAllEmprunts() { return empruntRepo.findAll(); }

    public Optional<Etudiant> getEtudiant(Long id) { return etudiantRepo.findById(id); }
    public Optional<Livre> getLivre(Long id) { return livreRepo.findById(id); }

    @Transactional
    public Emprunt emprunter(Long etudiantId, Long livreId) {
        Etudiant e = etudiantRepo.findById(etudiantId).orElseThrow(() -> new RuntimeException("Etudiant not found"));
        Livre l = livreRepo.findById(livreId).orElseThrow(() -> new RuntimeException("Livre not found"));
        if (!l.isDisponible()) throw new RuntimeException("Livre non disponible");
        l.setDisponible(false);
        livreRepo.save(l);
        Emprunt em = new Emprunt();
        em.setEtudiant(e);
        em.setLivre(l);
        em.setDateEmprunt(LocalDate.now());
        return empruntRepo.save(em);
    }

    @Transactional
    public Emprunt retourner(Long empruntId) {
        Emprunt em = empruntRepo.findById(empruntId).orElseThrow(() -> new RuntimeException("Emprunt not found"));
        em.setDateRetour(LocalDate.now());
        Livre l = em.getLivre();
        l.setDisponible(true);
        livreRepo.save(l);
        return empruntRepo.save(em);
    }
}
