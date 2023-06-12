package com.estancias.servicios;

import com.estancias.dto.CasaAlta;
import com.estancias.entidades.Casa;
import com.estancias.entidades.Familia;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import com.estancias.servicios.interfaces.CasaServicio;
import com.estancias.servicios.interfaces.FamiliaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasaServicioImpl implements CasaServicio {

    private final CRUDBaseRepository<Casa> repository;
    private final FamiliaServicio familiaServicio;

    @Autowired
    public CasaServicioImpl(CRUDBaseRepository<Casa> repository, FamiliaServicio familiaServicio) {
        this.repository = repository;
        this.familiaServicio = familiaServicio;
    }

    @Override
    public Casa crearCasa(CasaAlta casaAlta) {
        //Validar CasaAlta
        Familia propietario = familiaServicio.consulta(casaAlta.getIdPropietario());
        //Validar propietario

        Casa casa = creaCasaDesdeCasaAlta(casaAlta);
        casa.setPropietario(propietario);


        Integer id = repository.guardar(casa);

        //Guardarlo
        return repository.obtenerPorID(id);
    }

    @Override
    public Casa consulta(Integer idCasa) {
        return repository.obtenerPorID(idCasa);
    }

    @Override
    public Casa modificacion(Integer id, CasaAlta casaModificada) {
        Familia propietario = familiaServicio.consulta(casaModificada.getIdPropietario());
        Casa casaAEditar = repository.obtenerPorID(id);

        if (propietario == null) {
            return null;
        }

        Casa casaConDatosNuevos = creaCasaDesdeCasaAlta(casaModificada);
        casaConDatosNuevos.setPropietario(propietario);

        casaAEditar.editateConDatosDe(casaConDatosNuevos);

        return repository.obtenerPorID(id);
    }

    @Override
    public boolean eliminacion(Integer id) {
        return repository.borrar(id);
    }

    private Casa creaCasaDesdeCasaAlta(CasaAlta casaAlta) {
        return Casa.builder()
                .maxDias(casaAlta.getMaxDias())
                .minDias(casaAlta.getMinDias())
                .calle(casaAlta.getCalle())
                .pais(casaAlta.getPais())
                .precio(casaAlta.getPrecio())
                .numero(casaAlta.getNumero())
                .fechaDesde(casaAlta.getFechaDesde())
                .fechaHasta(casaAlta.getFechaHasta())
                .ciudad(casaAlta.getCiudad())
                .tipoVivienda(casaAlta.getTipoVivienda())
                .codPostal(casaAlta.getCodPostal())
                .build();
    }
}
