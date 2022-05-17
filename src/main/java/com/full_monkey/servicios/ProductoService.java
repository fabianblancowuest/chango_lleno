package com.full_monkey.servicios;

import com.full_monkey.entidades.Categoria;
import com.full_monkey.entidades.Producto;
import com.full_monkey.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("deprecation")
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaServicio cs;

    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.finByNombre(nombre);
    }

    @Transactional(readOnly = true)
    public List<Producto> finByCategoria (String categoria){
        return productoRepository.finByCategoria(categoria);
    }

    @Transactional
    public void crearProducto(String img, String nombre, Double precio, Integer stock, String area, String descripcion,Integer unidades) throws Exception {
        validar(nombre, precio, stock, area, descripcion, img);
        Producto p = new Producto();
        p.setImg(img);
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setUnidades(unidades);
        p.setCategoria(cs.crearCategoria(area));
        productoRepository.save(p);
    }

    @Transactional
    public void eliminarProducto(String id) throws Exception {
        if (productoRepository.findById(id).isPresent()){
            productoRepository.deleteById(id);
        }else{
            throw new Exception("No se puede eliminar ese producto porque no existe en la base de datos");
        }
    }
    
    @Transactional(readOnly = true)
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto getOne(String id) {
        return productoRepository.getOne(id);

    }

    @Transactional
    public Producto modificarProducto(Producto producto) throws Exception {

        validar(producto.getNombre(),producto.getPrecio(),producto.getStock(),producto.getCategoria().getNombre(),producto.getDescripcion(),producto.getImg());
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

    public void validar(String nombre,Double precio,Integer stock,String categoria,String descripcion,String img) throws Exception {
        if (nombre == null || nombre.isEmpty()){
            throw new Exception("El producto no puede estar vacio");
        }
        if (categoria == null){
            throw new Exception("El producto debe tener una categoria");
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

    public List<Producto> listarProductos() throws Exception {
        if(!productoRepository.findAll().isEmpty()){
            List<Producto> productos =  productoRepository.findAll();
            return productos;

        }else{
            throw new Exception("No se encontro ningun producto en el catalogo");
        }
    }
}




