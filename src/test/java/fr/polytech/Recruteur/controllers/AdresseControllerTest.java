package fr.polytech.Recruteur.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.Recruteur.models.Adresse;
import fr.polytech.Recruteur.services.AdresseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
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

@WebMvcTest(AdresseController.class)
public class AdresseControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private AdresseService adresseService;

    @Test
    @DisplayName("GET /adresses/1 - Found")
    public void getAddressByIdTest() throws Exception {
        Adresse mockAdresse = new Adresse(1L, 30, "rue belkhir", "34000", "Montpellier");
        when(adresseService.findAdresseById(1L)).thenReturn(mockAdresse);

        mockMvc.perform(get("/adresses/{id}", 1))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id_adresse", is(1)))
                .andExpect(jsonPath("$.numero_voie", is(30)))
                .andExpect(jsonPath("$.nom_voie", is("rue belkhir")))
                .andExpect(jsonPath("$.code_postal", is("34000")))
                .andExpect(jsonPath("$.ville", is("Montpellier")));
    }

    @Test
    @DisplayName("GET /adresses/1 - Not Found")
    public void getAddressByIdNotFoundTest() throws Exception {
        when(adresseService.findAdresseById(1L)).thenReturn(null);

        mockMvc.perform(get("/adresses/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /adresses - Found")
    public void getAllAdressesTest() throws Exception {
        Adresse mockAdresse1 = new Adresse(1L, 30, "rue belkhir", "34000", "Montpellier");
        Adresse mockAdresse2 = new Adresse(2L, 25, "rue Karim", "34080", "Montpellier");

        List<Adresse> mockAdresses = List.of(mockAdresse1, mockAdresse2);
        when(adresseService.findAllAdresses()).thenReturn(mockAdresses);

        mockMvc.perform(get("/adresses/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id_adresse", is(1)))
                .andExpect(jsonPath("$[0].numero_voie", is(30)))
                .andExpect(jsonPath("$[0].nom_voie", is("rue belkhir")))
                .andExpect(jsonPath("$[0].code_postal", is("34000")))
                .andExpect(jsonPath("$[0].ville", is("Montpellier")))

                .andExpect(jsonPath("$[1].id_adresse", is(2)))
                .andExpect(jsonPath("$[1].numero_voie", is(25)))
                .andExpect(jsonPath("$[1].nom_voie", is("rue Karim")))
                .andExpect(jsonPath("$[1].code_postal", is("34080")))
                .andExpect(jsonPath("$[1].ville", is("Montpellier")));
    }

    @Test
    @DisplayName("GET /adresses - Not Found")
    public void getAllAdressesNotFoundTest() throws Exception {
        when(adresseService.findAllAdresses()).thenReturn(List.of());

        mockMvc.perform(get("/adresses/"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /adresses - Success")
    public void addAdresseTest() throws Exception {
        Adresse postAdresse = new Adresse(null, 25, "rue de la Paix", "75000", "Paris");
        Adresse mockAdresse = new Adresse(2L, 25, "rue de la Paix", "75000", "Paris");

        when(adresseService.addAdresse(any())).thenReturn(mockAdresse);

        mockMvc.perform(post("/adresses/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postAdresse)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id_adresse", is(2)))
                .andExpect(jsonPath("$.numero_voie", is(25)))
                .andExpect(jsonPath("$.nom_voie", is("rue de la Paix")))
                .andExpect(jsonPath("$.code_postal", is("75000")))
                .andExpect(jsonPath("$.ville", is("Paris")));
    }

    @Test
    @DisplayName("POST /adresses - Failure")
    public void addAdresseFailureTest() throws Exception {
        Adresse postAdresse = new Adresse(null, 25, "rue de la Paix", "75000", "Paris");

        when(adresseService.addAdresse(any())).thenReturn(null);

        mockMvc.perform(post("/adresses/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postAdresse)))

                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /adresses/1 - Success")
    public void updateAdresseTest() throws Exception {
        Adresse putAdresse = new Adresse(null, 25, "rue de la Paix", "75000", "Paris");
        Adresse mockAdresse = new Adresse(1L, 25, "rue de la Paix", "75000", "Paris");

        doReturn(mockAdresse).when(adresseService).findAdresseById(1L);
        doReturn(mockAdresse).when(adresseService).updateAdresse(eq(1L), any(Adresse.class));

        mockMvc.perform(put("/adresses/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.IF_MATCH, 1)

                .content(new ObjectMapper().writeValueAsString(putAdresse)))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id_adresse", is(1)))
                .andExpect(jsonPath("$.numero_voie", is(25)))
                .andExpect(jsonPath("$.nom_voie", is("rue de la Paix")))
                .andExpect(jsonPath("$.code_postal", is("75000")))
                .andExpect(jsonPath("$.ville", is("Paris")));
    }

    @Test
    @DisplayName("PUT /adresses/1 - Not Found")
    public void updateAdresseNotFoundTest() throws Exception {
        Adresse putAdresse = new Adresse(null, 25, "rue de la Paix", "75000", "Paris");

        doReturn(null).when(adresseService).findAdresseById(1L);

        mockMvc.perform(put("/adresses/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(putAdresse)))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /adresses/1 - Success")
    public void deleteAdresseTest() throws Exception {
        Adresse mockAdresse = new Adresse(1L, 25, "rue de la Paix", "75000", "Paris");

        doReturn(mockAdresse).when(adresseService).findAdresseById(1L);
        doReturn(true).when(adresseService).deleteAdresse(1L);

        mockMvc.perform(delete("/adresses/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /adresses/1 - Not Found")
    public void deleteAdresseNotFoundTest() throws Exception {
        doReturn(null).when(adresseService).findAdresseById(1L);

        mockMvc.perform(delete("/adresses/{id}", 1))
                .andExpect(status().isNotFound());
    }

}
