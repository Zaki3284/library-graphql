package graphql.controller;

import graphql.Entites.EtudiantEntity;
import graphql.service.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;

    @QueryMapping
    public List<EtudiantEntity> allEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @QueryMapping
    public EtudiantEntity etudiantById(@Argument Long id) {
        return etudiantService.getEtudiantById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID: " + id));
    }

    @MutationMapping
    public EtudiantEntity createEtudiant(@Argument String name, @Argument String email) {
        return etudiantService.createEtudiant(name, email);
    }

    @MutationMapping
    public EtudiantEntity updateEtudiant(@Argument Long id, @Argument String name, @Argument String email) {
        return etudiantService.updateEtudiant(id, name, email);
    }

    @MutationMapping
    public Boolean deleteEtudiant(@Argument Long id) {
        return etudiantService.deleteEtudiant(id);
    }
}