package graphql.service;

import graphql.Entites.EtudiantEntity;
import graphql.repository.EtudiantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private LibraryService libraryService;

    private EtudiantEntity etudiant;

    @BeforeEach
    void setUp() {
        etudiant = EtudiantEntity.builder()
                .id(1L)
                .name("Ahmed Ben Ali")
                .email("ahmed@example.com")
                .build();
    }

    @Test
    void testGetAllEtudiants() {
        // Given
        List<EtudiantEntity> etudiants = Arrays.asList(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // When
        List<EtudiantEntity> result = libraryService.getAllEtudiants();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ahmed Ben Ali", result.get(0).getName());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testGetEtudiantById() {
        // Given
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // When
        Optional<EtudiantEntity> result = libraryService.getEtudiantById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Ahmed Ben Ali", result.get().getName());
        assertEquals("ahmed@example.com", result.get().getEmail());
    }

    @Test
    void testCreateEtudiant() {
        // Given
        when(etudiantRepository.existsByEmail(anyString())).thenReturn(false);
        when(etudiantRepository.save(any(EtudiantEntity.class))).thenReturn(etudiant);

        // When
        EtudiantEntity result = libraryService.createEtudiant("Ahmed Ben Ali", "ahmed@example.com");

        // Then
        assertNotNull(result);
        assertEquals("Ahmed Ben Ali", result.getName());
        verify(etudiantRepository, times(1)).save(any(EtudiantEntity.class));
    }

    @Test
    void testCreateEtudiant_EmailExists() {
        // Given
        when(etudiantRepository.existsByEmail("ahmed@example.com")).thenReturn(true);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            libraryService.createEtudiant("Ahmed", "ahmed@example.com");
        });

        assertEquals("Un étudiant avec cet email existe déjà", exception.getMessage());
        verify(etudiantRepository, never()).save(any());
    }

    @Test
    void testUpdateEtudiant() {
        // Given
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));
        when(etudiantRepository.save(any(EtudiantEntity.class))).thenReturn(etudiant);

        // When
        EtudiantEntity result = libraryService.updateEtudiant(1L, "Ahmed Updated", "new@example.com");

        // Then
        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(any(EtudiantEntity.class));
    }

    @Test
    void testDeleteEtudiant() {
        // Given
        when(etudiantRepository.existsById(1L)).thenReturn(true);
        doNothing().when(etudiantRepository).deleteById(1L);

        // When
        boolean result = libraryService.deleteEtudiant(1L);

        // Then
        assertTrue(result);
        verify(etudiantRepository, times(1)).deleteById(1L);
    }
}