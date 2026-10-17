package br.com.sebratel.radius.controller;

import br.com.sebratel.radius.BaseTest;
import br.com.sebratel.radius.controller.scripts.DuplicateCallingStationController;
import br.com.sebratel.radius.dto.DuplicateCallingStationDTO;
import br.com.sebratel.radius.service.DuplicateCallingStationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DuplicateCallingStationController.class)
@AutoConfigureMockMvc(addFilters = false)
class DuplicateCallingStationControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DuplicateCallingStationService duplicateCallingStationService;

    @Test
    @DisplayName("Deve retornar 200 ao listar conexões duplicadas do radius")
    void getDuplicateCallingStationsSucesso() throws Exception {
        DuplicateCallingStationDTO dto = new DuplicateCallingStationDTO("", 1L);
        List<DuplicateCallingStationDTO> lista = List.of(dto);

        when(duplicateCallingStationService.listarConexoesDuplicadas()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/radius/duplicate-calling-stations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }
}