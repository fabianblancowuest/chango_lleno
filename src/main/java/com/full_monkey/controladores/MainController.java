package com.full_monkey.controladores;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Compra;
import com.full_monkey.entidades.Perfil;
import com.full_monkey.entidades.Usuario;
import com.full_monkey.enums.Role;
import com.full_monkey.servicios.UsuarioServicio;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
private UsuarioServicio usuarioServicio;
    @GetMapping
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o Contraseña incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Desconectado correctamente");
        }
        return "login";
    }
     @GetMapping("/")
    public String registrarse() {
        return "registro";
    }

    @PostMapping("/")
    public String guardarUsuario(@RequestParam String username, @RequestParam String password, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni, @RequestParam Date nacimiento, @RequestParam String email, @RequestParam String domicilio, @RequestParam String fotoPerfil, @RequestParam List<Compra>historial, @RequestParam Carrito pendiente){
        try {

            Usuario u = usuarioServicio.registroUsuario(username, password, nombre, apellido,  dni, nacimiento,  email, domicilio, fotoPerfil,  historial,  pendiente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/login";
    }

}

