package com.estancias.servicios.interfaces;

import com.estancias.controladores.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;

public interface UsuarioServicio {
    Usuario altaUsuario(UsuarioAlta usuarioAlta);

    Usuario consulta(Integer id);

    Usuario baja(Integer id);

    boolean eliminacion(Integer id);
}
