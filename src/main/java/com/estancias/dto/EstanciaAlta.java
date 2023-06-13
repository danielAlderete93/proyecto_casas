package com.estancias.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EstanciaAlta {
    private String huesped;
    private String fechaDesde;
    private String fechaHasta;
    private Integer idCliente;
    private Integer idCasa;
}
