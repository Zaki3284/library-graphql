package graphql.controller;

import graphql.Entites.LivreEntity;
import graphql.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LivreController {

    private final LivreService livreService;

    @QueryMapping
    public List<LivreEntity> allLivres() {
        return livreService.getAllLivres();
    }

    @QueryMapping
    public LivreEntity livreById(@Argument Long id) {
        return livreService.getLivreById(id);
    }

    @MutationMapping
    public LivreEntity createLivre(@Argument String name) {
        return livreService.createLivre(name);
    }

    @MutationMapping
    public LivreEntity updateLivre(@Argument Long id, @Argument String name) {
        return livreService.updateLivre(id, name);
    }

    @MutationMapping
    public Boolean deleteLivre(@Argument Long id) {
        return livreService.deleteLivre(id);
    }
}
