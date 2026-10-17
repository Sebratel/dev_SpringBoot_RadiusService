package br.com.sebratel.radius.controller.scripts;

import br.com.sebratel.radius.dto.DuplicateCallingStationDTO;
import br.com.sebratel.radius.service.DuplicateCallingStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/radius")
public class DuplicateCallingStationController {

    private final DuplicateCallingStationService service;

    public DuplicateCallingStationController(DuplicateCallingStationService service) {
        this.service = service;
    }

    @GetMapping("/duplicate-calling-stations")
    public ResponseEntity<List<DuplicateCallingStationDTO>> getDuplicateCallingStations() {
        return ResponseEntity.ok(service.listarConexoesDuplicadas());
    }
}