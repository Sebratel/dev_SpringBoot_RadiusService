package br.com.sebratel.radius.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contracts", schema = "radius")
public class RadiusContract {
    @Id
    private Long id;
}