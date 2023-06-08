package com.estancias.servicios.interfaces;

import com.estancias.controladores.dto.FamiliaAlta;
import com.estancias.entidades.Familia;

public interface FamiliaServicio {
    Familia crearFamilia(FamiliaAlta alta);

    Familia consulta(Integer id);

    Familia modificacion(Integer id, FamiliaAlta alta);

    boolean eliminacion(Integer id);
}
