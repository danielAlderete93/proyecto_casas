package com.estancias.servicios;

import com.estancias.dto.ClienteAlta;
import com.estancias.entidades.Cliente;
import com.estancias.entidades.Usuario;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import com.estancias.servicios.interfaces.ClienteServicio;
import com.estancias.servicios.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteServicio {
    private final CRUDBaseRepository<Cliente> repository;
    private final UsuarioServicio usuarioServicio;

    @Autowired
    public ClienteServiceImpl(CRUDBaseRepository<Cliente> repository, UsuarioServicio usuarioServicio) {
        this.repository = repository;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public Cliente crearCliente(ClienteAlta clienteAlta) {

        //TODO: Validar
        //Exceptions

        //Crear usuario->tratar exception
        Usuario usuario = usuarioServicio.altaUsuario(clienteAlta.getUsuario());

        //Si pasa el try->
        Cliente clienteCreado = creaClienteDesdeClienteAlta(clienteAlta);
        clienteCreado.setUsuario(usuario);

        Integer id = repository.guardar(clienteCreado);

        //Guardarlo
        return repository.obtenerPorID(id);

    }

    @Override
    public Cliente consulta(Integer idCliente) {
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

        return repository.obtenerPorID(idCliente);
    }

    @Override
    public boolean eliminacion(Integer idCliente) {
        return repository.borrar(idCliente);
    }

    private Cliente creaClienteDesdeClienteAlta(ClienteAlta clienteAlta){
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
