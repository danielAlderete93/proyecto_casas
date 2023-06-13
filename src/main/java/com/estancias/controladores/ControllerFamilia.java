package com.estancias.controladores;

import com.estancias.dto.FamiliaAlta;
import com.estancias.entidades.Familia;
import com.estancias.servicios.interfaces.FamiliaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/familia")
public class ControllerFamilia {
    private final FamiliaServicio familiaServicio;

    @Autowired
    public ControllerFamilia(FamiliaServicio familiaServicio) {
        this.familiaServicio = familiaServicio;
    }

    /*
    Administrar familias: cargar datos de una familia, modificar datos, consultar familias, eliminar
    familias que no ofrecen más sus viviendas para estancias. Las familias deben darse de alta una
    vez que hayan sido registradas como usuario.
     */

   @GetMapping(value = "/registro")
    public String vistaRegistro(ModelMap modelo) {
        modelo.addAttribute("main", "familia/main/registro");
        return "base";
    }

    @PostMapping(value = "/registro")
    public String registrar(@RequestParam String nombre,
                            @RequestParam("edad-min") Integer edadMin,
                            @RequestParam("edad-max") Integer edadMax,
                            @RequestParam("num-hijos") Integer numHijos,
                            @RequestParam String email) {
        //TODO SE DEBE ARMAR DESDE EL LOGIN DE SPRINGBOOT
        FamiliaAlta familiaAlta = FamiliaAlta.builder()
                .numHijos(numHijos)
                .edadMax(edadMax)
                .edadMin(edadMin)
                .email(email)
                .idUsuario(1102)
                .nombre(nombre)
                .build();

        Familia familia = familiaServicio.crearFamilia(familiaAlta);


        return "redirect:/usuario/login";

    }

    //ListarTodos
    @GetMapping(value = "/listar/todos")
    public String vistaListaTodos(ModelMap modelo) {
        List<Familia> familias = familiaServicio.consulta();

        modelo.put("familias", familias);
        modelo.addAttribute("main", "familia/main/listado");

        return "base";

    }

    //ListarPorID
    @GetMapping(value = "/listar/{idFamilia}")
    public String vistaListaPorID(@PathVariable Integer idFamilia, ModelMap modelo) {
        Familia familia = familiaServicio.consulta(idFamilia);

        modelo.put("familia", familia);
        modelo.addAttribute("main", "familia/main/detalle");

        return "base";

    }

    //ListarPorID
    @PutMapping(value = "/editar/{idFamilia}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String listarSegunId(@PathVariable Integer idFamilia, @RequestBody FamiliaAlta familiaAlta, ModelMap modelo) {
        Familia familia = familiaServicio.modificacion(idFamilia, familiaAlta);

        modelo.put("familia", familia);


        return "familia/familiaDetalle";

    }


    //BajaDeFamilia
    @DeleteMapping(value = "/baja/{idFamilia}")
    public String darBaja(@PathVariable Integer idFamilia) {

        familiaServicio.eliminacion(idFamilia);
        //todo:vista


        return "redirect:/familia/listar/todos";

    }


}
