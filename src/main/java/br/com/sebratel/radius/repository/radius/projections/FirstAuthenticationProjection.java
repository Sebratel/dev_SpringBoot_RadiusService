package br.com.sebratel.radius.repository.radius.projections;

import java.time.LocalDateTime;

public interface FirstAuthenticationProjection {
    String getPppoe();
    LocalDateTime getAuthentication();
}