package com.estancias.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioAlta {
    private String alias;
    private String email;
    private String clave;
}
