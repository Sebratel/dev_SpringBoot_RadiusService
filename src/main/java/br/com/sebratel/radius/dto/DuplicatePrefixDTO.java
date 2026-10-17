package br.com.sebratel.radius.dto;

public record DuplicatePrefixDTO(
        String username,
        Long countCallingstation
) {}