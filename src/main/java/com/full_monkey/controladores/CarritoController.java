package com.full_monkey.controladores;

import com.full_monkey.entidades.Producto;
import com.full_monkey.entidades.User;
import com.full_monkey.repository.ProductoRepository;
import com.full_monkey.servicios.CarritoServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String cargarCarrito(ModelMap modelo, @RequestParam String idProducto, @RequestParam Integer unidades) {
        try {
            if (unidades == 0 || unidades == null) {
                throw new Exception();
            }
            User u = (User) session.getAttribute("usuariosession");

            List<Producto> productos = new ArrayList();
            Producto prod = productoRepository.findById(idProducto).get();
            prod.setStock(prod.getStock() - unidades);
            for (int i = 0; i < unidades; i++) {
                productos.add(prod);
            }
            carritoServicio.poner(u.getPerfil().getPendiente().getId(), productos);
            return "";
        } catch (Exception ex) {
            modelo.put("error", unidades > 0 ? "No se cargaron los pructos" : "No selecciono ningun producto");
            return "";
        }
    }

    @PostMapping("/sacar")
    public String sacarDelCarrito(ModelMap modelo, @RequestParam String idProducto, @RequestParam Integer unidades) {
        try {
            if (unidades == 0 || unidades == null) {
                throw new Exception();
            }
            User u = (User) session.getAttribute("usuariosession");

            List<Producto> productos = new ArrayList();
            Producto prod = new Producto();
            prod.setStock(prod.getStock() - unidades);
            for (int i = 0; i < unidades; i++) {
                productos.add(prod);
            }
            carritoServicio.poner(u.getPerfil().getPendiente().getId(), productos);
            return "";
        } catch (Exception ex) {
            modelo.put("error", unidades > 0 ? "No se cargaron los pructos" : "No selecciono ningun producto");
            return "";
        }
    }
}
