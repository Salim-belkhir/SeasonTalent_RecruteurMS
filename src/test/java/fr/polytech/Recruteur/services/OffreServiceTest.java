package fr.polytech.Recruteur.services;

import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.models.Etablissement;
import fr.polytech.Recruteur.models.Offre;
import fr.polytech.Recruteur.repositories.OffreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@WebMvcTest(OffreService.class)
class OffreServiceTest {

    @Autowired
    private OffreService offreService;

    @MockBean
    private OffreRepository offreRepository;

    @Test
    @DisplayName("Test findOffreById Success")
    void findOffreByIdSuccess() {
        Adresse ikea_adresse = new Adresse(1L, 1, "place de Troie Zone Odysseum", "34000", "Montpellier");
        Etablissement ikea = new Etablissement(1L, "Ikea", "ikea.png", ikea_adresse);
        Offre mockOffre = new Offre(1L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", ikea);

        doReturn(Optional.of(mockOffre)).when(offreRepository).findById(1L);

        Offre returnedOffre = offreService.findOffreById(1L);

        assertNotNull(returnedOffre, "Offre was not found");
        assertSame(returnedOffre, mockOffre, "Offre should be the same");
    }

    @Test
    @DisplayName("Test findOffreById Not Found")
    void findOffreByIdNotFound() {
        doReturn(Optional.empty()).when(offreRepository).findById(1L);

        Offre returnedOffre = offreService.findOffreById(1L);

        assertNull(returnedOffre, "Offre was found, when it shouldn't be");
    }

    @Test
    @DisplayName("Test findAllOffres Success")
    void findAllOffresSuccess() {
        Adresse ikea_adresse = new Adresse(1L, 1, "place de Troie Zone Odysseum", "34000", "Montpellier");
        Etablissement ikea = new Etablissement(1L, "Ikea", "ikea.png", ikea_adresse);
        Offre mockOffre1 = new Offre(1L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", ikea);
        Offre mockOffre2 = new Offre(2L, "Préparateur de commandes", "Réception de marchandises", "Dynamique, Esprit d'équipe", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 1), "1200", "Gilet jaune gratuit", ikea);

        doReturn(Arrays.asList(mockOffre1, mockOffre2)).when(offreRepository).findAll();

        List<Offre> offres = offreService.findAllOffres();

        assertNotNull(offres, "Offres were not found");
        assertEquals(2, offres.size(), "findAll should return 2 offres");
        assertSame(offres.get(0), mockOffre1, "Offre should be the same");
        assertSame(offres.get(1), mockOffre2, "Offre should be the same");
    }

    @Test
    @DisplayName("Test findAllOffres Empty")
    void findAllOffresEmpty() {
        doReturn(List.of()).when(offreRepository).findAll();

        List<Offre> offres = offreService.findAllOffres();

        assertEquals(0, offres.size(), "findAll should return 0 offres");
    }

    @Test
    @DisplayName("Test addOffre Success")
    void addOffreSuccess() {
        Adresse ikea_adresse = new Adresse(1L, 1, "place de Troie Zone Odysseum", "34000", "Montpellier");
        Etablissement ikea = new Etablissement(1L, "Ikea", "ikea.png", ikea_adresse);
        Offre mockOffre = new Offre(1L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", ikea);

        doReturn(mockOffre).when(offreRepository).save(mockOffre);

        Offre returnedOffre = offreService.addOffre(mockOffre);

        assertNotNull(returnedOffre, "Offre was not found");
        assertSame(returnedOffre, mockOffre, "Offre should be the same");
    }

    @Test
    @DisplayName("Test addOffre Failure")
    void addOffreNull() {
        Adresse ikea_adresse = new Adresse(1L, 1, "place de Troie Zone Odysseum", "34000", "Montpellier");
        Etablissement ikea = new Etablissement(1L, "Ikea", "ikea.png", ikea_adresse);
        Offre mockOffre1 = new Offre(1L, null, "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", ikea);
        Offre mockOffre2 = null;

        doReturn(null).when(offreRepository).save(any(Offre.class));

        Offre returnedOffre1 = offreService.addOffre(mockOffre1);
        Offre returnedOffre2 = offreService.addOffre(mockOffre2);

        assertNull(returnedOffre1, "Offre was found, when it shouldn't be");
        assertNull(returnedOffre2, "Offre was found, when it shouldn't be");
    }

    @Test
    @DisplayName("Test updateOffre Success")
    void updateOffreSuccess() {
        Adresse ikea_adresse = new Adresse(1L, 1, "place de Troie Zone Odysseum", "34000", "Montpellier");
        Etablissement ikea = new Etablissement(1L, "Ikea", "ikea.png", ikea_adresse);
        Offre mockOffre = new Offre(1L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", ikea);
        Offre mockOffreUpdated = new Offre(1L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1900", "Tickets restaurants", ikea);

        doReturn(Optional.of(mockOffre)).when(offreRepository).findById(1L);
        doReturn(mockOffreUpdated).when(offreRepository).save(any(Offre.class));

        Offre returnedOffre = offreService.updateOffre(1L, mockOffreUpdated);

        assertNotNull(returnedOffre, "Offre was not found");
        assertEquals(returnedOffre.getSalaire(), mockOffreUpdated.getSalaire(), "Offre salary should be the same");
    }

    @Test
    @DisplayName("Test updateOffre Not Found")
    void updateOffreNotFound() {
        Adresse ikea_adresse = new Adresse(1L, 1, "place de Troie Zone Odysseum", "34000", "Montpellier");
        Etablissement ikea = new Etablissement(1L, "Ikea", "ikea.png", ikea_adresse);
        Offre mockOffreUpdated = new Offre(1L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", ikea);

        doReturn(Optional.empty()).when(offreRepository).findById(1L);

        Offre returnedOffre = offreService.updateOffre(1L, mockOffreUpdated);

        assertNull(returnedOffre, "Offre was found, when it shouldn't be");
    }

    @Test
    @DisplayName("Test deleteOffre Success")
    void deleteOffreSuccess() {
        Adresse ikea_adresse = new Adresse(1L, 1, "place de Troie Zone Odysseum", "34000", "Montpellier");
        Etablissement ikea = new Etablissement(1L, "Ikea", "ikea.png", ikea_adresse);
        Offre mockOffre = new Offre(1L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", ikea);

        doReturn(true).when(offreRepository).existsById(1L);

        Boolean isDeleted = offreService.deleteOffre(1L);

        assertTrue(isDeleted, "Offre was not deleted");
    }

    @Test
    @DisplayName("Test deleteOffre Failure")
    void deleteOffreFailure() {
        doReturn(false).when(offreRepository).existsById(1L);

        Boolean isDeleted = offreService.deleteOffre(1L);

        assertFalse(isDeleted, "Offre was deleted, when it shouldn't be");
    }

    @Test
    @DisplayName("Test findAllOffresByEtablissement Success")
    void findAllOffresByEtablissementSuccess() {
        Adresse ikea_adresse = new Adresse(1L, 1, "place de Troie Zone Odysseum", "34000", "Montpellier");
        Etablissement ikea = new Etablissement(1L, "Ikea", "ikea.png", ikea_adresse);

        Adresse viapost_adresse = new Adresse(2L, 50, "Rue des Tamaris", "34130", "Saint-Aunès");
        Etablissement viapost = new Etablissement(2L, "Viapost", "viapost.png", viapost_adresse);

        Offre mockOffre1 = new Offre(1L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", ikea);
        Offre mockOffre2 = new Offre(2L, "Préparateur de commandes", "Réception de marchandises", "Dynamique, Esprit d'équipe", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 1), "1200", "Gilet jaune gratuit", ikea);
        Offre mockOffre3 = new Offre(3L, "Vendeur", "Vente de meubles", "Vente, Communication", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1), "1500", "Tickets restaurants", viapost);
        Offre mockOffre4 = new Offre(4L, "Préparateur de commandes", "Réception de marchandises", "Dynamique, Esprit d'équipe", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 1), "1200", "Gilet jaune gratuit", viapost);

        doReturn(Arrays.asList(mockOffre1, mockOffre2, mockOffre3, mockOffre4)).when(offreRepository).findAll();

        List<Offre> offresIkea = offreService.findAllOffresByEtablissement(1L);
        List<Offre> offresViapost = offreService.findAllOffresByEtablissement(2L);

        assertNotNull(offresIkea, "Offres were not found");
        assertEquals(2, offresIkea.size(), "findAll for IKEA should return 2 offres");
        assertSame(offresIkea.get(0), mockOffre1, "Offre should be the same");
        assertSame(offresIkea.get(1), mockOffre2, "Offre should be the same");

        assertNotNull(offresViapost, "Offres were not found");
        assertEquals(2, offresViapost.size(), "findAll for Viapost should return 2 offres");
        assertSame(offresViapost.get(0), mockOffre3, "Offre should be the same");
        assertSame(offresViapost.get(1), mockOffre4, "Offre should be the same");
    }

}