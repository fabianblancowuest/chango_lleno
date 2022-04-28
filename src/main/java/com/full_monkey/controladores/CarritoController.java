package com.full_monkey.controladores;

import com.full_monkey.entidades.Producto;
import com.full_monkey.entidades.User;
import com.full_monkey.servicios.CarritoServicio;
import com.full_monkey.servicios.ProductoService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    CarritoServicio carritoServicio;

    @Autowired
    ProductoService productoServicio;

    @GetMapping("/crearCarrito")
    public String crearCarrito(HttpSession session) {
        carritoServicio.crearCarrito();
        return "";
    }

    @GetMapping("/cargar")
    public String cargarCarrito() {
        return " ";
    }

    @PostMapping("/cargar/{idProducto}")
    public String cargarCarrito(ModelMap modelo, @PathVariable String idProducto, @RequestParam Integer unidades, HttpSession session) {
        try {
            if (unidades == 0 || unidades == null) {
                throw new Exception();
            }
            User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity

            List<Producto> productos = new ArrayList();
            Producto prod = productoServicio.getOne(idProducto);
            prod.setStock(prod.getStock() - unidades);
            prod.setUnidades(prod.getUnidades()+unidades);
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

    @GetMapping("/sacar")
    public String sacarDelCarrito(ModelMap modelo, HttpSession session) {
        User u = (User) session.getAttribute("usuariosession");

        List<Producto> producto = productoServicio.findAll();

        modelo.addAttribute("listaProductos", producto);
        return "";
    }
    
    @PostMapping("/sacar/{idProducto}")
    public String sacarDelCarrito(ModelMap modelo, @PathVariable String idProducto, @RequestParam Integer unidades, HttpSession session) {
        try {
            if (unidades == 0 || unidades == null) {
                throw new Exception();
            }
            User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity

            List<Producto> productos = new ArrayList();
            Producto prod = productoServicio.getOne(idProducto);
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

    @GetMapping("/eliminar/{id}")
    public String eliminarCarrito(@PathVariable String id){
        try {
            carritoServicio.eliminarCarrito(id);
            return "";
        } catch (Exception ex) {
            return "";
        }
    }
    
    @GetMapping("/precioEnvio")
    public String seleccionarPrecioEnvio() {

        return "";
    }

    @PostMapping("/precioEnvio")
    public String precioEnvio(ModelMap modelo, HttpSession session, Double precioEnvio) {
        User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity 
        try {
            carritoServicio.precioDeEnvio(u.getPerfil().getPendiente().getId(), precioEnvio);
            return "";
        } catch (Exception ex) {
            return "";
        }
    }

    @GetMapping("/moificarEnvio")
    public String mostrarPrecioEnvio(ModelMap modelo, HttpSession session) {
        User u = (User) session.getAttribute("usuariosession");

        modelo.put("precioEnvio", u.getPerfil().getPendiente().getPrecio_envio());
        return "";
    }
    
    @PostMapping("/modificarEnvio")
    public String modificarPrecioEnvio(ModelMap modelo, HttpSession session, Double precioEnvio) {
        User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity 
        try {
            carritoServicio.modificarPrecioDeEnvio(u.getPerfil().getPendiente().getId(), precioEnvio);
            return "";
        } catch (Exception ex) {
         return "";   
        }
    }

    @GetMapping("/mostrarProductos")
    public String mostrarProductosDelCarrito(ModelMap modelo, HttpSession session){
        User u = (User) session.getAttribute("usuariosession");//falta SpringSegurity  

        List<Producto> producto;
        try {
            producto = carritoServicio.mostrarProductos(u.getPerfil().getPendiente().getId());
             modelo.addAttribute("listaProductos", producto);
            return "";
        } catch (Exception ex) {
           return "";
        }
    }
}
