package br.com.sebratel.radius.dto;

public record DuplicateCallingStationDTO(
        String username,
        Long uniqueCallingstationCount
) {}