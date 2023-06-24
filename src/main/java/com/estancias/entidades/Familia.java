package com.estancias.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "familias")
public class Familia extends EntidadPersistente {

    @Column
    private String nombre;
    @Column
    private Integer edadMin;
    @Column
    private Integer edadMax;
    @Column
    private Integer numHijos;
    @Column
    private String email;
    @OneToOne
    private Usuario usuario;


    public Familia editateConDatosDe(Familia familia) {
        this.edadMax = familia.getEdadMax();
        this.edadMin = familia.getEdadMin();
        this.email = familia.getEmail();
        this.nombre = familia.getNombre();
        this.numHijos = familia.getNumHijos();
        return this;
    }
}
