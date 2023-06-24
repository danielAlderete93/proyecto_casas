package com.estancias.servicios.interfaces;

import com.estancias.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {
    Integer altaUsuario(UsuarioAlta usuarioAlta);

    Usuario consulta(Integer id);

    Usuario consulta(String alias);

    List<Usuario> consulta();


    void baja(Integer id);

    void cambiarClaveNueva(Integer id, String claveNueva);


    void eliminacion(Integer id);
}
