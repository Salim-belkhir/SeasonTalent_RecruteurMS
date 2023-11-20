package fr.polytech.Recruteur.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.models.Etablissement;
import fr.polytech.Recruteur.models.Offre;
import fr.polytech.Recruteur.services.OffreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(OffreController.class)
class OffreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OffreService offreService;

    @Test
    @DisplayName("GET /offres/1 - Found")
    public void getOffreByIdTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement etablissementGifi = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);
        Offre mockOffre = new Offre(1L, "Offre 1", "Description 1", "Dynamique, Communication", new Date(2023, 11, 24), new Date(2023, 12, 24), "1000 euros", "aucun avantage", etablissementGifi);

        when(offreService.findOffreById(1L)).thenReturn(mockOffre);

        mockMvc.perform(get("/offres/{id}", 1))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id_offre", is(1)))
                .andExpect(jsonPath("$.titre", is("Offre 1")))
                .andExpect(jsonPath("$.descr", is("Description 1")))
                .andExpect(jsonPath("$.competences", is("Dynamique, Communication")))
                .andExpect(jsonPath("$.dateDebut", is("2023-11-24T00:00:00.000+00:00")))
                .andExpect(jsonPath("$.dateFin", is("2023-12-24T00:00:00.000+00:00")))
                .andExpect(jsonPath("$.etablissement.id_etablissement", is(1)))
                .andExpect(jsonPath("$.etablissement.nom_etablissement", is("GIFI")))
                .andExpect(jsonPath("$.etablissement.logo", is("gifi.png")))
                .andExpect(jsonPath("$.etablissement.adresse.id_adresse", is(3)));
    }

    @Test
    @DisplayName("GET /offres/1 - Not Found")
    public void getOffreByIdNotFoundTest() throws Exception {
        when(offreService.findOffreById(1L)).thenReturn(null);

        mockMvc.perform(get("/offres/{id}", 1))
                .andExpect(status().isNotFound());
    }
}