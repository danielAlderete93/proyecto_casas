package com.estancias.servicios.interfaces;

import com.estancias.dto.CasaAlta;
import com.estancias.entidades.Casa;

public interface CasaServicio {

    Casa crearCasa(CasaAlta casaAlta);

    Casa consulta(Integer idCasa);

    Casa modificacion(Integer id, CasaAlta casaModificada);

    boolean eliminacion(Integer id);

}
