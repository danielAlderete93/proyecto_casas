package com.estancias.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamiliaAlta {
    private String nombre;
    private Integer edadMin;
    private Integer edadMax;
    private Integer numHijos;
    private String email;
    private Integer idUsuario;
}
