package com.estancias.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "casas")
public class Casa extends EntidadPersistente {
    @Column
    private String calle;
    @Column
    private Integer numero;
    @Column
    private String codPostal;
    @Column
    private String ciudad;
    @Column
    private String pais;
    @Column
    @Temporal(TemporalType.TIME)
    private Date fechaDesde;
    @Column
    @Temporal(TemporalType.TIME)
    private Date fechaHasta;
    @Column
    private Integer minDias;
    @Column
    private Integer maxDias;
    @Column
    private Double precio;
    @Column
    private String tipoVivienda;
    @ManyToOne()
    private Familia propietario;


    public Casa editateConDatosDe(Casa casa) {
        this.calle = casa.getCalle();
        this.numero = casa.getNumero();
        this.codPostal = casa.getCodPostal();
        this.ciudad = casa.getCiudad();
        this.pais = casa.getPais();
        this.fechaDesde = casa.getFechaDesde();
        this.fechaHasta = casa.getFechaHasta();
        this.minDias = casa.getMinDias();
        this.maxDias = casa.getMaxDias();
        this.precio = casa.getPrecio();
        this.tipoVivienda = casa.getTipoVivienda();
        this.propietario = casa.getPropietario();
        return this;
    }
}
