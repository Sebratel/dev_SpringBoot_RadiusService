package br.com.sebratel.radius.service;

import br.com.sebratel.radius.dto.DuplicatePrefixDTO;
import br.com.sebratel.radius.repository.radius.DuplicatePrefixRepository;
import br.com.sebratel.radius.repository.radius.projections.DuplicatePrefixProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DuplicatePrefixServiceTest {

    @Mock
    private DuplicatePrefixRepository repository;

    @InjectMocks
    private DuplicatePrefixService service;

    @Test
    void deveListarPrefixosDuplicadosComSucesso() {
        DuplicatePrefixProjection p = mock(DuplicatePrefixProjection.class);
        when(p.getUsername()).thenReturn("prefixo.teste");
        when(p.getCountCallingstation()).thenReturn(3L);

        when(repository.findDuplicatePrefixes()).thenReturn(List.of(p));

        List<DuplicatePrefixDTO> resultado = service.listarPrefixosDuplicados();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("prefixo.teste", resultado.get(0).username());
        assertEquals(3L, resultado.get(0).countCallingstation());

        verify(repository, times(1)).findDuplicatePrefixes();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverPrefixosDuplicados() {
        when(repository.findDuplicatePrefixes()).thenReturn(List.of());

        List<DuplicatePrefixDTO> resultado = service.listarPrefixosDuplicados();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(repository, times(1)).findDuplicatePrefixes();
    }

    @Test
    void deveMapearCamposNulosCorretamente() {
        DuplicatePrefixProjection p = mock(DuplicatePrefixProjection.class);
        when(p.getUsername()).thenReturn(null);
        when(p.getCountCallingstation()).thenReturn(null);

        when(repository.findDuplicatePrefixes()).thenReturn(List.of(p));

        List<DuplicatePrefixDTO> resultado = service.listarPrefixosDuplicados();

        assertNotNull(resultado);
        assertNull(resultado.get(0).username());
        assertNull(resultado.get(0).countCallingstation());
    }
}