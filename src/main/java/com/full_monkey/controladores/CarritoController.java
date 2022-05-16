package com.full_monkey.controladores;

import com.full_monkey.entidades.Producto;
import com.full_monkey.entidades.Usuario;

import com.full_monkey.servicios.CarritoServicio;
import com.full_monkey.servicios.ProductoService;
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

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    CarritoServicio carritoServicio;

    @Autowired
    ProductoService productoServicio;
    
    @Autowired
    UsuarioServicio us;

    @GetMapping("/crearCarrito")
    public String crearCarrito(HttpSession session) {
        carritoServicio.crearCarrito();
        return "";
    }

    @GetMapping("/cargar/{idProducto}")
    public String cargarCarrito(@PathVariable String idProducto) {
        return "";
    }

    @PostMapping("/cargar/{idProducto}")
    public String cargarCarrito(ModelMap modelo, @PathVariable String idProducto, @RequestParam Integer unidades, HttpSession session) {
        try {
            if (unidades == 0 || unidades == null) {
                throw new Exception();
            }
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            Producto prod = productoServicio.getOne(idProducto);
            prod.setStock(prod.getStock() - unidades);
            prod.setUnidades(unidades);
            carritoServicio.poner(u.getPerfil().getPendiente().getId(), prod);
            return "";
        } catch (Exception ex) {
            modelo.put("error", unidades > 0 ? "No se cargaron los productos" : "No ha seleccionado ningún producto para agregar al carrito");
            return "";
        }
    }

    @GetMapping("/cargarUno/{idProducto}")
    public String cargarUnoCarrito(ModelMap modelo, @PathVariable String idProducto, HttpSession session) throws Exception {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());

        Producto prod = productoServicio.getOne(idProducto);
        prod.setStock(prod.getStock() - 1);
        prod.setUnidades(1);
        carritoServicio.poner(u.getPerfil().getPendiente().getId(), prod);
        return "";
    }

    @GetMapping("/sacar/{idProducto}")
    public String sacarDelCarrito(ModelMap modelo, HttpSession session, @PathVariable String idProducto) {
        Usuario u = (Usuario) session.getAttribute("usuariosession");

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
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());

            Producto prod = productoServicio.getOne(idProducto);
            prod.setStock(prod.getStock() + unidades);
            carritoServicio.sacar(u.getPerfil().getPendiente().getId(), prod, unidades);
            return "";
        } catch (Exception ex) {
            modelo.put("error", unidades > 0 ? "No se cargaron los productos" : "No ha seleccionado ningún producto para eliminar del carrito");
            return "";
        }
    }

    @GetMapping("/sacarUno/{idProducto}")
    public String sacarUnoCarrito(ModelMap modelo, HttpSession session, @PathVariable String idProducto) throws Exception {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());

        Producto prod = productoServicio.getOne(idProducto);
        prod.setStock(prod.getStock() + 1);
        carritoServicio.sacar(u.getPerfil().getPendiente().getId(), prod, 1);
        return "";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCarrito(@PathVariable String id) {
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
        Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
        try {
            carritoServicio.precioDeEnvio(u.getPerfil().getPendiente().getId(), precioEnvio);
            return "";
        } catch (Exception ex) {
            return "";
        }
    }

    @GetMapping("/moificarEnvio")
    public String mostrarPrecioEnvio(ModelMap modelo, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());

        modelo.put("precioEnvio", u.getPerfil().getPendiente().getPrecio_envio());
        return "";
    }

    @PostMapping("/modificarEnvio")
    public String modificarPrecioEnvio(ModelMap modelo, HttpSession session, Double precioEnvio) {
        Usuario u = (Usuario) session.getAttribute("usuariosession");//falta SpringSegurity 
        try {
            carritoServicio.modificarPrecioDeEnvio(u.getPerfil().getPendiente().getId(), precioEnvio);
            return "";
        } catch (Exception ex) {
            return "";
        }
    }

    @GetMapping("/mostrarProductos")
    public String mostrarProductosDelCarrito(ModelMap modelo, HttpSession session) {
        try {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId()); 

        List<Producto> producto;
        
            producto = carritoServicio.mostrarProductos(u.getPerfil().getPendiente().getId());
            modelo.addAttribute("listaProductos", producto);
            return "Carrito.html";
        } catch (Exception ex) {
            return "redirect:/producto/listaDeProductos";
        }
    }
}
