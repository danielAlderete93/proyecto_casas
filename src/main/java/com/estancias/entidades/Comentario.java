package com.estancias.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "comentarios")
public class Comentario extends EntidadPersistente{

    @Column
    private String descripcion;
    @ManyToOne
    private Casa casa;
}
