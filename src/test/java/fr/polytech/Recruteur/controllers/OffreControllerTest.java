package fr.polytech.Recruteur.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import java.time.LocalDate;
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
        Offre mockOffre = new Offre(1L, "Offre 1", "Description 1", "Dynamique, Communication", LocalDate.of(2023, 11, 24), LocalDate.of(2023, 12, 24), "1000 euros", "aucun avantage", etablissementGifi);

        when(offreService.findOffreById(1L)).thenReturn(mockOffre);

        mockMvc.perform(get("/offres/{id}", 1))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.idOffre", is(1)))
                .andExpect(jsonPath("$.titre", is("Offre 1")))
                .andExpect(jsonPath("$.descr", is("Description 1")))
                .andExpect(jsonPath("$.competences", is("Dynamique, Communication")))
                .andExpect(jsonPath("$.dateDebut", is("2023-11-24")))
                .andExpect(jsonPath("$.dateFin", is("2023-12-24")))
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

    @Test
    @DisplayName("GET /offres/ - Found")
    public void getAllOffresTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement etablissementGifi = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);
        Offre mockOffre1 = new Offre(1L, "Offre 1", "Description 1", "Dynamique, Communication", LocalDate.of(2023, 11, 24), LocalDate.of(2023, 12, 24), "1000 euros", "Rien", etablissementGifi);
        Offre mockOffre2 = new Offre(2L, "Offre 2", "Description 2", "Dynamique, Communication", LocalDate.of(2024, 11, 24), LocalDate.of(2024, 12, 24), "1200 euros", "Ticket restau", etablissementGifi);

        when(offreService.findAllOffres()).thenReturn(List.of(mockOffre1, mockOffre2));

        mockMvc.perform(get("/offres/"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].idOffre", is(1)))
                .andExpect(jsonPath("$[0].titre", is("Offre 1")))
                .andExpect(jsonPath("$[0].descr", is("Description 1")))
                .andExpect(jsonPath("$[0].competences", is("Dynamique, Communication")))
                .andExpect(jsonPath("$[0].dateDebut", is("2023-11-24")))
                .andExpect(jsonPath("$[0].dateFin", is("2023-12-24")))
                .andExpect(jsonPath("$[0].salaire", is("1000 euros")))
                .andExpect(jsonPath("$[0].avantages", is("Rien")))
                .andExpect(jsonPath("$[0].etablissement.nom_etablissement", is("GIFI")))

                .andExpect(jsonPath("$[1].idOffre", is(2)))
                .andExpect(jsonPath("$[1].titre", is("Offre 2")))
                .andExpect(jsonPath("$[1].descr", is("Description 2")))
                .andExpect(jsonPath("$[1].competences", is("Dynamique, Communication")))
                .andExpect(jsonPath("$[1].dateDebut", is("2024-11-24")))
                .andExpect(jsonPath("$[1].dateFin", is("2024-12-24")))
                .andExpect(jsonPath("$[1].salaire", is("1200 euros")))
                .andExpect(jsonPath("$[1].avantages", is("Ticket restau")))
                .andExpect(jsonPath("$[1].etablissement.nom_etablissement", is("GIFI")));
    }

    @Test
    @DisplayName("GET /etablissement/1 - Found")
    public void getAllOffresByEtablissementTest() throws Exception{
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement etablissementGifi = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);
        Offre mockOffre1 = new Offre(1L, "Offre 1", "Description 1", "Dynamique, Communication", LocalDate.of(2023, 11, 24), LocalDate.of(2023, 12, 24), "1000 euros", "Rien", etablissementGifi);
        Offre mockOffre2 = new Offre(2L, "Offre 2", "Description 2", "Dynamique, Communication", LocalDate.of(2024, 11, 24), LocalDate.of(2024, 12, 24), "1200 euros", "Ticket restau", etablissementGifi);

        when(offreService.findAllOffresByEtablissement(1L)).thenReturn(List.of(mockOffre1, mockOffre2));

        mockMvc.perform(get("/offres/etablissement/{id}", 1))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].idOffre", is(1)))
                .andExpect(jsonPath("$[0].titre", is("Offre 1")))
                .andExpect(jsonPath("$[0].dateDebut", is("2023-11-24")))
                .andExpect(jsonPath("$[0].dateFin", is("2023-12-24")))
                .andExpect(jsonPath("$[0].salaire", is("1000 euros")))
                .andExpect(jsonPath("$[0].avantages", is("Rien")))
                .andExpect(jsonPath("$[0].etablissement.nom_etablissement", is("GIFI")))

                .andExpect(jsonPath("$[1].idOffre", is(2)))
                .andExpect(jsonPath("$[1].titre", is("Offre 2")))
                .andExpect(jsonPath("$[1].dateDebut", is("2024-11-24")))
                .andExpect(jsonPath("$[1].dateFin", is("2024-12-24")))
                .andExpect(jsonPath("$[1].salaire", is("1200 euros")))
                .andExpect(jsonPath("$[1].avantages", is("Ticket restau")))
                .andExpect(jsonPath("$[1].etablissement.nom_etablissement", is("GIFI")));
    }

    @Test
    @DisplayName("POST /offres/ - Success")
    public void addOffreTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement etablissementGifi = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);

        Offre postOffre = new Offre(null, "Offre 1", "Description 1", "Dynamique, Communication", LocalDate.of(2023, 11, 24), LocalDate.of(2023, 12, 24), "1000 euros", "Rien", etablissementGifi);
        Offre mockOffre = new Offre(1L, "Offre 1", "Description 1", "Dynamique, Communication", LocalDate.of(2023, 11, 24), LocalDate.of(2023, 12, 24), "1000 euros", "Rien", etablissementGifi);

        when(offreService.addOffre(any(Offre.class))).thenReturn(mockOffre);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(post("/offres/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postOffre)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.idOffre", is(1)))
                .andExpect(jsonPath("$.titre", is("Offre 1")))
                .andExpect(jsonPath("$.descr", is("Description 1")))
                .andExpect(jsonPath("$.competences", is("Dynamique, Communication")))
                .andExpect(jsonPath("$.dateDebut", is("2023-11-24")))
                .andExpect(jsonPath("$.dateFin", is("2023-12-24")))
                .andExpect(jsonPath("$.salaire", is("1000 euros")))
                .andExpect(jsonPath("$.avantages", is("Rien")))
                .andExpect(jsonPath("$.etablissement.nom_etablissement", is("GIFI")));
    }

    @Test
    @DisplayName("PUT /offres/1 - Success")
    public void updateOffreTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement etablissementGifi = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);

        Offre putOffre = new Offre(null, "Offre 1", "Description 1", "Dynamique, Communication", LocalDate.of(2023, 11, 24), LocalDate.of(2023, 12, 24), "1000 euros", "Rien", etablissementGifi);
        Offre mockOffre = new Offre(1L, "Offre 1", "Description 1", "Dynamique, Communication", LocalDate.of(2023, 11, 24), LocalDate.of(2023, 12, 24), "1000 euros", "Rien", etablissementGifi);

        when(offreService.findOffreById(1L)).thenReturn(mockOffre);
        when(offreService.updateOffre(eq(1L), any(Offre.class))).thenReturn(mockOffre);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(put("/offres/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putOffre)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.idOffre", is(1)))
                .andExpect(jsonPath("$.titre", is("Offre 1")))
                .andExpect(jsonPath("$.descr", is("Description 1")))
                .andExpect(jsonPath("$.competences", is("Dynamique, Communication")))
                .andExpect(jsonPath("$.dateDebut", is("2023-11-24")))
                .andExpect(jsonPath("$.dateFin", is("2023-12-24")))
                .andExpect(jsonPath("$.salaire", is("1000 euros")))
                .andExpect(jsonPath("$.avantages", is("Rien")))
                .andExpect(jsonPath("$.etablissement.nom_etablissement", is("GIFI")));
    }

    @Test
    @DisplayName("DELETE /offres/1 - Success")
    public void deleteOffreTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement etablissementGifi = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);
        Offre mockOffre = new Offre(1L, "Offre 1", "Description 1", "Dynamique, Communication", LocalDate.of(2023, 11, 24), LocalDate.of(2023, 12, 24), "1000 euros", "aucun avantage", etablissementGifi);

        doReturn(mockOffre).when(offreService).findOffreById(1L);
        doReturn(true).when(offreService).deleteOffre(1L);

        mockMvc.perform(delete("/offres/{id}", 1))
                .andExpect(status().isOk());
    }

}