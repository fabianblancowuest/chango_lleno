/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.full_monkey.servicios;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Compra;
import com.full_monkey.repository.CompraRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraServicio {
    
    
     @Autowired
    private CompraRepository compraRepositorio;

    
    @Transactional
    public Compra crearCompra(String id,LocalDateTime fecha_compra, Carrito carro, String metodopago, Double precio_final)throws Exception{
    Compra compra= new Compra();
    validator(fecha_compra, carro, metodopago);
    compra.setFecha_compra(fecha_compra.now());
    compra.setCarro(carro);
    compra.setMetodopago(metodopago);
    compra.setPrecio_final(carro.getPrecio_total()+ carro.getPrecio_envio());
    return compraRepositorio.save(compra);
    }
    
    
    @Transactional
    public Compra modificar(String id, LocalDateTime fecha_compra, Carrito carro, String metodopago, Double precio_final) throws Exception{
  validator(fecha_compra, carro, metodopago);
   Optional<Compra>compraTraida= compraRepositorio.findById(id);
   if (compraTraida.isPresent()){
   Compra compra = compraTraida.get();
    compra.setFecha_compra(fecha_compra);
    
    compra.setCarro(carro);
    compra.setMetodopago(metodopago);
    compra.setPrecio_final(precio_final);
    return compraRepositorio.save(compra);
   }else{
       throw new Exception("no existe una compra con esde Id");
   }
   }

   
    
     @Transactional
    public void eliminar (Compra compra){
       compraRepositorio.delete(compra);
    }
    
    
     @Transactional( readOnly = true)
    public Compra buscarPorId(Compra compra){
        return compraRepositorio.findById(compra.getId()).orElse(null);
        
    }
    
     public void validator(LocalDateTime fecha_compra, Carrito carro, String metodopago) throws Exception {
        if (fecha_compra == null ) {
            throw new Exception("fecha invalida");
        }
        if (carro == null ) {
            throw new Exception("el carro es nulo");
        }
        if (metodopago!="efectivo" || metodopago!="tarjeta"  ) {
            throw new Exception("metodo de pago incorrecto");
        }
      
        }
    }
    

