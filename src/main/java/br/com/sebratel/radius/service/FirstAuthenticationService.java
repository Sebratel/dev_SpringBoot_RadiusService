package br.com.sebratel.radius.service;

import br.com.sebratel.radius.dto.FirstAuthenticationDTO;
import br.com.sebratel.radius.repository.radius.FirstAuthenticationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstAuthenticationService {

    private final FirstAuthenticationRepository repository;

    public FirstAuthenticationService(FirstAuthenticationRepository repository) {
        this.repository = repository;
    }

    public List<FirstAuthenticationDTO> listarPrimeirasAutenticacoes() {
        return repository.findFirstAuthentications().stream()
                .map(p -> new FirstAuthenticationDTO(
                        p.getPppoe(),
                        p.getAuthentication()
                )).toList();
    }
}