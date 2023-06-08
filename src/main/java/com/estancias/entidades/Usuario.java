package com.estancias.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "usuarios")
public class Usuario extends EntidadPersistente {

    @Column
    private String alias;
    @Column
    private String email;
    @Column
    private String clave;
    @Column
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
}
