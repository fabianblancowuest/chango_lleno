package com.full_monkey.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fotoPerfil;
    private String nombre;
    private String apellido;
    private Long dni;
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    private String domicilio;
    private String email;
    private String pregunta;
    @OneToMany
    private List<Compra> historial;
    @OneToOne
    private Carrito pendiente;

    public Perfil() {
    }

    public Perfil(String fotoPerfil, String nombre, String apellido, Long dni, Date nacimiento, String domicilio, String email, List<Compra> historial, Carrito pendiente) {
        this.fotoPerfil = fotoPerfil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nacimiento = nacimiento;
        this.domicilio = domicilio;
        this.email = email;
        this.historial = historial;
        this.pendiente = pendiente;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Compra> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Compra> historial) {
        this.historial = historial;
    }

    public Carrito getPendiente() {
        return pendiente;
    }

    public void setPendiente(Carrito pendiente) {
        this.pendiente = pendiente;
    }

    
}
