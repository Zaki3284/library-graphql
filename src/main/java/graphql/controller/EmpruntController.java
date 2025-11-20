package graphql.controller;

import graphql.Entites.EmpruntEntity;
import graphql.service.EmpruntService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
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
        return empruntService.getEmpruntById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé avec l'ID: " + id));
    }

    @QueryMapping
    public List<EmpruntEntity> empruntsByEtudiant(@Argument Long etudiantId) {
        return empruntService.getEmpruntsByEtudiantId(etudiantId);
    }

    @MutationMapping
    public EmpruntEntity createEmprunt(
            @Argument Long etudiantId,
            @Argument Long livreId,
            @Argument String dateRetourPrevue // accept ISO string, convert below
    ) {
        LocalDateTime retourPrevue = dateRetourPrevue == null ? null : LocalDateTime.parse(dateRetourPrevue);
        return empruntService.createEmprunt(etudiantId, livreId, retourPrevue);
    }

    @MutationMapping
    public EmpruntEntity returnEmprunt(@Argument Long empruntId, @Argument String returnedAt) {
        LocalDateTime returned = returnedAt == null ? null : LocalDateTime.parse(returnedAt);
        return empruntService.markReturned(empruntId, returned);
    }

    @MutationMapping
    public EmpruntEntity updateEmprunt(@Argument Long empruntId, @Argument String newDateRetourPrevue) {
        LocalDateTime retourPrevue = newDateRetourPrevue == null ? null : LocalDateTime.parse(newDateRetourPrevue);
        return empruntService.updateEmpruntDates(empruntId, retourPrevue);
    }

    @MutationMapping
    public Boolean deleteEmprunt(@Argument Long id) {
        return empruntService.deleteEmprunt(id);
    }
}
