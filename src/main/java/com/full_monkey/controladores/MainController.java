package com.full_monkey.controladores;

<<<<<<< HEAD
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
=======
import com.full_monkey.entidades.Compra;
import com.full_monkey.entidades.Usuario;
import com.full_monkey.servicios.CarritoServicio;
import com.full_monkey.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
>>>>>>> 675e954dc6c4d338c8aabf6c20ea9dc95cd50573
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
<<<<<<< HEAD
private UsuarioServicio usuarioServicio;
=======

    @Autowired
    private UsuarioServicio usuarioServicio;

>>>>>>> 675e954dc6c4d338c8aabf6c20ea9dc95cd50573
    @GetMapping
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o Contraseña incorrectos");
            return "inicioSesion.html";
        }
        if (logout != null) {
            model.put("logout", "Desconectado correctamente");
            return "inicioSesion.html";
        }
         return "redirect:/producto/listaDeProductos";
    }
     @GetMapping("/registro")
    public String registrarse() {
        return "registro";
    }

    @PostMapping("/registro")
    public String guardarUsuario(@RequestParam String username, @RequestParam String password, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni, @RequestParam Date nacimiento, @RequestParam String email, @RequestParam String domicilio, @RequestParam String fotoPerfil, @RequestParam List<Compra>historial, @RequestParam Carrito pendiente){
        try {

            Usuario u = usuarioServicio.registroUsuario(username, password, nombre, apellido,  dni, nacimiento,  email, domicilio, fotoPerfil,  historial,  pendiente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "registro";
    }

    @GetMapping("/registro")
    public String registrarse(ModelMap model) {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String guardarUsuario(@RequestParam String username, @RequestParam String password, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni, @ModelAttribute Date nacimiento, @RequestParam String email, @RequestParam String domicilio, @RequestParam(required = false) String fotoPerfil, @RequestParam String pregunta) {
        try {
            List<Compra> historial = new ArrayList();
            usuarioServicio.registroUsuario(username, password, nombre, apellido, historial, dni, nacimiento, email, domicilio, fotoPerfil,pregunta);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally{
            return "redirect:/login";
        }
    }
    
    
}
