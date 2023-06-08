package com.estancias.entidades;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


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
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
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

