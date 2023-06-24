package com.estancias.servicios;

import com.estancias.dto.ClienteAlta;
import com.estancias.entidades.Cliente;
import com.estancias.entidades.Usuario;
import com.estancias.excepciones.ClienteException;
import com.estancias.repositorios.ClienteRepositorio;
import com.estancias.servicios.interfaces.ClienteServicio;
import com.estancias.servicios.interfaces.UsuarioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteServicio {
    private final ClienteRepositorio repository;
    private final UsuarioServicio usuarioServicio;

    @Autowired
    public ClienteServiceImpl(ClienteRepositorio repository, UsuarioServicio usuarioServicio) {
        this.repository = repository;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public Integer crearCliente(ClienteAlta clienteAlta) {
        //Crear usuario->tratar exception
        Usuario usuario = usuarioServicio.consulta(clienteAlta.getIdUsuario());

        if (usuario == null) {
            log.error("No se pudo crear cliente. No existe usuario.");
            throw new ClienteException("No se pudo crear cliente");
        }


        Cliente clienteCreado = creaClienteDesdeClienteAlta(clienteAlta);
        clienteCreado.setUsuario(usuario);

        Integer id = repository.save(clienteCreado).getId();
        log.info("Se creó cliente con id:", id);
        //Guardarlo
        return id;

    }

    @Override
    public List<Cliente> consulta() {
        log.info("Se busca a todos los clientes");
        return repository.findAll();
    }

    @Override
    public Cliente consulta(Integer idCliente) {
        log.info("Se busca a cliente con id:" + idCliente);
        return repository.findById(idCliente).orElse(null);
    }


    @Override
    public Cliente modificacion(Integer idCliente, ClienteAlta clienteAlta) {
        //Busco cliente
        Cliente clienteAEditar = repository.findById(idCliente).orElse(null);

        //Valido
        if (clienteAEditar == null) {
            return null;
        }

        //Seteo modificaciones y persisto
        Cliente clienteConDatosNuevos = creaClienteDesdeClienteAlta(clienteAlta);

        clienteAEditar.editateConDatosDe(clienteConDatosNuevos);
        repository.save(clienteAEditar);

        log.info("Se actualizó el objeto: {}", clienteAEditar);
        return clienteAEditar;
    }

    @Override
    public void eliminacion(Integer id) {
        //Busco cliente
        Cliente cliente = repository.findById(id).orElse(null);

        //Validacion
        if (cliente == null) {
            log.error("No se puede eliminar cliente " + id + ". El cliente no existe");
            throw new ClienteException("El cliente no existe.");
        }

        //Eliminacion
        repository.delete(cliente);
        log.warn("Se eliminó casa con ID: {}", id);
    }

    private Cliente creaClienteDesdeClienteAlta(ClienteAlta clienteAlta) {
        return Cliente.builder()
                .numero(clienteAlta.getNumero())
                .pais(clienteAlta.getPais())
                .nombre(clienteAlta.getNombre())
                .email(clienteAlta.getEmail())
                .calle(clienteAlta.getCalle())
                .ciudad(clienteAlta.getCiudad())
                .codPostal(clienteAlta.getCodPostal())
                .build();
    }
}
