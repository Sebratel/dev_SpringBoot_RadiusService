package br.com.sebratel.radius.dto;

import java.time.LocalDateTime;

public record FirstAuthenticationDTO(
        String pppoe,
        LocalDateTime authentication
) {}