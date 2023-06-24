package com.estancias.servicios;

import com.estancias.dto.EstanciaAlta;
import com.estancias.entidades.Casa;
import com.estancias.entidades.Cliente;
import com.estancias.entidades.Estancia;
import com.estancias.excepciones.ClienteException;
import com.estancias.excepciones.EstanciaException;
import com.estancias.repositorios.EstanciaRepositorio;
import com.estancias.servicios.interfaces.CasaServicio;
import com.estancias.servicios.interfaces.ClienteServicio;
import com.estancias.servicios.interfaces.EstanciaServicio;
import com.estancias.utils.FechaConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class EstanciaServicioImpl implements EstanciaServicio {
    private final EstanciaRepositorio repository;
    private final CasaServicio casaServicio;
    private final ClienteServicio clienteServicio;

    @Autowired
    public EstanciaServicioImpl(EstanciaRepositorio repository, CasaServicio casaServicio, ClienteServicio clienteServicio) {
        this.repository = repository;
        this.casaServicio = casaServicio;
        this.clienteServicio = clienteServicio;
    }

    @Override
    public Integer crearEstancia(EstanciaAlta estanciaAlta) {
        //Busco cliente y casa
        Cliente cliente = clienteServicio.consulta(estanciaAlta.getIdCliente());
        Casa casa = casaServicio.consulta(estanciaAlta.getIdCasa());

        //Valido
        if (cliente == null || casa == null) {
            log.error("No se pudo crear estancia. Casa/Cliente no existen.");
            throw new ClienteException("No se pudo crear estancia");
        }

        //Creo y persisto estancia
        Estancia estancia = creaEstanciaDesdeEstanciaAlta(estanciaAlta);
        estancia.setCasa(casa);
        estancia.setCliente(cliente);


        Integer idEstancia = repository.save(estancia).getId();
        log.info("Se creó estancia con id:", idEstancia);
        //Devuelvo el usuario guardado
        return idEstancia;
    }

    @Override
    public Estancia consulta(Integer idEstancia) {
        log.info("Se consulta por estancia id:{}", idEstancia);
        return repository.findById(idEstancia).orElse(null);
    }

    @Override
    public List<Estancia> consulta() {
        log.info("Se consulta por todas las estancias");
        return repository.findAll();
    }

    @Override
    public Estancia modificacion(Integer idEstancia, EstanciaAlta estanciaAlta) {
        //Busco cliente,casa y estancia
        Cliente cliente = clienteServicio.consulta(estanciaAlta.getIdCliente());
        Casa casa = casaServicio.consulta(estanciaAlta.getIdCasa());
        Estancia estancia = repository.findById(idEstancia).orElse(null);

        //Valido
        if (cliente == null || casa == null || estancia == null) {
            return null;
        }
        //Seteo modificacion y persisto
        Estancia estanciaConDatosNuevos = creaEstanciaDesdeEstanciaAlta(estanciaAlta);
        estanciaConDatosNuevos.setCliente(cliente);
        estanciaConDatosNuevos.setCasa(casa);

        estancia.editateConDatosDe(estanciaConDatosNuevos);

        repository.save(estancia);
        log.info("Se actualizó el objeto: {}", estancia);
        return estancia;
    }

    @Override
    public void eliminacion(Integer id) {
        //Busco cliente
        Estancia estancia = repository.findById(id).orElse(null);

        //Validacion
        if (estancia == null) {
            log.error("No se puede eliminar estancia " + id + ". El estancia no existe");
            throw new EstanciaException("El estancia no existe.");
        }

        //Eliminacion
        repository.delete(estancia);
        log.warn("Se eliminó casa con ID: {}", id);
    }

    private Estancia creaEstanciaDesdeEstanciaAlta(EstanciaAlta estanciaAlta) {

        LocalDateTime fechaDesde = FechaConverter.convertiCadena(estanciaAlta.getFechaDesde());
        LocalDateTime fechaHasta = FechaConverter.convertiCadena(estanciaAlta.getFechaHasta());

        return Estancia.builder()
                .huesped(estanciaAlta.getHuesped())
                .fechaDesde(fechaDesde)
                .fechaHasta(fechaHasta)
                .build();
    }
}
