package com.estancias.servicios;

import com.estancias.dto.FamiliaAlta;
import com.estancias.entidades.Familia;
import com.estancias.entidades.Usuario;
import com.estancias.excepciones.FamiliaException;
import com.estancias.repositorios.FamiliaRepositorio;
import com.estancias.servicios.interfaces.FamiliaServicio;
import com.estancias.servicios.interfaces.UsuarioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FamiliaServicioImpl implements FamiliaServicio {
    private final FamiliaRepositorio repository;
    private final UsuarioServicio usuarioServicio;

    public FamiliaServicioImpl(FamiliaRepositorio repository, UsuarioServicio usuarioServicio) {
        this.repository = repository;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public Integer crearFamilia(FamiliaAlta familiaAlta) {
        //TODO: Validar
        //Exceptions
        Usuario usuario = usuarioServicio.consulta(familiaAlta.getIdUsuario());

        //Valido
        if (usuario == null) {
            log.error("No se pudo crear familia. No existe usuario.");
            throw new FamiliaException("No se pudo crear familia");
        }

        //Creo y persisto
        Familia familiaCreada = creaFamiliaDesdeFamiliaAlta(familiaAlta);
        familiaCreada.setUsuario(usuario);

        Integer id = repository.save(familiaCreada).getId();
        log.info("Se creó el objeto: {}", familiaCreada);

        return id;
    }

    @Override
    public Familia consulta(Integer id) {
        log.info("Se busca a familia con id:" + id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Familia> consulta() {
        log.info("Se busca a todas las familias");
        return repository.findAll();
    }

    @Override
    public Familia modificacion(Integer id, FamiliaAlta familiaModificada) {
        //Busco
        Familia familia = repository.findById(id).orElse(null);

        //Valido
        if (familia == null) {
            return null;
        }
        //Seteo modificacion y persisto

        Familia familiaConDatosNuevos = creaFamiliaDesdeFamiliaAlta(familiaModificada);

        familia.editateConDatosDe(familiaConDatosNuevos);
        repository.save(familia);

        log.info("Se actualizó el objeto: {}", familia);
        return familia;
    }

    @Override
    public void eliminacion(Integer id) {
        //Busco familia
        Familia familia = repository.findById(id).orElse(null);

        //Validacion
        if (familia == null) {
            log.error("No se puede eliminar familia " + id + ". La familia no existe");
            throw new FamiliaException("La familia no existe.");
        }

        //Eliminacion
        repository.delete(familia);
        log.warn("Se eliminó familia con ID: {}", id);
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
