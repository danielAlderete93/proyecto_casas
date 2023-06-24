package com.estancias.servicios;

import com.estancias.dto.EstanciaAlta;
import com.estancias.entidades.Casa;
import com.estancias.entidades.Cliente;
import com.estancias.entidades.Estancia;
import com.estancias.repositorios.CRUDBaseRepository;
import com.estancias.servicios.interfaces.CasaServicio;
import com.estancias.servicios.interfaces.ClienteServicio;
import com.estancias.servicios.interfaces.EstanciaServicio;
import com.estancias.utils.FechaConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstanciaServicioImpl implements EstanciaServicio {
    private CRUDBaseRepository<Estancia> repository;
    private CasaServicio casaServicio;
    private ClienteServicio clienteServicio;

    @Override
    public Integer crearEstancia(EstanciaAlta estanciaAlta) {
        Cliente cliente = clienteServicio.consulta(estanciaAlta.getIdCliente());
        Casa casa = casaServicio.consulta(estanciaAlta.getIdCasa());

        if (cliente == null || casa == null) {
            return null;
        }

        Estancia estancia = creaEstanciaDesdeEstanciaAlta(estanciaAlta);
        estancia.setCasa(casa);
        estancia.setCliente(cliente);


        Integer idEstancia = repository.guardar(estancia);

        //Devuelvo el usuario guardado
        return idEstancia;
    }

    @Override
    public Estancia consulta(Integer idEstancia) {
        return repository.obtenerPorID(idEstancia);
    }

    @Override
    public List<Estancia> consulta() {
        return repository.listarTodas();
    }

    @Override
    public Estancia modificacion(Integer idEstancia, EstanciaAlta estanciaAlta) {
        Cliente cliente = clienteServicio.consulta(estanciaAlta.getIdCliente());
        Casa casa = casaServicio.consulta(estanciaAlta.getIdCasa());
        Estancia estanciaModificada = repository.obtenerPorID(idEstancia);

        if (cliente == null || casa == null || estanciaModificada == null) {
            return null;
        }
        Estancia estanciaConDatosNuevos = creaEstanciaDesdeEstanciaAlta(estanciaAlta);
        estanciaConDatosNuevos.setCliente(cliente);
        estanciaConDatosNuevos.setCasa(casa);

        estanciaModificada.editateConDatosDe(estanciaConDatosNuevos);

        return repository.obtenerPorID(idEstancia);
    }

    @Override
    public boolean eliminacion(Integer idEstancia) {
        return repository.borrar(idEstancia);
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
