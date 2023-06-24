package com.estancias.servicios;

import com.estancias.dto.CasaAlta;
import com.estancias.entidades.Casa;
import com.estancias.entidades.Familia;
import com.estancias.repositorios.CRUDBaseRepository;
import com.estancias.servicios.interfaces.CasaServicio;
import com.estancias.servicios.interfaces.FamiliaServicio;
import com.estancias.utils.FechaConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CasaServicioImpl implements CasaServicio {

    private final CRUDBaseRepository<Casa> repository;
    private final FamiliaServicio familiaServicio;

    @Autowired
    public CasaServicioImpl(CRUDBaseRepository<Casa> repository, FamiliaServicio familiaServicio) {
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


        Integer id = repository.guardar(casa);
        log.info("Se creó casa con id:", id);
        //Guardarlo
        return id;
    }

    @Override
    public Casa consulta(Integer idCasa) {
        log.atInfo().log("Se busca a casa con id:" + idCasa);
        return repository.obtenerPorID(idCasa);
    }

    @Override
    public List<Casa> consulta() {
        log.atInfo().log("Se busca a usuario todas las casas");
        return repository.listarTodas();
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
