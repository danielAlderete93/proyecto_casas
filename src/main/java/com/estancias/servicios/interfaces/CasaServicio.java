package com.estancias.servicios.interfaces;

import com.estancias.dto.CasaAlta;
import com.estancias.entidades.Casa;

import java.util.List;

public interface CasaServicio {

    Integer crearCasa(CasaAlta casaAlta);

    Casa consulta(Integer idCasa);

    List<Casa> consulta();

    Casa modificacion(Integer id, CasaAlta casaModificada);

    void eliminacion(Integer id);

}
