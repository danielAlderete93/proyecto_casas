package com.estancias.servicios;

import com.estancias.dto.ClienteAlta;
import com.estancias.entidades.Cliente;
import com.estancias.entidades.Usuario;
import com.estancias.repositorios.CRUDBaseRepository;
import com.estancias.servicios.interfaces.ClienteServicio;
import com.estancias.servicios.interfaces.UsuarioServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteServicio {
    private final CRUDBaseRepository<Cliente> repository;
    private final UsuarioServicio usuarioServicio;

    @Autowired
    public ClienteServiceImpl(CRUDBaseRepository<Cliente> repository, UsuarioServicio usuarioServicio) {
        this.repository = repository;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public Integer crearCliente(ClienteAlta clienteAlta) {

        //TODO: Validar
        //Exceptions

        //Crear usuario->tratar exception
        Usuario usuario = usuarioServicio.consulta(clienteAlta.getIdUsuario());

        //Si pasa el try->
        Cliente clienteCreado = creaClienteDesdeClienteAlta(clienteAlta);
        clienteCreado.setUsuario(usuario);

        Integer id = repository.guardar(clienteCreado);
        log.info("Se creó cliente con id:", id);
        //Guardarlo
        return id;

    }

    @Override
    public List<Cliente> consulta() {
        log.atInfo().log("Se busca a todos los clientes");
        return repository.listarTodas();
    }

    @Override
    public Cliente consulta(Integer idCliente) {
        log.atInfo().log("Se busca a cliente con id:" + idCliente);
        return repository.obtenerPorID(idCliente);
    }


    @Override
    public Cliente modificacion(Integer idCliente, ClienteAlta clienteAlta) {
        Cliente clienteAEditar = repository.obtenerPorID(idCliente);

        if (clienteAEditar == null) {
            return null;
        }


        Cliente clienteConDatosNuevos = creaClienteDesdeClienteAlta(clienteAlta);

        clienteAEditar.editateConDatosDe(clienteConDatosNuevos);

        log.info("Se actualizó el objeto: {}", clienteAEditar);
        return repository.obtenerPorID(idCliente);
    }

    @Override
    public boolean eliminacion(Integer idCliente) {
        boolean sePudoElimino = repository.borrar(idCliente);
        if (sePudoElimino) {
            log.warn("Se eliminó el objeto con ID: {}", idCliente);
        }

        return sePudoElimino;
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
