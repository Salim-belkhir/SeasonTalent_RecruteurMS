package fr.polytech.Recruteur.services;

import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.models.Etablissement;
import fr.polytech.Recruteur.repositories.EtablissementRepository;
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

@WebMvcTest(EtablissementService.class)
class EtablissementServiceTest {

    @Autowired
    private EtablissementService etablissementService;

    @MockBean
    private EtablissementRepository etablissementRepository;

    @Test
    @DisplayName("Test getEtablissement Success")
    void getEtablissementSuccess() {
        Adresse food_adresse = new Adresse(0L, 44, "place Jean Bene", "34000", "Montpellier");
        Etablissement mockEtablissement = new Etablissement(5L, "The Street Food", "street_food.png", food_adresse);
        doReturn(Optional.of(mockEtablissement)).when(etablissementRepository).findById(5L);

        Etablissement returnedEtablissement = etablissementService.getEtablissement(5L);

        assertNotNull(returnedEtablissement, "Establishment was not found");
        assertSame(returnedEtablissement, mockEtablissement, "Establishment should be the same");
    }

    @Test
    @DisplayName("Test getEtablissement Not Found")
    void getEtablissementNotFound() {
        doReturn(Optional.empty()).when(etablissementRepository).findById(5L);

        Etablissement returnedEtablissement = etablissementService.getEtablissement(5L);

        assertNull(returnedEtablissement, "Establishment was found, when it shouldn't be");
    }

    @Test
    @DisplayName("Test getAllEtablissements Success")
    void getAllEtablissementsSuccess() {
        Adresse snack1 = new Adresse(0L, 44, "place Jean Bene", "34000", "Montpellier");
        Adresse snack2 = new Adresse(1L, 129, "avenue de Lodève", "34070", "Montpellier");
        Etablissement mockEtablissement1 = new Etablissement(5L, "The Street Food", "street_food.png", snack1);
        Etablissement mockEtablissement2 = new Etablissement(6L, "Full Chicken", "full_chicken.png", snack2);

        doReturn(Arrays.asList(mockEtablissement1, mockEtablissement2)).when(etablissementRepository).findAll();

        List<Etablissement> etablissements = etablissementService.getAllEtablissements();

        assertNotNull(etablissements, "Establishments were not found");
        assertEquals(2, etablissements.size(), "findAll should return 2 establishments");
        assertSame(etablissements.get(0), mockEtablissement1, "Establishment should be the same");
        assertSame(etablissements.get(1), mockEtablissement2, "Establishment should be the same");
    }

    @Test
    @DisplayName("Test getAllEtablissements Empty")
    void getAllEtablissementsEmpty() {
        doReturn(List.of()).when(etablissementRepository).findAll();

        List<Etablissement> etablissements = etablissementService.getAllEtablissements();

        assertEquals(0, etablissements.size(), "findAll should return 0 establishment");
    }

    @Test
    @DisplayName("Test addEtablissement Success")
    void addEtablissementSuccess() {
        Adresse food_adresse = new Adresse(0L, 44, "place Jean Bene", "34000", "Montpellier");
        Etablissement mockEtablissement = new Etablissement(5L, "The Street Food", "street_food.png", food_adresse);
        doReturn(mockEtablissement).when(etablissementRepository).save(any(Etablissement.class));

        Etablissement returnedEtablissement = etablissementService.addEtablissement(mockEtablissement);

        assertNotNull(returnedEtablissement, "Establishment was not found");
        assertSame(returnedEtablissement, mockEtablissement, "establishment should be the same");
    }

    @Test
    @DisplayName("Test addEtablissement Null")
    void addEtablissementNull() {
        Adresse food_adresse = new Adresse(0L, 44, "place Jean Bene", "34000", "Montpellier");
        Etablissement mockEtablissement = new Etablissement(5L, "The Street Food", null, food_adresse);
        doReturn(null).when(etablissementRepository).save(any(Etablissement.class));

        Etablissement returnedEtablissement = etablissementService.addEtablissement(mockEtablissement);

        assertNull(returnedEtablissement, "The new establishment should be null or have a null field");
    }

    @Test
    @DisplayName("Test updateEtablissement Success")
    void updateEtablissementSuccess() {
        Adresse food_adresse = new Adresse(0L, 44, "place Jean Bene", "34000", "Montpellier");
        Etablissement mockEtablissement = new Etablissement(5L, "The Street Food", "street_food.png", food_adresse);
        Etablissement mockEtablissementUpdated = new Etablissement(5L, "The New Food", "street_food.png", food_adresse);

        doReturn(Optional.of(mockEtablissement)).when(etablissementRepository).findById(5L);
        doReturn(mockEtablissementUpdated).when(etablissementRepository).save(any(Etablissement.class));

        Etablissement returnedEtablissement = etablissementService.updateEtablissement(5L, mockEtablissementUpdated);

        assertNotNull(returnedEtablissement, "Etablissement was not found");
        assertSame(returnedEtablissement.getNom_etablissement(), mockEtablissementUpdated.getNom_etablissement(), "Establishment name should be the same");
    }

    @Test
    @DisplayName("Test updateEtablissement Failure")
    void updateEtablissementFailure() {
        Adresse food_adresse = new Adresse(0L, 44, "place Jean Bene", "34000", "Montpellier");
        Etablissement mockEtablissement = new Etablissement(5L, "The New Food", "street_food.png", food_adresse);

        Etablissement mockEtablissementUpdated1 = null;
        Etablissement mockEtablissementUpdated2 = new Etablissement(5L, "The New Food", "new_food.png", food_adresse);

        doReturn(Optional.of(mockEtablissement)).when(etablissementRepository).findById(5L);

        Etablissement returnedEtablissement = etablissementService.updateEtablissement(5L, mockEtablissementUpdated1);
        Etablissement returnedEtablissement2 = etablissementService.updateEtablissement(null, mockEtablissementUpdated2);

        assertNull(returnedEtablissement, "In this case, the updated establishment should be null");
        assertNull(returnedEtablissement2, "In this case, the updated establishment should be null");
    }

    @Test
    @DisplayName("Test deleteEtablissement Success")
    void deleteEtablissementSuccess() {
        Adresse food_adresse = new Adresse(0L, 44, "place Jean Bene", "34000", "Montpellier");
        Etablissement mockEtablissement = new Etablissement(5L, "The Street Food", "street_food.png", food_adresse);
        doReturn(Optional.of(mockEtablissement)).when(etablissementRepository).findById(5L);

        Etablissement etablissementDeleted = etablissementService.deleteEtablissement(5L);

        assertEquals(mockEtablissement, etablissementDeleted, "Establishment deleted should be the same as the mock establishment");
    }

    @Test
    @DisplayName("Test deleteEtablissement Failure")
    void deleteEtablissementNotFound() {
        doReturn(Optional.empty()).when(etablissementRepository).findById(5L);

        Etablissement etablissementDeleted = etablissementService.deleteEtablissement(5L);
        Etablissement etablissementDeleted2 = etablissementService.deleteEtablissement(null);

        assertNull(etablissementDeleted, "Establishment deleted should be null");
        assertNull(etablissementDeleted2, "Establishment deleted should be null");
    }

}