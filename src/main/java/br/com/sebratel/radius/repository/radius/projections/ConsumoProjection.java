package br.com.sebratel.radius.repository.radius.projections;

public interface ConsumoProjection {
    String getUsername();
    Double getDownloadTb();
    Double getUploadTb();
    Double getTotalTb();
}