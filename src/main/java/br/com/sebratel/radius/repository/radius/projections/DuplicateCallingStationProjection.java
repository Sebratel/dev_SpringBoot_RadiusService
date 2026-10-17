package br.com.sebratel.radius.repository.radius.projections;

public interface DuplicateCallingStationProjection {
    String getUsername();
    Long getUniqueCallingstationCount();
}