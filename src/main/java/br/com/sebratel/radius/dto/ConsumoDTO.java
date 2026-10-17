package br.com.sebratel.radius.dto;

public record ConsumoDTO(
        String username,
        String cliente,
        String contrato,
        String plano,
        Double downloadTb,
        Double uploadTb,
        Double totalTb
) {}