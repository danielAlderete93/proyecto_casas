package com.estancias.servicios;

import com.estancias.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import com.estancias.servicios.interfaces.UsuarioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioServicio {

    private final CRUDBaseRepository<Usuario> usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(CRUDBaseRepository<Usuario> usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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

        Integer idUsuario = usuarioRepository.guardar(usuario);
        log.info("Se creó usuario con id:", idUsuario);

        //Guardarlo
        return idUsuario;
    }

    @Override
    public Usuario consulta(Integer id) {

        log.atInfo().log("Se busca a usuario con id:" + id);
        return usuarioRepository.obtenerPorID(id);
    }

    @Override
    public List<Usuario> consulta() {
        log.atInfo().log("Se busca a todos los usuarios");
        return usuarioRepository.listarTodas();
    }

    @Override
    @Transactional()
    public boolean baja(Integer id) {
        //Busco usuario
        Usuario usuarioModificado = usuarioRepository.obtenerPorID(id);

        //lo doy de baja
        usuarioModificado.setFechaBaja(LocalDateTime.now());

        //Guardo la baja
        usuarioRepository.guardar(usuarioModificado);

        //Devuelvo el usuario guardado
        usuarioModificado = usuarioRepository.obtenerPorID(id);
        log.info("Se actualizó el objeto: {}", usuarioModificado);
        //todo:ver  true
        return true;
    }

    @Override
    @Transactional()
    public boolean cambiarClaveNueva(Integer id, String claveNueva) {

        Usuario usuarioModificado = usuarioRepository.obtenerPorID(id);//Busco usuario

        usuarioModificado.setClave(claveNueva);//cambio clave
        usuarioRepository.guardar(usuarioModificado);//Guardo la baja


        usuarioModificado = usuarioRepository.obtenerPorID(id);//Devuelvo el usuario guardado TODO: Innecesaria

        log.info("Se actualizó el objeto: {}", usuarioModificado);
        //todo: true
        return true;
    }

    @Override
    @Transactional()
    public boolean eliminacion(Integer id) {
        boolean sePudoElimino = usuarioRepository.borrar(id);
        if (sePudoElimino) {
            log.warn("Se eliminó el objeto con ID: {}", id);
        }

        return sePudoElimino;
    }
}
