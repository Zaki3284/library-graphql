package graphql.service;

import graphql.Entites.EtudiantEntity;
import graphql.repository.EtudiantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Required for Mockito annotations
class LibraryServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    void testInscrireEtudiant() {
        // Arrange
        EtudiantEntity etudiant = new EtudiantEntity(null, "Jean", "jean@test.com", null);
        when(etudiantRepository.existsByEmail("jean@test.com")).thenReturn(false);
        when(etudiantRepository.save(any(EtudiantEntity.class))).thenReturn(etudiant);

        // Act
        EtudiantEntity result = libraryService.inscrireEtudiant("Jean", "jean@test.com");

        // Assert
        assertEquals("Jean", result.getNom());
        assertEquals("jean@test.com", result.getEmail());

        // Verify interactions
        verify(etudiantRepository).existsByEmail("jean@test.com");
        verify(etudiantRepository).save(any(EtudiantEntity.class));
    }
}
