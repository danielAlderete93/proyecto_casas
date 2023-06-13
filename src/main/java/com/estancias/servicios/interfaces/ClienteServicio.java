package com.estancias.servicios.interfaces;

import com.estancias.dto.ClienteAlta;
import com.estancias.entidades.Cliente;

import java.util.List;

public interface ClienteServicio {
    Integer crearCliente(ClienteAlta clienteAlta);

    Cliente consulta(Integer idCliente);

    List<Cliente> consulta();

    Cliente modificacion(Integer idCliente, ClienteAlta clienteAlta);

    boolean eliminacion(Integer idCliente);
}
