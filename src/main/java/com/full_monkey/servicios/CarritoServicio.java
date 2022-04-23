package com.full_monkey.servicios;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Producto;
import com.full_monkey.repository.CarritoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoServicio {
    @Autowired
    private CarritoRepository carritoRepository;
    
    @Transactional
    public void crearCarrito() {
        Carrito carrito = new Carrito();
        carritoRepository.save(carrito);
    }
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
    @Transactional
    public void sacar(String idCarrito, List<Producto> productos) throws Exception {
        Optional<Carrito> respuesta = carritoRepository.findById(idCarrito);
        if (productos.isEmpty()) {
            throw new Exception("Lista de productos vacia");
        }
        if (respuesta.isPresent()) {
            Carrito carrito = respuesta.get();
            for (Producto aux : productos) {
                carrito.setPrecio_total(carrito.getPrecio_total() - aux.getPrecio());
                carritoRepository.delete(carrito);
            }
            carrito.setUnidades(carrito.getUnidades() - productos.size());
        } else {
            throw new Exception("No existe el carrito");
        }
    }
    public void poner(String idCarrito, List<Producto> productos) throws Exception {
        Optional<Carrito> respuesta = carritoRepository.findById(idCarrito);
        Double total = 0D;
        if (respuesta.isPresent()) {
            Carrito carrito = respuesta.get();
            carrito.setProductos(productos);
            carrito.setUnidades(carrito.getProductos().size());
            for (Producto aux : carrito.getProductos()) {
                total += aux.getPrecio();
            }
            carrito.setPrecio_envio(total);
            carritoRepository.save(carrito);
        } else {
            throw new Exception("No existe el carrito");
        }
    }
    public void precioDeEnvio(String idCarrito, Double precio_envio) throws Exception {
        Optional<Carrito> respuesta = carritoRepository.findById(idCarrito);

        if (respuesta.isPresent()) {
            Carrito carrito = respuesta.get();
            //El carrito puede existir pero no tener productos
            if (!carrito.getProductos().isEmpty()) {
                carrito.setPrecio_envio(precio_envio);
                carrito.setPrecio_total(carrito.getPrecio_total() + precio_envio);
                carritoRepository.save(carrito);
            }else{
               throw new Exception("Carrito vacio");
            }
        } else {
            throw new Exception("No existe el carrito");
        }
    }
    public void ModificarPrecioDeEnvio(String idCarrito, Double precio_envio) throws Exception {
        Optional<Carrito> respuesta = carritoRepository.findById(idCarrito);
        if (respuesta.isPresent()) {
            Carrito carrito = respuesta.get();
            //El carrito puede existir pero no tener productos
            if (!carrito.getProductos().isEmpty()) {
                //precio total le saco el evio anterior
                carrito.setPrecio_total(carrito.getPrecio_total()-carrito.getPrecio_envio());
                //precio total con el nuevo precio de envio
                carrito.setPrecio_envio(precio_envio);
                carrito.setPrecio_total(carrito.getPrecio_total() + precio_envio);
                carritoRepository.save(carrito);
            }else{
               throw new Exception("Carrito vacio");
            }
        } else {
            throw new Exception("No existe el carrito");
        }
    }
}
