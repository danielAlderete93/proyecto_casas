package com.estancias.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
        return this.clave.equals(clave);
    }
}
