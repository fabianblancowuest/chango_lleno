package com.full_monkey.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToMany
    private List<Producto> productos;
    private Integer unidades;
    private Double precio_total;
    private Double precio_envio;

    public Carrito() {
    }

    public Carrito(List<Producto> productos, Integer unidades, Double precio_total, Double precio_envio) {
        this.productos = productos;
        this.unidades = unidades;
        this.precio_total = precio_total;
        this.precio_envio = precio_envio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(Double precio_total) {
        this.precio_total = precio_total;
    }

    public Double getPrecio_envio() {
        return precio_envio;
    }

    public void setPrecio_envio(Double precio_envio) {
        this.precio_envio = precio_envio;
    }
    
}

