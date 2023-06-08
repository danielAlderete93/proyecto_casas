package com.estancias.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "clientes")
public class Cliente extends EntidadPersistente {

    @Column
    private String nombre;
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
    private String email;
    @OneToOne
    private Usuario usuario;


    public Cliente editateConDatosDe(Cliente cliente) {
        this.nombre = cliente.getNombre();
        this.calle = cliente.getCalle();
        this.numero = cliente.getNumero();
        this.codPostal = cliente.getCodPostal();
        this.ciudad = cliente.getCiudad();
        this.pais = cliente.getPais();
        this.email = cliente.getEmail();
        this.usuario = cliente.getUsuario();
        return this;
    }
}
