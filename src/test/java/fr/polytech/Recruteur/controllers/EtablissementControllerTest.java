package fr.polytech.Recruteur.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.models.Etablissement;
import fr.polytech.Recruteur.services.EtablissementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(EtablissementController.class)
class EtablissementControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private EtablissementService etablissementService;

    @Test
    @DisplayName("GET /etablissements/1 - Found")
    public void getEtablissementByIdTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement mockEtablissement = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);

        when(etablissementService.getEtablissement(1L)).thenReturn(mockEtablissement);

        mockMvc.perform(get("/etablissements/{id}", 1))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id_etablissement", is(1)))
                .andExpect(jsonPath("$.nom_etablissement", is("GIFI")))
                .andExpect(jsonPath("$.logo", is("gifi.png")))
                .andExpect(jsonPath("$.adresse.id_adresse", is(3)))
                .andExpect(jsonPath("$.adresse.numero_voie", is(10)))
                .andExpect(jsonPath("$.adresse.nom_voie", is("rue gifi")))
                .andExpect(jsonPath("$.adresse.code_postal", is("34000")))
                .andExpect(jsonPath("$.adresse.ville", is("Montpellier")));
    }

    @Test
    @DisplayName("GET /etablissements/1 - Not Found")
    public void getEtablissementByIdNotFoundTest() throws Exception {
        when(etablissementService.getEtablissement(1L)).thenReturn(null);

        mockMvc.perform(get("/etablissements/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /etablissements - Found")
    public void getAllEtablissementsTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Adresse adresseCarrefour = new Adresse(4L, 10, "rue carrefour", "34000", "Montpellier");

        Etablissement mockEtablissement1 = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);
        Etablissement mockEtablissement2 = new Etablissement(2L, "Carrefour", "carrefour.png", adresseCarrefour);

        List<Etablissement> mockEtablissements = List.of(mockEtablissement1, mockEtablissement2);
        when(etablissementService.getAllEtablissements()).thenReturn(mockEtablissements);

        mockMvc.perform(get("/etablissements/"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].id_etablissement", is(1)))
                .andExpect(jsonPath("$[0].nom_etablissement", is("GIFI")))
                .andExpect(jsonPath("$[0].logo", is("gifi.png")))
                .andExpect(jsonPath("$[0].adresse.id_adresse", is(3)))
                .andExpect(jsonPath("$[0].adresse.numero_voie", is(10)))
                .andExpect(jsonPath("$[0].adresse.nom_voie", is("rue gifi")))
                .andExpect(jsonPath("$[0].adresse.code_postal", is("34000")))
                .andExpect(jsonPath("$[0].adresse.ville", is("Montpellier")))

                .andExpect(jsonPath("$[1].id_etablissement", is(2)))
                .andExpect(jsonPath("$[1].nom_etablissement", is("Carrefour")))
                .andExpect(jsonPath("$[1].logo", is("carrefour.png")))
                .andExpect(jsonPath("$[1].adresse.id_adresse", is(4)))
                .andExpect(jsonPath("$[1].adresse.numero_voie", is(10)))
                .andExpect(jsonPath("$[1].adresse.nom_voie", is("rue carrefour")))
                .andExpect(jsonPath("$[1].adresse.code_postal", is("34000")))
                .andExpect(jsonPath("$[1].adresse.ville", is("Montpellier")));
    }

    @Test
    @DisplayName("POST /etablissements - Success")
    public void addEtablissementTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement postEtablissement = new Etablissement(null, "GIFI", "gifi.png", adresseGifi);
        Etablissement mockEtablissement = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);

        when(etablissementService.addEtablissement(any(Etablissement.class))).thenReturn(mockEtablissement);

        mockMvc.perform(post("/etablissements/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postEtablissement)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id_etablissement", is(1)))
                .andExpect(jsonPath("$.nom_etablissement", is("GIFI")))
                .andExpect(jsonPath("$.logo", is("gifi.png")))
                .andExpect(jsonPath("$.adresse.id_adresse", is(3)))
                .andExpect(jsonPath("$.adresse.numero_voie", is(10)))
                .andExpect(jsonPath("$.adresse.nom_voie", is("rue gifi")))
                .andExpect(jsonPath("$.adresse.code_postal", is("34000")))
                .andExpect(jsonPath("$.adresse.ville", is("Montpellier")));
    }

    @Test
    @DisplayName("PUT /etablissements/1 - Success")
    public void updateEtablissementTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement putEtablissement = new Etablissement(null, "GIFI", "gifi.png", adresseGifi);
        Etablissement mockEtablissement = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);

        doReturn(mockEtablissement).when(etablissementService).getEtablissement(1L);
        doReturn(mockEtablissement).when(etablissementService).updateEtablissement(eq(1L), any(Etablissement.class));

        mockMvc.perform(put("/etablissements/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(putEtablissement)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id_etablissement", is(1)))
                .andExpect(jsonPath("$.nom_etablissement", is("GIFI")))
                .andExpect(jsonPath("$.logo", is("gifi.png")))
                .andExpect(jsonPath("$.adresse.id_adresse", is(3)))
                .andExpect(jsonPath("$.adresse.numero_voie", is(10)))
                .andExpect(jsonPath("$.adresse.nom_voie", is("rue gifi")))
                .andExpect(jsonPath("$.adresse.code_postal", is("34000")))
                .andExpect(jsonPath("$.adresse.ville", is("Montpellier")));
    }

    @Test
    @DisplayName("PUT /etablissements/1 - Not Found")
    public void updateEtablissementNotFoundTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement putEtablissement = new Etablissement(null, "GIFI", "gifi.png", adresseGifi);

        doReturn(null).when(etablissementService).getEtablissement(1L);

        mockMvc.perform(put("/etablissements/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(putEtablissement)))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /etablissements/1 - Success")
    public void deleteEtablissementTest() throws Exception {
        Adresse adresseGifi = new Adresse(3L, 10, "rue gifi", "34000", "Montpellier");
        Etablissement mockEtablissement = new Etablissement(1L, "GIFI", "gifi.png", adresseGifi);

        doReturn(mockEtablissement).when(etablissementService).getEtablissement(1L);
        doReturn(mockEtablissement).when(etablissementService).deleteEtablissement(1L);

        mockMvc.perform(delete("/etablissements/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /etablissements/1 - Not Found")
    public void deleteEtablissementNotFoundTest() throws Exception {
        doReturn(null).when(etablissementService).getEtablissement(1L);

        mockMvc.perform(delete("/etablissements/{id}", 1))
                .andExpect(status().isNotFound());
    }
}