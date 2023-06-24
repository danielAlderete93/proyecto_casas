package com.estancias.controladores.admin;


import com.estancias.entidades.Usuario;
import com.estancias.servicios.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/usuario")
public class UsuarioAdminController {
    private final UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioAdminController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping(value = "/listar/todos")
    public String listado(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.consulta();
        modelo.put("usuarios", usuarios);
        modelo.put("main", "listado");
        return "page/admin/base";

    }

}
