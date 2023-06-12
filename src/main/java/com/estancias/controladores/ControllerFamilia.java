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
@RequestMapping("/api/familia")
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

    //Alta
    @PostMapping(value = "/nuevo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String darAltaJson(@RequestBody FamiliaAlta familiaAlta, ModelMap modelo) {

        Familia familia = familiaServicio.crearFamilia(familiaAlta);

        modelo.put("exito", "El familia fue registrado correctamente!");
        modelo.put("familia", familia);


        return "familia/familiaCreado";

    }

    //ListarTodos
    @GetMapping(value = "/listar/todos")
    public String listarTodos(ModelMap modelo) {
        List<Familia> familias = familiaServicio.consulta();

        modelo.put("familias", familias);


        return "familia/familiaListado";

    }

    //ListarPorID
    @GetMapping(value = "/listar/{idFamilia}")
    public String listarSegunId(@PathVariable Integer idFamilia, ModelMap modelo) {
        Familia familia = familiaServicio.consulta(idFamilia);

        modelo.put("familia", familia);


        return "familia/familiaDetalle";

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


        return "redirect:/api/familia/listar/todos";

    }


}
