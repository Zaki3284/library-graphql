package graphql.controller;

import graphql.model.Emprunt;
import graphql.model.Etudiant;
import graphql.model.Livre;
import graphql.repository.EmpruntRepository;
import graphql.repository.EtudiantRepository;
import graphql.repository.LivreRepository;
import graphql.service.LibraryService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class LibaryGraphQLController {

    private final LibraryService libraryService;
    private final EtudiantRepository etudiantRepo;
    private final LivreRepository livreRepo;
    private final EmpruntRepository empruntRepo;

    public LibaryGraphQLController(LibraryService s, EtudiantRepository er, LivreRepository lr, EmpruntRepository emr) {
        this.libraryService = s;
        this.etudiantRepo = er;
        this.livreRepo = lr;
        this.empruntRepo = emr;
    }

    // Queries
    @QueryMapping
    public List<Etudiant> etudiants() { return libraryService.getAllEtudiants(); }

    @QueryMapping
    public Etudiant etudiant(@Argument Long id) {
        return libraryService.getEtudiant(id).orElse(null);
    }

    @QueryMapping
    public List<Livre> livres() { return libraryService.getAllLivres(); }

    @QueryMapping
    public Livre livre(@Argument Long id) {
        return libraryService.getLivre(id).orElse(null);
    }

    @QueryMapping
    public List<Emprunt> emprunts() { return libraryService.getAllEmprunts(); }

    @QueryMapping
    public List<Emprunt> empruntsByEtudiant(@Argument Long etudiantId) {
        return empruntRepo.findByEtudiantId(etudiantId);
    }

    // Mutations
    @MutationMapping
    public Etudiant createEtudiant(@Argument String nom, @Argument String prenom, @Argument String email) {
        Etudiant e = new Etudiant();
        e.setNom(nom); e.setPrenom(prenom); e.setEmail(email);
        return etudiantRepo.save(e);
    }

    @MutationMapping
    public Livre createLivre(@Argument String titre, @Argument String auteur, @Argument String isbn) {
        Livre l = new Livre();
        l.setTitre(titre); l.setAuteur(auteur); l.setIsbn(isbn); l.setDisponible(true);
        return livreRepo.save(l);
    }

    @MutationMapping
    public Emprunt emprunter(@Argument Long etudiantId, @Argument Long livreId) {
        return libraryService.emprunter(etudiantId, livreId);
    }

    @MutationMapping
    public Emprunt retourner(@Argument Long empruntId) {
        return libraryService.retourner(empruntId);
    }
}
