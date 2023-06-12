package com.estancias.servicios.interfaces;

import com.estancias.dto.EstanciaAlta;
import com.estancias.entidades.Estancia;

public interface EstanciaServicio {
    Estancia crearEstancia(EstanciaAlta estanciaAlta);

    Estancia consulta(Integer idEstancia);

    Estancia modificacion(Integer idEstancia, EstanciaAlta estanciaAlta);

    boolean eliminacion(Integer idEstancia);
}
