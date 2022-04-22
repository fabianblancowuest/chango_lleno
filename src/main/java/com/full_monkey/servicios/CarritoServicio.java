package com.full_monkey.servicios;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Producto;
import com.full_monkey.repository.CarritoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 */
@Service
public class CarritoServicio {

    @Autowired
    private CarritoRepository carritoRepository;

    @Transactional
    public void crearCarrito() {
        Carrito carrito = new Carrito();
        carritoRepository.save(carrito);
    }

//    public void modificarCarrito(String idCarrito, Producto producto) throws Exception {
//        
//    }
    @Transactional
    public void eliminarCarrito(String idCarrito) throws Exception {
        Optional<Carrito> respuesta = carritoRepository.findById(idCarrito);
        if (respuesta.isPresent()) {
            Carrito carrito = respuesta.get();
            carritoRepository.delete(carrito);

        } else {
            throw new Exception("No existe el carrito");
        }

    }

    public void sacar(String idCarrito, List<Producto> productos) throws Exception {
        Optional<Carrito> respuesta = carritoRepository.findById(idCarrito);
        if (productos.isEmpty()) {
            throw new Exception("Lista Vacia");
        }
        if (respuesta.isPresent()) {
            Carrito carrito = respuesta.get();
            for (Producto aux : productos) {
                carrito.setPrecio_total(carrito.getPrecio_total() - aux.getPrecio());
                carritoRepository.delete(carrito);
            }
        } else {
            throw new Exception("No existe el carrito");
        }
    }

//     public void sacar(String idCarrito, List<Producto> producto) throws Exception{
//        Optional<Carrito> respuesta = carritoRepository.findById(idCarrito);
//
//        if (respuesta.isPresent()) { 
//            Carrito carrito = respuesta.get();
//            carrito.getProductos().add(producto);
//            carrito.setUnidades(carrito.getProductos().size());
//            for (Producto aux : carrito.getProductos()) {
//                carrito.setPrecio_total(carrito.getPrecio_total() + aux.getPrecio());
//            } 
//            carritoRepository.save(carrito);
//        } else {
//            throw new Exception("No existe el carrito");
//        }
//    }
    public void poner(String idCarrito, Producto producto) {
        Optional<Carrito> respuesta = carritoRepository.findById(idCarrito);
//
//        if (respuesta.isPresent()) { 
//            Carrito carrito = respuesta.get();
//            carrito.getProductos().add(producto);
//            carrito.setUnidades(carrito.getProductos().size());
//            for (Producto aux : carrito.getProductos()) {
//                carrito.setPrecio_total(carrito.getPrecio_total() + aux.getPrecio());
//            } 
//            carritoRepository.save(carrito);
//        } else {
//            throw new Exception("No existe el carrito");
//        }
    }

}
