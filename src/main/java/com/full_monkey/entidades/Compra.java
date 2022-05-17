package com.full_monkey.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Compra {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Integer numerp_orden;
    private LocalDateTime fecha_compra;
    @OneToOne
    private Carrito carro;
    @OneToOne
    private Tarjeta metodopago;
    private Double precio_final;

    public Compra() {
    }

    public Compra(Integer numerp_orden, LocalDateTime fecha_compra, Carrito carro, Tarjeta metodopago, Double precio_final) {
        this.numerp_orden = numerp_orden;
        this.fecha_compra = fecha_compra;
        this.carro = carro;
        this.metodopago = metodopago;
        this.precio_final = precio_final;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDateTime fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Carrito getCarro() {
        return carro;
    }

    public void setCarro(Carrito carro) {
        this.carro = carro;
    }

    public Tarjeta getMetodopago() {
        return metodopago;
    }

    public void setMetodopago(Tarjeta metodopago) {
        this.metodopago = metodopago;
    }

    public Double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(Double precio_final) {
        this.precio_final = precio_final;
    }

    public Integer getNumerp_orden() {
        return numerp_orden;
    }

    public void setNumerp_orden(Integer numerp_orden) {
        this.numerp_orden = numerp_orden;
    }
    
    
}

