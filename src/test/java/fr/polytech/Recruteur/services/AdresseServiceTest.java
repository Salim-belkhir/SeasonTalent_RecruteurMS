package fr.polytech.Recruteur.services;

import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.repositories.AdresseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@WebMvcTest(AdresseService.class)
class AdresseServiceTest {

    @Autowired
    private AdresseService adresseService;

    @MockBean
    private AdresseRepository adresseRepository;

    @Test
    @DisplayName("Test findAdresseById Success")
    void findAdresseByIdSuccess() {
        Adresse mockAdresse = new Adresse(5L, 74, "rue Hamid", "74000", "Annecy");
        doReturn(Optional.of(mockAdresse)).when(adresseRepository).findById(5L);

        Adresse returnedAdresse = adresseService.findAdresseById(5L);

        assertNotNull(returnedAdresse, "Adresse was not found");
        assertSame(returnedAdresse, mockAdresse, "Adresse should be the same");
    }

    @Test
    @DisplayName("Test findAdresseById Not Found")
    void findAdresseByIdNotFound() {
        doReturn(Optional.empty()).when(adresseRepository).findById(5L);

        Adresse returnedAdresse = adresseService.findAdresseById(5L);

        assertNull(returnedAdresse, "Adresse was found, when it shouldn't be");
    }

    @Test
    @DisplayName("Test findAllAdresses Success")
    void findAllAdressesSuccess() {
        Adresse mockAdresse1 = new Adresse(5L, 74, "rue Hamid", "74000", "Annecy");
        Adresse mockAdresse2 = new Adresse(6L, 75, "rue Jamel", "75000", "Chambéry");
        doReturn(Arrays.asList(mockAdresse1, mockAdresse2)).when(adresseRepository).findAll();

        List<Adresse> adresses = adresseService.findAllAdresses();

        assertNotNull(adresses, "Adresses were not found");
        assertEquals(2, adresses.size(), "findAll should return 2 adresses");
        assertSame(adresses.get(0), mockAdresse1, "Adresse 1 should be the same");
        assertSame(adresses.get(1), mockAdresse2, "Adresse 2 should be the same");
    }

    @Test
    @DisplayName("Test addAdresse Success")
    void addAdresseSuccess() {
        Adresse mockAdresse = new Adresse(5L, 74, "rue Hamid", "74000", "Annecy");
        doReturn(mockAdresse).when(adresseRepository).save(any(Adresse.class));

        Adresse returnedAdresse = adresseService.addAdresse(mockAdresse);

        assertNotNull(returnedAdresse, "The new adress should not be null");
        assertSame(returnedAdresse, mockAdresse, "Adresse should be the same");
    }

    @Test
    @DisplayName("Test addAdresse Null")
    void addAdresseNull() {
        Adresse mockAdresse = new Adresse(5L, null, "rue Hamid", "74000", "Annecy");
        doReturn(null).when(adresseRepository).save(any(Adresse.class));

        Adresse returnedAdresse = adresseService.addAdresse(mockAdresse);

        assertNull(returnedAdresse, "The new adress should be null or have a null field");
    }

    @Test
    @DisplayName("Test updateAdresse Success")
    void updateAdresseSuccess() {
        Adresse mockAdresse = new Adresse(5L, 74, "rue Hamid", "74000", "Annecy");
        Adresse mockAdresseUpdated = new Adresse(5L, 75, "rue Hamid", "74000", "Annecy");

        doReturn(Optional.of(mockAdresse)).when(adresseRepository).findById(5L);
        doReturn(mockAdresseUpdated).when(adresseRepository).save(any(Adresse.class));

        Adresse returnedAdresse = adresseService.updateAdresse(5L, mockAdresseUpdated);

        assertNotNull(returnedAdresse, "The updated adress should not be null");
        assertSame(returnedAdresse, mockAdresseUpdated, "Adresse should be the same");
    }

    @Test
    @DisplayName("Test updateAdresse Not Found")
    void updateAdresseNotFound() {
        Adresse mockAdresseUpdated = new Adresse(5L, 75, "rue Hamid", "74000", "Annecy");
        doReturn(Optional.empty()).when(adresseRepository).findById(5L);

        Adresse returnedAdresse = adresseService.updateAdresse(5L, mockAdresseUpdated);

        assertNull(returnedAdresse, "The updated adress should be null");
    }

    @Test
    @DisplayName("Test deleteAdresse Success")
    void deleteAdresseSuccess() {
        Adresse mockAdresse = new Adresse(5L, 74, "rue Hamid", "74000", "Annecy");
        doReturn(true).when(adresseRepository).existsById(5L);

        Boolean isDeleted = adresseService.deleteAdresse(5L);

        assertTrue(isDeleted, "The adress should be deleted");
    }

    @Test
    @DisplayName("Test deleteAdresse Not Found")
    void deleteAdresseNotFound() {
        doReturn(false).when(adresseRepository).existsById(5L);

        Boolean isDeleted = adresseService.deleteAdresse(5L);

        assertFalse(isDeleted, "The adress should not be deleted");
    }

}