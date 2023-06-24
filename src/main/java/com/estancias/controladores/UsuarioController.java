package com.estancias.controladores;

import com.estancias.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;
import com.estancias.servicios.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    /*
    UsuarioController
    Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para operar
    con la vista del usuario diseñada para la gestión de usuarios (dar de alta un usuario, cambiar clave,
    listar usuarios registrados, dar de baja un usuario).
     */

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    //Login por Form
    @GetMapping(value = "/login")
    public String login() {
        return "login/login";
    }

    //Registro por Form
    @GetMapping(value = "/registro")
    public String vistaRegistro(ModelMap modelo) {
        modelo.addAttribute("main", "usuario/main/registro");
        return "base";
    }

    @PostMapping(value = "/registro")
    public String crearPorForm(@RequestParam String alias,
                               @RequestParam String clave,
                               @RequestParam String email,
                               ModelMap modelo) {
        UsuarioAlta usuarioAlta = UsuarioAlta.builder().email(email).alias(alias).clave(clave).build();
        Integer idUsuario = usuarioServicio.altaUsuario(usuarioAlta);

        modelo.addAttribute("mensaje", "¡Usuario fue dado de alta exitosamente!");
        modelo.addAttribute("tipoAlerta", "success");
        modelo.addAttribute("main", "usuario/main/seleccionTipoUsuario");
        modelo.addAttribute("idUsuario", idUsuario);


        return "base";

    }

    @PostMapping(value = "/seleccion")
    public String seleccionUsuario(@RequestParam("tipo") String tipo, RedirectAttributes modelo) {
        //if (tipo.equalsIgnoreCase("familia")) {
        modelo.addFlashAttribute("mensaje", "Se seleccionó familia");
        modelo.addFlashAttribute("tipoAlerta", "success");
        modelo.addFlashAttribute("main", "usuario/main/crear");
        return "redirect:/familia/registro";

        //}
       /* if (tipo.equalsIgnoreCase("cliente")) {
            modelo.addFlashAttribute("mensaje", "Se seleccionó familia");
            modelo.addFlashAttribute("tipoAlerta", "success");
            modelo.addAttribute("main", "usuario/main/crear");
        }
        return "base";

        */
    }


    //ListarTodos
    @GetMapping(value = "/listar/todos")
    public String listado(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.consulta();

        modelo.put("usuarios", usuarios);
        modelo.addAttribute("main", "usuario/main/listado");

        return "base";

    }

    //ListarPorID
    @GetMapping(value = "/listar/{idUsuario}")
    public String detalle(@PathVariable Integer idUsuario, ModelMap modelo) {
        Usuario usuario = usuarioServicio.consulta(idUsuario);

        modelo.put("estaActivo", usuario.estaActivo());
        modelo.put("usuario", usuario);
        modelo.addAttribute("main", "usuario/main/detalle");

        return "base";

    }

    //CambiarClave
    @PutMapping(value = "/edita/{idUsuario}/clave")
    public String cambiarClave(@PathVariable Integer idUsuario, @RequestBody String nuevaClave, RedirectAttributes modelo) {
        boolean seCambioClave = usuarioServicio.cambiarClaveNueva(idUsuario, nuevaClave);

        if (seCambioClave) {
            modelo.addFlashAttribute("mensaje", "¡La clave del usuario ha sido cambiada exitosamente!");
            modelo.addFlashAttribute("tipoAlerta", "success");
        } else {
            modelo.addFlashAttribute("mensaje", "No se pudo cambiar la clave del usuario. Por favor, intenta nuevamente.");
            modelo.addFlashAttribute("tipoAlerta", "danger");
        }


        return "redirect:/api/usuario/listar/" + idUsuario;

    }

    //BajaDeUsuario
    @PostMapping(value = "/baja/{idUsuario}")
    public String darBaja(@PathVariable Integer idUsuario, RedirectAttributes modelo) {
        boolean seDioBaja = usuarioServicio.baja(idUsuario);

        if (seDioBaja) {
            modelo.addFlashAttribute("mensaje", "¡Usuario dado de baja exitosamente!");
            modelo.addFlashAttribute("tipoAlerta", "success");
        } else {
            modelo.addFlashAttribute("mensaje", "No se pudo dar de baja al usuario. Por favor, intenta nuevamente.");
            modelo.addFlashAttribute("tipoAlerta", "danger");
        }


        return "redirect:/api/usuario/listar/" + idUsuario;

    }

}
