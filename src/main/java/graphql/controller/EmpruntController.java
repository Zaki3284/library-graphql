package graphql.controller;

import graphql.Entites.EmpruntEntity;
import graphql.Entites.EtudiantEntity;
import graphql.Entites.LivreEntity;
import graphql.service.EmpruntService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmpruntController {

    private final EmpruntService empruntService;

    @QueryMapping
    public List<EmpruntEntity> allEmprunts() {
        return empruntService.getAllEmprunts();
    }

    @QueryMapping
    public EmpruntEntity empruntById(@Argument Long id) {
        return empruntService.getEmpruntById(id);
    }

    @QueryMapping
    public List<EmpruntEntity> empruntsByEtudiant(@Argument Long etudiantId) {
        return empruntService.getEmpruntsByEtudiant(etudiantId);
    }

    @QueryMapping
    public List<EmpruntEntity> empruntsActifs() {
        return empruntService.getEmpruntsActifs();
    }

    @MutationMapping
    public EmpruntEntity createEmprunt(
            @Argument Long etudiantId,
            @Argument Long livreId,
            @Argument Integer joursEmprunt) {
        return empruntService.createEmprunt(etudiantId, livreId, joursEmprunt);
    }

    @MutationMapping
    public EmpruntEntity retournerLivre(@Argument Long empruntId) {
        return empruntService.retournerLivre(empruntId);
    }

    @MutationMapping
    public Boolean deleteEmprunt(@Argument Long id) {
        return empruntService.deleteEmprunt(id);
    }

    @SchemaMapping(typeName = "Emprunt", field = "etudiant")
    public EtudiantEntity getEtudiant(EmpruntEntity emprunt) {
        return emprunt.getEtudiant();
    }

    @SchemaMapping(typeName = "Emprunt", field = "livre")
    public LivreEntity getLivre(EmpruntEntity emprunt) {
        return emprunt.getLivre();
    }
}