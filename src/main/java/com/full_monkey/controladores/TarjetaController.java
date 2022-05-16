package com.full_monkey.controladores;

import com.full_monkey.entidades.Tarjeta;
import com.full_monkey.entidades.Usuario;
import com.full_monkey.servicios.TarjetaServicio;
import com.full_monkey.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
@RequestMapping("/tarjeta")
public class TarjetaController {

    @Autowired
    private TarjetaServicio ts;
    @Autowired
    UsuarioServicio us;
    
    @GetMapping("/registro")
    public String registrarTarjeta(){
        return "";
    }
    
    @PostMapping("/registro")
    public String registrarTarjeta(@RequestParam String titular, @RequestParam String fotoempresa,@RequestParam Long numero,@RequestParam Integer clave,@RequestParam String expiracion,HttpSession session) throws Exception{
        try{
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            ts.crearTarjeta(titular, fotoempresa, numero, clave, clave, expiracion, u);
        return ""; 
        }catch(Exception e){
            System.err.println(e.getMessage());
         return "";   
        }
    }
    
    @GetMapping
    public String tarjetas(ModelMap modelo, HttpSession session){
        try{
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            List<Tarjeta> lt = ts.findByUser(u);
            modelo.addAttribute("tarjetas", lt);
            return "";
        }catch(Exception e){
            System.err.println(e.getMessage());
            return "";
        }
    }
    
    @GetMapping("/{id}")
    public String tarjetaId(@PathVariable String id,RedirectAttributes ra){
       try{
          ts.findById(id);
          ra.addFlashAttribute("actual", ts.findById(id));
          return "redirect:/compra/crearCompra";
       }catch(Exception e){
           System.err.println(e.getMessage());
           return "redirect:/tarjeta";
       } 
    }
}

