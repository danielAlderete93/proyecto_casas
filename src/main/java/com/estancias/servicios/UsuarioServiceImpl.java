package com.estancias.servicios;

import com.estancias.controladores.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import com.estancias.servicios.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsuarioServiceImpl implements UsuarioServicio {

    private final CRUDBaseRepository<Usuario> usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(CRUDBaseRepository<Usuario> usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario altaUsuario(UsuarioAlta usuarioAlta) {
        //TODO: Validar
        //Exceptions
        //Crear usuario
        Usuario usuario = Usuario.builder()
                .alias(usuarioAlta.getAlias())
                .clave(usuarioAlta.getClave())
                .email(usuarioAlta.getEmail())
                .fechaAlta(new Date())
                .fechaBaja(null)
                .build();

        Integer id = usuarioRepository.guardar(usuario);

        //Guardarlo
        return usuarioRepository.obtenerPorID(id);
    }

    @Override
    public Usuario consulta(Integer id) {
        return usuarioRepository.obtenerPorID(id);
    }

    @Override
    public Usuario baja(Integer id) {
        //Busco usuario
        Usuario usuarioModificado = usuarioRepository.obtenerPorID(id);

        //lo doy de baja
        usuarioModificado.setFechaBaja(new Date());

        //Guardo la baja
        usuarioRepository.guardar(usuarioModificado);

        //Devuelvo el usuario guardado
        return usuarioRepository.obtenerPorID(id);
    }

    @Override
    public boolean eliminacion(Integer id) {
        return usuarioRepository.borrar(id);
    }
}
