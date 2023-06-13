package com.estancias.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "usuarios")
public class Usuario extends EntidadPersistente {

    @Column
    private String alias;
    @Column
    private String email;
    @Column
    private String clave;
    @Column
    private LocalDateTime fechaAlta;
    @Column(nullable = true)
    private LocalDateTime fechaBaja;

    public boolean estaActivo() {
        return this.fechaBaja == null;
    }
}
