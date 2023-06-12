package com.estancias.controladores;

import com.estancias.dto.UsuarioAlta;
import com.estancias.entidades.Usuario;
import com.estancias.servicios.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/usuario")
public class ControllerUsuario {
    /*
    UsuarioController
    Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para operar
    con la vista del usuario diseñada para la gestión de usuarios (dar de alta un usuario, cambiar clave,
    listar usuarios registrados, dar de baja un usuario).
     */

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public ControllerUsuario(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    //AltaUsuario
    @PostMapping(value = "/nuevo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String crear(@RequestBody UsuarioAlta usuarioAlta, ModelMap modelo) {
        Usuario usuario = usuarioServicio.altaUsuario(usuarioAlta);

        modelo.put("exito", "El usuario fue registrado correctamente!");
        modelo.put("usuario", usuario);
        modelo.addAttribute("main", "usuario/main/crear");


        return "base";

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
