package com.estancias.servicios.interfaces;

import com.estancias.dto.EstanciaAlta;
import com.estancias.entidades.Estancia;

import java.util.List;

public interface EstanciaServicio {
    Integer crearEstancia(EstanciaAlta estanciaAlta);

    Estancia consulta(Integer idEstancia);

    List<Estancia> consulta();

    Estancia modificacion(Integer idEstancia, EstanciaAlta estanciaAlta);

    void eliminacion(Integer idEstancia);
}
