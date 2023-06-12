package com.estancias.servicios.interfaces;

import com.estancias.dto.FamiliaAlta;
import com.estancias.entidades.Familia;

import java.util.List;

public interface FamiliaServicio {
    Familia crearFamilia(FamiliaAlta alta);

    Familia consulta(Integer id);

    List<Familia> consulta();

    Familia modificacion(Integer id, FamiliaAlta alta);

    boolean eliminacion(Integer id);
}
