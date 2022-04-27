/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.full_monkey.controladores;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Compra;
import com.full_monkey.servicios.CompraServicio;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/compra")
public class CompraControladores {
    
    @Autowired
    private  CompraServicio compraServicio;
    
      @GetMapping("/crearCompra")
    public String crearCompra() {
        return "crearCompra.html";
    }
    
    public Compra llenarCompra(@RequestParam LocalDateTime fecha_compra, @RequestParam Carrito carro, @RequestParam String metodopago,@RequestParam Double precio_final ){
    Compra compra = new Compra();
       compraServicio.crearCompra(metodopago, fecha_compra, carro, metodopago)
            
            
     return "crearCompra.html";
    }
    
    
    @GetMapping("/compra")
    public String mostarCompra(@RequestParam Compra compra, Model model){
   compraServicio.buscarPorId(compra);
     model.addAttribute("compra",compra);
        return "compra";
    }
    
    
    
    @GetMapping("/editar") 
    public String editarCompra(@RequestParam Compra compra, Model model){
try{
    compra = compraServicio.buscarPorId(compra);
model.addAttribute("compra",compra);
        return "editar compra";}
catch(Exception exc){
            exc.printStackTrace();
         return "redirect:/compra";
        }
    }
     @GetMapping("/eliminar")
    public String eliminarCompra(@RequestParam Compra compra){
       compra = compraServicio.buscarPorId(compra);
       compraServicio.eliminar(compra);
        return "redirect:/compra";
    }
    @PostMapping("/editar")
    public String editCompra(){
    
    }
    
}
