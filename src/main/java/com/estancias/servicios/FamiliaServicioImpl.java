package com.estancias.servicios;

import com.estancias.dto.FamiliaAlta;
import com.estancias.entidades.Familia;
import com.estancias.entidades.Usuario;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import com.estancias.servicios.interfaces.FamiliaServicio;
import com.estancias.servicios.interfaces.UsuarioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FamiliaServicioImpl implements FamiliaServicio {
    private final CRUDBaseRepository<Familia> repository;
    private final UsuarioServicio usuarioServicio;

    @Autowired
    public FamiliaServicioImpl(CRUDBaseRepository<Familia> repository, UsuarioServicio usuarioServicio) {
        this.repository = repository;
        this.usuarioServicio = usuarioServicio;
    }


    @Override
    public Familia crearFamilia(FamiliaAlta familiaAlta) {
        //TODO: Validar
        //Exceptions
        Usuario usuario = usuarioServicio.consulta(familiaAlta.getIdUsuario());
        //Crear usuario->tratar exception

        //Si pasa el try->
        Familia familiaCreada = creaFamiliaDesdeFamiliaAlta(familiaAlta);
        familiaCreada.setUsuario(usuario);

        Integer id = repository.guardar(familiaCreada);
        log.info("Se creó el objeto: {}", familiaCreada);
        //Guardarlo
        return repository.obtenerPorID(id);
    }

    @Override
    public Familia consulta(Integer id) {
        log.atInfo().log("Se busca a familia con id:" + id);
        return repository.obtenerPorID(id);
    }

    @Override
    public List<Familia> consulta() {
        log.atInfo().log("Se busca a todas las familias");
        return repository.listarTodas();
    }

    @Override
    public Familia modificacion(Integer id, FamiliaAlta familiaModificada) {
        Familia familiaAEditar = repository.obtenerPorID(id);

        if (familiaAEditar == null) {
            return null;
        }


        Familia familiaConDatosNuevos = creaFamiliaDesdeFamiliaAlta(familiaModificada);

        familiaAEditar.editateConDatosDe(familiaConDatosNuevos);

        log.info("Se actualizó el objeto: {}", familiaAEditar);
        return repository.obtenerPorID(id);
    }

    @Override
    public boolean eliminacion(Integer id) {
        boolean seElimino = repository.borrar(id);
        if (seElimino) {
            log.warn("Se eliminó el objeto con ID: {}", id);
        }
        return seElimino;
    }

    private Familia creaFamiliaDesdeFamiliaAlta(FamiliaAlta familiaAlta) {
        return Familia.builder()
                .numHijos(familiaAlta.getNumHijos())
                .edadMax(familiaAlta.getEdadMax())
                .edadMin(familiaAlta.getEdadMin())
                .nombre(familiaAlta.getNombre())
                .email(familiaAlta.getEmail())
                .build();
    }
}
