package com.full_monkey.service;

import com.full_monkey.entidades.Producto;
import com.full_monkey.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@SuppressWarnings("deprecation")
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findByNombre(String nombre) {
        return productoRepository.finByNombre(nombre);
    }

    public List<Producto> finByCategoria (String categoria){
        return productoRepository.finByCategoria(categoria);
    }

    public void crearProducto(Producto producto) throws Exception {
        validar(producto.getNombre(),producto.getPrecio(),producto.getStock(),producto.getCategoria(),producto.getDescripcion(),producto.getImg());
        productoRepository.save(producto);
    }

    @Transactional
    public void eliminarProducto(String id) throws Exception {
        if (productoRepository.findById(id).isPresent()){
            productoRepository.deleteById(id);
        }else{
            throw new Exception("No se puede eliminar ese producto porque no existe en la base de datos");
        }
    }

    public Producto getOne(String id) {
        return productoRepository.getOne(id);

    }

    public Producto modificarProducto(Producto producto) throws Exception {

        validar(producto.getNombre(),producto.getPrecio(),producto.getStock(),producto.getCategoria(),producto.getDescripcion(),producto.getImg());
        if (productoRepository.findById(producto.getId()).isPresent()){
            Producto productoBuscado = productoRepository.findById(producto.getId()).get();
            productoBuscado.setNombre(producto.getNombre());
            productoBuscado.setPrecio(producto.getPrecio());
            productoBuscado.setStock(producto.getStock());
            productoBuscado.setCategoria(producto.getCategoria());
            productoBuscado.setDescripcion(producto.getDescripcion());
            productoBuscado.setImg(producto.getImg());
            productoRepository.save(productoBuscado);
            return productoBuscado;
        }
        else{
            throw new Exception("Este producto no esta en la base de datos");
        }

    }

    public void validar(String nombre,double precio,Integer stock,String categoria,String descripcion,String img) throws Exception {
        if (nombre == null || nombre.isEmpty()){
            throw new Exception("El producto no puede estar vacio");
        }
        if (categoria == null ||categoria.isEmpty()){
            throw new Exception("El producto debe tener una categoria");
        }
        if (descripcion == null ||descripcion.isEmpty()){
            throw new Exception("No se permite un producto sin descripcion");
        }
        if (stock == null || !Integer.class.isInstance(stock)){
            throw new Exception("El producto debe tener un stock");
        }
        if (precio < 0 || !Double.class.isInstance(precio)){
            throw new Exception("Precio invalido");
        }
        if (img == null || img.isEmpty()){
            throw new Exception("No se permiten productos sin imagen");
        }

    }
}




