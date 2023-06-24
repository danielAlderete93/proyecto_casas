package com.estancias.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public boolean estaActivo() {
        return this.fechaBaja == null;
    }

    public void dateDeBaja() {
        this.fechaBaja = LocalDateTime.now();
    }

    public void levantateBaja() {
        this.fechaBaja = null;
    }

    public boolean esMismaClave(String clave) {
        return  this.clave.equals(clave);
    }
}
