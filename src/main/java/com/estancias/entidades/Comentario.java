package com.estancias.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "comentarios")
public class Comentario extends EntidadPersistente {

    @Column
    private String descripcion;
    @ManyToOne
    private Casa casa;
}
