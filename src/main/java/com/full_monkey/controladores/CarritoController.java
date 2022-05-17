package com.full_monkey.controladores;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Perfil;
import com.full_monkey.entidades.Producto;
import com.full_monkey.entidades.Usuario;

import com.full_monkey.servicios.CarritoServicio;
import com.full_monkey.servicios.PerfilServicio;
import com.full_monkey.servicios.ProductoService;
import com.full_monkey.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    CarritoServicio carritoServicio;

    @Autowired
    ProductoService productoServicio;

    @Autowired
    UsuarioServicio us;
    

//    @GetMapping("/crearCarrito")
//    public String crearCarrito(HttpSession session) {
//        carritoServicio.crearCarrito();
//        return "";
//    }
    @GetMapping("/mostrarProductos")
    public String mostrarProductosDelCarrito(ModelMap modelo, HttpSession session) {
        try {
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());

            List<Producto> productos;
            Perfil p = u.getPerfil();
            Carrito c = p.getPendiente();
            modelo.addAttribute("carro", c);
            productos = carritoServicio.mostrarProductos(c.getId());
            modelo.addAttribute("listaproductos", productos);
            return "Carrito.html";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/producto/listaDeProductos";
        }
    }

//    @PostMapping("/mostrarProductos")
//    public String cargarCarrito(ModelMap modelo, @ModelAttribute List<String> idProducto, @ModelAttribute List<Integer> unidades, HttpSession session) {
//        try {
//            if (unidades == null) {
//                throw new Exception();
//            }
//
//            Usuario user = (Usuario) session.getAttribute("usuariosession");
//            Usuario u = us.findById(user.getId());
//            for (String producto : idProducto) {
//                for (Integer unidad : unidades) {
//                    Producto prod = productoServicio.getOne(producto);
//                    prod.setStock(prod.getStock() - unidad);
//                    prod.setUnidades(unidad);
//                    carritoServicio.poner(u.getPerfil().getPendiente().getId(), prod);
//                }
//            }
//            modelo.addAttribute("carro", u.getPerfil().getPendiente());
//            return "redirect:/compra/crearCompra";
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return "redirect:/";
//        }
//    }

    @GetMapping("/cargarUno/{idProducto}")
    public String cargarUnoCarrito(ModelMap modelo, @PathVariable String idProducto, HttpSession session) throws Exception {
        try {
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());

            Producto prod = productoServicio.getOne(idProducto);
            prod.setStock(prod.getStock() - 1);
            prod.setUnidades(prod.getUnidades() + 1);
            carritoServicio.poner(u.getPerfil().getPendiente().getId(), prod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/carrito/mostrarProductos";
    }
    
    @GetMapping("/cargar/{idProducto}")
    public String cargarCarrito(ModelMap modelo, @PathVariable String idProducto, HttpSession session) throws Exception {
        try {
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());

            Producto prod = productoServicio.getOne(idProducto);
            prod.setStock(prod.getStock() - 1);
            prod.setUnidades(1);
            carritoServicio.poner(u.getPerfil().getPendiente().getId(), prod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/producto/listaDeProductos";
    }

//    @GetMapping("/sacar/{idProducto}")
//    public String sacarDelCarrito(ModelMap modelo, HttpSession session, @PathVariable String idProducto) {
//        Usuario u = (Usuario) session.getAttribute("usuariosession");
//
//        List<Producto> producto = productoServicio.findAll();
//
//        modelo.addAttribute("listaProductos", producto);
//        return "";
//    }
//    @PostMapping("/sacar/{idProducto}")
//    public String sacarDelCarrito(ModelMap modelo, @PathVariable String idProducto, @RequestParam Integer unidades, HttpSession session) {
//        try {
//            if (unidades == 0 || unidades == null) {
//                throw new Exception();
//            }
//            Usuario user = (Usuario) session.getAttribute("usuariosession");
//            Usuario u = us.findById(user.getId());
//
//            Producto prod = productoServicio.getOne(idProducto);
//            prod.setStock(prod.getStock() + unidades);
//            carritoServicio.sacar(u.getPerfil().getPendiente().getId(), prod, unidades);
//            return "";
//        } catch (Exception ex) {
//            modelo.put("error", unidades > 0 ? "No se cargaron los productos" : "No ha seleccionado ningún producto para eliminar del carrito");
//            return "";
//        }
//    }
    @GetMapping("/sacar/{idProducto}")
    public String sacarCarrito(ModelMap modelo, HttpSession session, @PathVariable String idProducto) throws Exception {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
        Usuario u = us.findById(user.getId());

        Producto prod = productoServicio.getOne(idProducto);
        prod.setStock(prod.getStock() + prod.getUnidades());
        carritoServicio.sacar(u.getPerfil().getPendiente().getId(), prod, prod.getUnidades());
        return "redirect:/carrito/mostrarProductos";
    }
    
    @GetMapping("/sacarUno/{idProducto}")
    public String sacarUnoCarrito(ModelMap modelo, HttpSession session, @PathVariable String idProducto) throws Exception {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
        Usuario u = us.findById(user.getId());

        Producto prod = productoServicio.getOne(idProducto);
        prod.setStock(prod.getStock() + 1);
        carritoServicio.sacar(u.getPerfil().getPendiente().getId(), prod, 1);
        return "redirect:/carrito/mostrarProductos";
    }

//    @GetMapping("/eliminar/{id}")
//    public String eliminarCarrito(@PathVariable String id) {
//        try {
//            carritoServicio.eliminarCarrito(id);
//            return "";
//        } catch (Exception ex) {
//            return "";
//        }
//    }
//
//    @GetMapping("/precioEnvio")
//    public String seleccionarPrecioEnvio() {
//
//        return "";
//    }
//
//    @PostMapping("/precioEnvio")
//    public String precioEnvio(ModelMap modelo, HttpSession session, Double precioEnvio) {
//        Usuario user = (Usuario) session.getAttribute("usuariosession");
//        Usuario u = us.findById(user.getId());
//        try {
//            carritoServicio.precioDeEnvio(u.getPerfil().getPendiente().getId(), precioEnvio);
//            return "";
//        } catch (Exception ex) {
//            return "";
//        }
//    }
//    @GetMapping("/moificarEnvio")
//    public String mostrarPrecioEnvio(ModelMap modelo, HttpSession session) {
//        Usuario user = (Usuario) session.getAttribute("usuariosession");
//        Usuario u = us.findById(user.getId());
//
//        modelo.put("precioEnvio", u.getPerfil().getPendiente().getPrecio_envio());
//        return "";
//    }
    @PostMapping("/modificarEnvio")
    public String modificarPrecioEnvio(ModelMap modelo, HttpSession session, Double precioEnvio) {
        Usuario u = (Usuario) session.getAttribute("usuariosession");
        try {
            carritoServicio.modificarPrecioDeEnvio(u.getPerfil().getPendiente().getId(), precioEnvio);
            return "";
        } catch (Exception ex) {
            return "";
        }
    }
}
