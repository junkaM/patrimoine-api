package com.hei.patrimoine_api;

import com.hei.patrimoine_api.controller.PatrimoineController;
import com.hei.patrimoine_api.model.Patrimoine;
import com.hei.patrimoine_api.service.PatrimoineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(PatrimoineController.class)
public class TestPatrimoineAPI {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PatrimoineService patrimoineService;

        private Patrimoine patrimoine;

        @BeforeEach
        public void setUp() {
            patrimoine = new Patrimoine("John Doe", LocalDateTime.now());
        }

        @Test
        public void testCreateOrUpdatePatrimoine() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.put("/patrimoines/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"possesseur\":\"John Doe\"}"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void testGetPatrimoine() throws Exception {
            Mockito.when(patrimoineService.getPatrimoine(any())).thenReturn(patrimoine);

            mockMvc.perform(MockMvcRequestBuilders.get("/patrimoines/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.possesseur").value("John Doe"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.derniereModification").exists());
        }
}
