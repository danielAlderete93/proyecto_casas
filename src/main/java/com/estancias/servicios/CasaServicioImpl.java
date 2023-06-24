package com.estancias.servicios;

import com.estancias.dto.CasaAlta;
import com.estancias.entidades.Casa;
import com.estancias.entidades.Familia;
import com.estancias.excepciones.CasaException;
import com.estancias.repositorios.CasaRepositorio;
import com.estancias.servicios.interfaces.CasaServicio;
import com.estancias.servicios.interfaces.FamiliaServicio;
import com.estancias.utils.FechaConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CasaServicioImpl implements CasaServicio {

    private final CasaRepositorio repository;
    private final FamiliaServicio familiaServicio;

    public CasaServicioImpl(CasaRepositorio repository, FamiliaServicio familiaServicio) {
        this.repository = repository;
        this.familiaServicio = familiaServicio;
    }

    @Override
    public Integer crearCasa(CasaAlta casaAlta) {
        //Validar CasaAlta
        Familia propietario = familiaServicio.consulta(casaAlta.getIdPropietario());
        //Validar propietario

        Casa casa = creaCasaDesdeCasaAlta(casaAlta);
        casa.setPropietario(propietario);


        Integer id = repository.save(casa).getId();
        log.info("Se creó casa con id:", id);
        //Guardarlo
        return id;
    }

    @Override
    public Casa consulta(Integer idCasa) {
        log.info("Se busca a casa con id:" + idCasa);
        return repository.findById(idCasa).orElse(null);
    }

    @Override
    public List<Casa> consulta() {
        log.info("Se busca todas las casas");
        return repository.findAll();
    }

    @Override
    public Casa modificacion(Integer id, CasaAlta casaModificada) {
        //Busco propietarios y casa
        Familia propietario = familiaServicio.consulta(casaModificada.getIdPropietario());
        Casa casa = repository.findById(id).orElse(null);

        //Validacion
        if (propietario == null || casa == null) {
            return null;
        }

        //Seteo modificacion y persisto.
        Casa casaConDatosNuevos = creaCasaDesdeCasaAlta(casaModificada);
        casaConDatosNuevos.setPropietario(propietario);

        casa.editateConDatosDe(casaConDatosNuevos);

        repository.save(casa);

        log.info("Se edito casa: {}", casa);
        return casa;
    }

    @Override
    public void eliminacion(Integer id) {
        //Busco casa
        Casa casa = repository.findById(id).orElse(null);

        //Validacion
        if (casa == null) {
            log.error("No se puede eliminar casa " + id + ". La casa no existe");
            throw new CasaException("La casa no existe.");
        }

        //Eliminacion
        repository.delete(casa);
        log.warn("Se eliminó casa con ID: {}", id);
    }

    private Casa creaCasaDesdeCasaAlta(CasaAlta casaAlta) {
        LocalDateTime fechaDesde = FechaConverter.convertiCadena(casaAlta.getFechaDesde());
        LocalDateTime fechaHasta = FechaConverter.convertiCadena(casaAlta.getFechaHasta());
        return Casa.builder()
                .maxDias(casaAlta.getMaxDias())
                .minDias(casaAlta.getMinDias())
                .calle(casaAlta.getCalle())
                .pais(casaAlta.getPais())
                .precio(casaAlta.getPrecio())
                .numero(casaAlta.getNumero())
                .fechaDesde(fechaDesde)
                .fechaHasta(fechaHasta)
                .ciudad(casaAlta.getCiudad())
                .tipoVivienda(casaAlta.getTipoVivienda())
                .codPostal(casaAlta.getCodPostal())
                .build();
    }
}
