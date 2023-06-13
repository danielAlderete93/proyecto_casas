package com.estancias.controladores;

import com.estancias.dto.ClienteAlta;
import com.estancias.entidades.Cliente;
import com.estancias.servicios.interfaces.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteServicio clienteServicio;

    @Autowired
    public ClienteController(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    //Registro
    @GetMapping(value = "/registro")
    public String vistaRegistro(ModelMap modelo) {
        modelo.addAttribute("main", "cliente/main/registro");
        return "base";
    }

    @PostMapping(value = "/registro")
    public String registrar(@RequestParam("calle") String calle,
                            @RequestParam("nombre") String nombre,
                            @RequestParam("pais") String pais,
                            @RequestParam("ciudad") String ciudad,
                            @RequestParam("codigo-postal") String codPostal,
                            @RequestParam("email") String email,
                            @RequestParam("numero") Integer numero) {
        //TODO SE DEBE ARMAR DESDE EL LOGIN DE SPRINGBOOT
        ClienteAlta clienteAlta = ClienteAlta.builder()
                .calle(calle)
                .nombre(nombre)
                .pais(pais)
                .numero(numero)
                .ciudad(ciudad)
                .codPostal(codPostal)
                .email(email)
                .idUsuario(802)
                .build();

        Integer idCliente = clienteServicio.crearCliente(clienteAlta);


        return "redirect:/cliente/listar/" + idCliente;

    }

    //ListarTodos
    @GetMapping(value = "/listar/todos")
    public String vistaListaTodos(ModelMap modelo) {
        List<Cliente> clientes = clienteServicio.consulta();

        modelo.put("clientes", clientes);
        modelo.addAttribute("main", "cliente/main/listado");

        return "base";

    }

    //ListarPorID
    @GetMapping(value = "/listar/{idCliente}")
    public String vistaPorID(@PathVariable Integer idCliente, ModelMap modelo) {
        Cliente cliente = clienteServicio.consulta(idCliente);

        modelo.put("cliente", cliente);
        modelo.addAttribute("main", "cliente/main/detalle");

        return "base";

    }


}
