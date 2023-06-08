package com.estancias.servicios;

import com.estancias.controladores.dto.FamiliaAlta;
import com.estancias.entidades.Familia;
import com.estancias.entidades.Usuario;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import com.estancias.servicios.interfaces.FamiliaServicio;
import com.estancias.servicios.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

        //Crear usuario->tratar exception
        Usuario usuario = usuarioServicio.altaUsuario(familiaAlta.getUsuarioAlta());

        //Si pasa el try->
        Familia familiaCreada = creaFamiliaDesdeFamiliaAlta(familiaAlta);
        familiaCreada.setUsuario(usuario);

        Integer id = repository.guardar(familiaCreada);

        //Guardarlo
        return repository.obtenerPorID(id);
    }

    @Override
    public Familia consulta(Integer id) {
        return repository.obtenerPorID(id);
    }

    @Override
    public Familia modificacion(Integer id, FamiliaAlta familiaModificada) {
        Familia familiaAEditar = repository.obtenerPorID(id);

        if (familiaAEditar == null) {
            return null;
        }


        Familia familiaConDatosNuevos = creaFamiliaDesdeFamiliaAlta(familiaModificada);

        familiaAEditar.editateConDatosDe(familiaConDatosNuevos);

        return repository.obtenerPorID(id);
    }

    @Override
    public boolean eliminacion(Integer id) {
        return repository.borrar(id);
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
