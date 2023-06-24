package com.estancias.servicios;

import com.estancias.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;
import com.estancias.excepciones.UsuarioException;
import com.estancias.repositorios.UsuarioRepositorio;
import com.estancias.servicios.interfaces.UsuarioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServiceImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    @Transactional()
    public Integer altaUsuario(UsuarioAlta usuarioAlta) {
        //TODO: Validar
        //Exceptions
        //Crear usuario
        Usuario usuario = Usuario.builder()
                .alias(usuarioAlta.getAlias())
                .clave(usuarioAlta.getClave())
                .email(usuarioAlta.getEmail())
                .fechaAlta(LocalDateTime.now())
                .fechaBaja(null)
                .build();

        Integer idUsuario = usuarioRepositorio.save(usuario).getId();
        log.atInfo().log("Se creó usuario con id:", idUsuario);


        return idUsuario;
    }

    @Override
    public Usuario consulta(Integer id) {
        log.atInfo()
                .log("Se busca a usuario con id:" + id);
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @Override
    public Usuario consulta(String alias) {
        log.atInfo()
                .log("Se busca a usuario con alias:" + alias);
        return usuarioRepositorio.findByAlias(alias).orElse(null);
    }

    @Override
    public List<Usuario> consulta() {
        log.atInfo()
                .log("Se busca a todos los usuarios");
        return usuarioRepositorio.findAll();
    }

    @Override
    @Transactional()
    public void baja(Integer id) {
        //Busco usuario
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        //Validacion
        if (usuario == null) {
            log.atError()
                    .log("No se puede dar de baja usuario " + id + ". El usuario no existe");
            throw new UsuarioException("El usuario no existe.");
        }
        if (!usuario.estaActivo()) {
            log.atError()
                    .log("No se puede dar de baja usuario " + id + ". El usuario ya fue dado de baja");
            throw new UsuarioException("El usuario ya fue dado de baja.");
        }


        //Damos de baja + persistencia
        usuario.dateDeBaja();
        usuarioRepositorio.save(usuario);
        log.info("Se actualizó el objeto: {}", usuario);


    }

    @Override
    @Transactional()
    public void cambiarClaveNueva(Integer id, String claveNueva) {
        //Busco usuario
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        //Validacion
        if (usuario == null) {
            log.atError()
                    .log("No se puede cambiar clave usuario " + id + ". El usuario no existe");
            throw new UsuarioException("El usuario no existe.");
        }

        if (usuario.esMismaClave(claveNueva)) {
            log.atError()
                    .log("No se puede cambiar clave usuario " + id + ". El usuario ya tiene esa clave");
            throw new UsuarioException("El usuario ya tiene esa clave.");
        }

        //Seteo nueva clave y persisto
        usuario.setClave(claveNueva);
        usuarioRepositorio.save(usuario);
        log.info("Se actualizó el objeto: {}", usuario);
    }

    @Override
    @Transactional()
    public void eliminacion(Integer id) {
        //Busco usuario
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        //Validacion
        if (usuario == null) {
            log.atError()
                    .log("No se puede eliminar usuario " + id + ". El usuario no existe");
            throw new UsuarioException("El usuario no existe.");
        }

        usuarioRepositorio.delete(usuario);
        log.warn("Se eliminó el objeto con ID: {}", id);
    }

    @Override
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByAlias(alias).orElse(null);

        //Usuario nulo -> no login
        if (usuario == null) {
            return null;
        }
        //Instancio usuario logueado
        List<GrantedAuthority> permisos = new ArrayList<>();
        GrantedAuthority rol = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
        permisos.add(rol);

        return new User(usuario.getAlias(), usuario.getClave(), permisos);
    }
}
