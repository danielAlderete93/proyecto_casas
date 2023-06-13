package com.estancias.controladores;

import com.estancias.dto.CasaAlta;
import com.estancias.entidades.Casa;
import com.estancias.servicios.interfaces.CasaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/casa")
public class CasaController {
    private final CasaServicio casaServicio;

    @Autowired
    public CasaController(CasaServicio casaServicio) {
        this.casaServicio = casaServicio;
    }


    //Registro
    @GetMapping(value = "/registro")
    public String vistaRegistro(ModelMap modelo) {
        modelo.addAttribute("main", "casa/main/registro");
        return "base";
    }

    @PostMapping(value = "/registro")
    public String registrar(@RequestParam("calle") String calle,
                            @RequestParam("pais") String pais,
                            @RequestParam("ciudad") String ciudad,
                            @RequestParam("codigo-postal") String codPostal,
                            @RequestParam("fecha-desde") String fechaDesde,
                            @RequestParam("fecha-hasta") String fechaHasta,
                            @RequestParam("dia-min") Integer diaMin,
                            @RequestParam("dia-max") Integer diaMax,
                            @RequestParam("precio") Double precio,
                            @RequestParam("tipo-vivienda") String tipoVivienda,
                            @RequestParam("numero") Integer numero) {
        //TODO SE DEBE ARMAR DESDE EL LOGIN DE SPRINGBOOT


        CasaAlta casaAlta = CasaAlta.builder()
                .calle(calle)
                .pais(pais)
                .numero(numero)
                .ciudad(ciudad)
                .codPostal(codPostal)
                .fechaDesde(fechaDesde)
                .fechaHasta(fechaHasta)
                .minDias(diaMin)
                .maxDias(diaMax)
                .precio(precio)
                .tipoVivienda(tipoVivienda)
                .idPropietario(1)
                .build();

        Integer idCasa = casaServicio.crearCasa(casaAlta);


        return "redirect:/casa/listar/" + idCasa;

    }

    //ListarTodos
    @GetMapping(value = "/listar/todos")
    public String vistaListaTodos(ModelMap modelo) {
        List<Casa> casas = casaServicio.consulta();

        modelo.put("casas", casas);
        modelo.addAttribute("main", "casa/main/listado");

        return "base";

    }

    //ListarPorID
    @GetMapping(value = "/listar/{idCliente}")
    public String vistaPorID(@PathVariable Integer idCliente, ModelMap modelo) {
        Casa casa = casaServicio.consulta(idCliente);

        modelo.put("casa", casa);
        modelo.addAttribute("main", "casa/main/detalle");

        return "base";

    }
}
