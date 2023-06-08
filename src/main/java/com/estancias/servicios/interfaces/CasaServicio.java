package com.estancias.servicios.interfaces;

import com.estancias.controladores.dto.CasaAlta;
import com.estancias.entidades.Casa;

public interface CasaServicio {

    Casa crearCasa(CasaAlta casaAlta);

    Casa consulta(Integer idCasa);

    Casa modificacion(Integer id, CasaAlta casaModificada);

    boolean eliminacion(Integer id);

}
