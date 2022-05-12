package com.full_monkey.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Producto {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    protected String img;
    protected String nombre;
    protected Double precio;
    protected Integer stock;
    @OneToOne
    protected Categoria categoria;
    protected String descripcion;
    protected Integer unidades;

    public Producto() {
    }

    public Producto(String img, String nombre, Double precio, Integer stock, Categoria area, String descripcion,Integer unidades) {
        this.img = img;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = area;
        this.descripcion = descripcion;
        this.unidades = unidades;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getUnidades() { return unidades; }

    public void setUnidades(Integer unidades) { this.unidades = unidades; }
}

