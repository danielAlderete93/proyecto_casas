package com.estancias.servicios.interfaces;

import com.estancias.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {
    Integer altaUsuario(UsuarioAlta usuarioAlta);

    Usuario consulta(Integer id);

    Usuario consulta(String alias);

    List<Usuario> consulta();


    void baja(Integer id);

    void cambiarClave(Integer id, String claveNueva);


    void eliminacion(Integer id);
}
