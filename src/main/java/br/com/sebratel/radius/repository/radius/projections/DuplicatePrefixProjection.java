package br.com.sebratel.radius.repository.radius.projections;

public interface DuplicatePrefixProjection {
    String getUsername();
    Long getCountCallingstation();
}