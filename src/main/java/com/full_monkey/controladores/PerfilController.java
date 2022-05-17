package com.full_monkey.controladores;

import com.full_monkey.entidades.Usuario;
import com.full_monkey.servicios.PerfilServicio;
import com.full_monkey.servicios.UsuarioServicio;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    PerfilServicio ps;
    @Autowired
    UsuarioServicio us;
    
    @GetMapping
    public String mostrarPerfil(ModelMap modelo,HttpSession session){
       try{
           Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            modelo.addAttribute("perfil", u);
            return "verPerfil.html";
       }catch (Exception e){
           e.printStackTrace();
           return "redirect:/";
       }
    }
    
    @GetMapping("/modificar")
    public String modificarPerfil(ModelMap modelo,HttpSession session){
       try{
           Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            modelo.addAttribute("perfil", u);
            return "modificarPerfil.html";
       }catch (Exception e){
           return "redirect:/";
       }
    }
    
    @PostMapping("/modificar")
    public String cambiarPerfil(HttpSession session,ModelMap modelo,@RequestParam String username,@RequestParam String password,@RequestParam String nombre,@RequestParam String apellido,@RequestParam Long dni,@ModelAttribute Date nacimiento,@RequestParam String email,@RequestParam String domicilio,@RequestParam(required = false) String fotoPerfil) throws Exception{
        try{
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            us.modifUsuario(u.getId(), username, password, u.getPerfil().getId(), nombre, apellido, dni, nacimiento, email, domicilio, fotoPerfil);
            return "redirect:/Perfil";
       }catch (Exception e){
           modelo.put("error", e.getMessage());
           
           Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            modelo.addAttribute("perfil", u);
           return "modificarPerfil.html";
       }
    }
}

