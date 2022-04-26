package com.full_monkey.entidades;

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
    @Temporal(TemporalType.DATE)
    private Date fecha_compra;
    @OneToOne
    private Carrito carro;
    private String metodopago;
    private Double precio_final;

    public Compra() {
    }

    public Compra(Date fecha_compra, Carrito carro, String metodopago, Double precio_final) {
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

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Carrito getCarro() {
        return carro;
    }

    public void setCarro(Carrito carro) {
        this.carro = carro;
    }

    public String getMetodopago() {
        return metodopago;
    }

    public void setMetodopago(String metodopago) {
        this.metodopago = metodopago;
    }

    public Double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(Double precio_final) {
        this.precio_final = precio_final;
    }
    
    
}

