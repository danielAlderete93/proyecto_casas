package com.estancias.controladores.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteAlta {
    private String nombre;
    private String calle;
    private Integer numero;
    private String codPostal;
    private String ciudad;
    private String pais;
    private String email;
    private UsuarioAlta usuario;
}
