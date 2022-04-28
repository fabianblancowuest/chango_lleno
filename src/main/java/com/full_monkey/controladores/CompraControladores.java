
package com.full_monkey.controladores;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Compra;
import com.full_monkey.servicios.CompraServicio;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/compra")
public class CompraControladores {

    @Autowired
    private CompraServicio compraServicio;

    @GetMapping("/crearCompra")
    public String crearCompra() {
        return "crearCompra.html";
    }

    @PostMapping("/crearCompra")
    public String llenarCompra(@RequestParam LocalDateTime fecha_compra, @RequestParam Carrito carro, @RequestParam String metodopago) {
        try {
            compraServicio.crearCompra(carro, metodopago);
            return "crearCompra.html";
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            return "redirect:/compra";
        }
    }

    @GetMapping("/{id}")
    public String mostarCompra(@PathVariable String id, Model model) {
        Compra compra = compraServicio.buscarPorId(id);
        model.addAttribute("compra", compra);
        return "compra";
    }

    @GetMapping("/editar/{id}")
    public String editarCompra(@PathVariable String id, Model model) {
        try {
            Compra compra = compraServicio.buscarPorId(id);
            model.addAttribute("compra", compra);
            return "editar compra";
        } catch (Exception exc) {
            exc.printStackTrace();
            return "redirect:/compra";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCompra(@PathVariable String id) {
        Compra compra = compraServicio.buscarPorId(id);
        compraServicio.eliminar(compra);
        return "redirect:/compra";
    }

    @PostMapping("/editar/{id}")
    public String editCompra(String id, Carrito carro, String metodopago, Double precio_final) {
        try {
            compraServicio.modificar(id, LocalDateTime.now(), carro, metodopago, precio_final);
            return"redirect:/compra";
        } catch (Exception exc) {
            exc.printStackTrace();
            return "redirect:/compra";
        }
    }

}
