package com.estancias.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CasaAlta {
    private String calle;
    private Integer numero;
    private String codPostal;
    private String ciudad;
    private String pais;
    private Double precio;
    private Date fechaDesde;
    private Date fechaHasta;
    private Integer minDias;
    private Integer maxDias;
    private String tipoVivienda;
    private Integer idPropietario;
}
