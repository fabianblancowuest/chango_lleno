package com.full_monkey.controladores;

import com.full_monkey.entidades.Producto;
import com.full_monkey.entidades.User;
import com.full_monkey.repository.ProductoRepository;
import com.full_monkey.servicios.CarritoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    CarritoServicio carritoServicio;
    
    @Autowired
    ProductoRepository productoRepository;

    @PostMapping("/cargar")
    public String cargarCarrito(ModelMap modelo, @RequestParam String idProducto, @RequestParam Integer unidades, HttpSession session) {
        try {
            if (unidades == 0 || unidades == null) {
                throw new Exception();
            }
            User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity

            List<Producto> productos = new ArrayList();
            Producto prod = productoRepository.findById(idProducto).get();
            prod.setStock(prod.getStock() - unidades);
            for (int i = 0; i < unidades; i++) {
                productos.add(prod);
            }
            carritoServicio.poner(u.getPerfil().getPendiente().getId(), productos);
            return "";
        } catch (Exception ex) {
            modelo.put("error", unidades > 0 ? "No se cargaron los productos" : "No ha seleccionado ningún producto para agregar al carrito");
            return "";
        }
    }

    @PostMapping("/sacar")
    public String sacarDelCarrito(ModelMap modelo, @RequestParam String idProducto, @RequestParam Integer unidades, HttpSession session) {
        try {
            if (unidades == 0 || unidades == null) {
                throw new Exception();
            }
            User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity
            
            List<Producto> productos = new ArrayList();
            Producto prod = productoRepository.findById(idProducto).get();
            prod.setStock(prod.getStock() + unidades);
            for (int i = 0; i < unidades; i++) {
                productos.add(prod);
            }
            carritoServicio.sacar(u.getPerfil().getPendiente().getId(), productos);
            return "";
        } catch (Exception ex) {
            modelo.put("error", unidades > 0 ? "No se cargaron los productos" : "No ha seleccionado ningún producto para eliminar del carrito");
            return "";
        }
    }
    
    @PostMapping("/elimanar")
    public String eliminarCarrito(ModelMap modelo, HttpSession session){
        User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity  
        //si se crea con el usuario no lo puedo eliminar  
        try {
            carritoServicio.eliminarCarrito(u.getPerfil().getPendiente().getId());
        } catch (Exception ex) {
            Logger.getLogger(CarritoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    @PostMapping("/precioEnvio")
    public String precioEnvio(ModelMap modelo, HttpSession session, Double precioEnvio){
        User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity 
        try { 
            carritoServicio.precioDeEnvio(u.getPerfil().getPendiente().getId(), precioEnvio);          
        } catch (Exception ex) { 
            Logger.getLogger(CarritoController.class.getName()).log(Level.SEVERE, null, ex);
        }
      return ""; 
    }
    
    public String modificarPrecioEnvio(ModelMap modelo, HttpSession session, Double precioEnvio){
         User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity 
         try { 
            carritoServicio.modificarPrecioDeEnvio(u.getPerfil().getPendiente().getId(), precioEnvio);   
        } catch (Exception ex) { 
            Logger.getLogger(CarritoController.class.getName()).log(Level.SEVERE, null, ex);
        }
      return ""; 
    }
    
    @GetMapping("/mostrarProductos")
    public String mostrarProductosDelCarrito(ModelMap modelo, HttpSession session){
        User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity  
        
        List<Producto> producto = productoRepository.findAll();
        
        modelo.addAttribute("listaProductos",producto);
        return "";
        
    }
}
