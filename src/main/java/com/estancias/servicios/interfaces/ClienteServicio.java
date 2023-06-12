package com.estancias.servicios.interfaces;

import com.estancias.dto.ClienteAlta;
import com.estancias.entidades.Cliente;

public interface ClienteServicio {
    Cliente crearCliente(ClienteAlta clienteAlta);

    Cliente consulta(Integer idCliente);

    Cliente modificacion(Integer idCliente, ClienteAlta clienteAlta);

    boolean eliminacion(Integer idCliente);
}
