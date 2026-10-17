package br.com.sebratel.radius.service;

import br.com.sebratel.radius.dto.UltimaConexaoDTO;
import br.com.sebratel.radius.repository.radius.UltimaConexaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UltimaConexaoService {

    private final UltimaConexaoRepository repository;

    public UltimaConexaoService(UltimaConexaoRepository repository) {
        this.repository = repository;
    }

    public List<UltimaConexaoDTO> listarUltimasConexoes() {
        return repository.findUltimasConexoesAtivas().stream()
                .map(p -> new UltimaConexaoDTO(
                        p.getUsuario(),
                        p.getInicio(),
                        p.getAtualizado(),
                        p.getPausado(),
                        p.getRecebendo(),
                        p.getEnviando(),
                        p.getIpConexao()
                )).toList();
    }
}