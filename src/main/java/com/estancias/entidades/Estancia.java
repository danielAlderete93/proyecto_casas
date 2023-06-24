package com.estancias.entidades;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "estancias")
public class Estancia extends EntidadPersistente {

    @Column
    private String huesped;
    @Column
    private LocalDateTime fechaDesde;
    @Column
    private LocalDateTime fechaHasta;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Casa casa;


    public Estancia editateConDatosDe(Estancia estancia) {
        this.huesped = estancia.getHuesped();
        this.fechaDesde = estancia.getFechaDesde();
        this.fechaHasta = estancia.getFechaHasta();
        this.cliente = estancia.getCliente();
        this.casa = estancia.getCasa();
        return this;
    }
}

