package br.com.sebratel.radius.service;

import br.com.sebratel.radius.dto.DuplicateCallingStationDTO;
import br.com.sebratel.radius.repository.radius.DuplicateCallingStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuplicateCallingStationService {

    private final DuplicateCallingStationRepository repository;

    public DuplicateCallingStationService(DuplicateCallingStationRepository repository) {
        this.repository = repository;
    }

    public List<DuplicateCallingStationDTO> listarConexoesDuplicadas() {
        return repository.findDuplicateCallingStations().stream()
                .map(p -> new DuplicateCallingStationDTO(
                        p.getUsername(),
                        p.getUniqueCallingstationCount()
                )).toList();
    }
}