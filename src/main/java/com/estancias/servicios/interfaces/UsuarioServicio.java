package com.estancias.servicios.interfaces;

import com.estancias.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {
    Integer altaUsuario(UsuarioAlta usuarioAlta);

    Usuario consulta(Integer id);

    List<Usuario> consulta();

    boolean baja(Integer id);

    boolean cambiarClaveNueva(Integer id, String claveNueva);


    boolean eliminacion(Integer id);
}
