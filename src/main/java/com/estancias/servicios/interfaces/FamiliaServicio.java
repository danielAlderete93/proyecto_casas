package com.estancias.servicios.interfaces;

import com.estancias.dto.FamiliaAlta;
import com.estancias.entidades.Familia;

import java.util.List;

public interface FamiliaServicio {
    Integer crearFamilia(FamiliaAlta alta);

    Familia consulta(Integer id);

    List<Familia> consulta();

    Familia modificacion(Integer id, FamiliaAlta alta);

    void eliminacion(Integer id);
}
