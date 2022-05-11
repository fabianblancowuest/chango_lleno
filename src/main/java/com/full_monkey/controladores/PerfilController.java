package com.full_monkey.controladores;

import com.full_monkey.servicios.PerfilServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Perfil")
public class PerfilController {

    @Autowired
    PerfilServicio ps;
    
    @GetMapping("/{id}")
    public String mostrarPerfil(@PathVariable String id,ModelMap modelo){
       try{
            modelo.addAttribute("perfil", ps.findById(id));
            return "perfil.html";
       }catch (Exception e){
           return "redirect:/";
       }
    }
    
    @GetMapping("/modificar/{id}")
    public String modificarPerfil(@PathVariable String id,ModelMap modelo){
       try{
            modelo.addAttribute("perfil", ps.findById(id));
            return "modif-perfil-html";
       }catch (Exception e){
           return "redirect:/";
       }
    }
    
    @PostMapping("/modificar/{id}")
    public String cambiarPerfil(@PathVariable String id,ModelMap modelo,@RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni, @RequestParam Date nacimiento, @RequestParam String email,  @RequestParam String domicilio, @RequestParam String fotoPerfil, @RequestParam Long tarjeta) throws Exception{
        try{
            ps.modifPerfil(id, nombre, apellido, dni, nacimiento, email, domicilio, fotoPerfil);
            return "redirect:/Perfil";
       }catch (Exception e){
           modelo.put("error", e.getMessage());
           modelo.addAttribute("perfil", ps.findById(id));
           return "modif-perfil-html";
       }
    }
}

