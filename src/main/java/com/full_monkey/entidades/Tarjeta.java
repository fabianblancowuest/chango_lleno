package com.full_monkey.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titular;
    private String fotoempresa;
    private Long numero;
    private Integer numfinal;
    private Integer clave;
    private String expiracion;
    @OneToOne
    private Usuario user;

    public Tarjeta() {
    }

    public Tarjeta(String titular, String fotoempresa, Long numero, Integer numfinal, Integer clave, String expiracion, Usuario user) {
        this.titular = titular;
        this.fotoempresa = fotoempresa;
        this.numero = numero;
        this.numfinal = numfinal;
        this.clave = clave;
        this.expiracion = expiracion;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFotoempresa() {
        return fotoempresa;
    }

    public void setFotoempresa(String fotoempresa) {
        this.fotoempresa = fotoempresa;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getNumfinal() {
        return numfinal;
    }

    public void setNumfinal(Integer numfinal) {
        this.numfinal = numfinal;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(String expiracion) {
        this.expiracion = expiracion;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}

