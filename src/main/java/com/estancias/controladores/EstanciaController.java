package com.estancias.controladores;

import com.estancias.dto.EstanciaAlta;
import com.estancias.entidades.Estancia;
import com.estancias.servicios.interfaces.EstanciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estancia")
public class EstanciaController {
    /*
     ReservaController
        Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para operar
        con la vista o portal para gestionar reservas de estancias (guardar/modificar, listar estancias
        reservadas/realizadas, eliminación).
     */
    private final EstanciaServicio estanciaServicio;

    @Autowired
    public EstanciaController(EstanciaServicio estanciaServicio) {
        this.estanciaServicio = estanciaServicio;
    }


    //Registro
    @GetMapping(value = "/registro")
    public String vistaRegistro(ModelMap modelo) {
        modelo.addAttribute("main", "estancia/main/registro");
        return "base";
    }

    @PostMapping(value = "/registro")
    public String registrar(@RequestParam("huesped") String huesped,
                            @RequestParam("fecha-desde") String fechaDesde,
                            @RequestParam("fecha-hasta") String fechaHasta
    ) {
        //TODO SE DEBE ARMAR DESDE EL LOGIN DE SPRINGBOOT
        EstanciaAlta estanciaAlta = EstanciaAlta.builder()
                .idCasa(1)
                .huesped(huesped)
                .fechaDesde(fechaDesde)
                .fechaHasta(fechaHasta)
                .idCliente(1)
                .build();

        Integer idEstancia = estanciaServicio.crearEstancia(estanciaAlta);


        return "redirect:/estancia/listar/" + idEstancia;

    }

    //ListarTodos
    @GetMapping(value = "/listar/todos")
    public String vistaListaTodos(ModelMap modelo) {
        List<Estancia> estancias = estanciaServicio.consulta();

        modelo.put("estancias", estancias);
        modelo.addAttribute("main", "estancia/main/listado");

        return "base";

    }

    //ListarPorID
    @GetMapping(value = "/listar/{idCliente}")
    public String vistaPorID(@PathVariable Integer idCliente, ModelMap modelo) {
        Estancia estancia = estanciaServicio.consulta(idCliente);

        modelo.put("estancia", estancia);
        modelo.addAttribute("main", "estancia/main/detalle");

        return "base";

    }

}
