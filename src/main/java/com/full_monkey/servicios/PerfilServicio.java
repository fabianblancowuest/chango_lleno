package com.full_monkey.servicios;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Compra;
import com.full_monkey.entidades.Perfil;
import com.full_monkey.repository.PerfilRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("deprecation")
public class PerfilServicio {

    @Autowired
    private PerfilRepository pr;

    @Transactional
    public Perfil crearPerfil(String nombre, String apellido, List<Compra> historial, Carrito pendiente, Long dni, Date nacimiento, String email, String domicilio, String fotoPerfil, String pregunta) throws Exception {
        validator(nombre, apellido, dni, nacimiento, email, domicilio);
        Perfil p = new Perfil();
        p.setApellido(apellido);
        p.setDni(dni);
        p.setDomicilio(domicilio);
        p.setEmail(email);
        p.setPregunta(pregunta);
        if (fotoPerfil == null || fotoPerfil.isEmpty()) {
            p.setFotoPerfil("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Face-monkey.svg/2048px-Face-monkey.svg.png");
        } else {
            p.setFotoPerfil(fotoPerfil);
        }
        p.setHistorial(historial);
        p.setNacimiento(nacimiento);
        p.setNombre(nombre);
        p.setPendiente(pendiente);
        return pr.save(p);
    }
    
    @Transactional(readOnly = true)
    public Perfil findById(String id)throws Exception{
        if(pr.findById(id)== null){
            throw new Exception("No Existe Un Perfil Con Ese Id");
        }
        return pr.getById(id);
    }

    @Transactional
    public Perfil modifPerfil(String id, String nombre, String apellido, Long dni, Date nacimiento, String email, String domicilio, String fotoPerfil) throws Exception {
        Optional<Perfil> respuesta = pr.findById(id);
        validator(nombre, apellido, dni, nacimiento, email, domicilio);
        if (respuesta.isPresent()) {
            Perfil p = respuesta.get();
            p.setApellido(apellido);
            p.setDni(dni);
            p.setDomicilio(domicilio);
            p.setEmail(email);
            if (fotoPerfil == null || fotoPerfil.isEmpty()) {
                p.setFotoPerfil("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Face-monkey.svg/2048px-Face-monkey.svg.png");
            } else {
                p.setFotoPerfil(fotoPerfil);
            }
            p.setNacimiento(nacimiento);
            p.setNombre(nombre);
            return pr.save(p);
        } else {
            throw new Exception("No existe ese perfil");
        }
    }
    
    @Transactional
    public Perfil cambiarCarrito(Perfil p,Carrito c){
        p.setPendiente(c);
        return pr.save(p);
    }
    
    @Transactional
    public void agregarCompra(String id,Compra compra) throws Exception{
         Optional<Perfil> respuesta = pr.findById(id);
         if (respuesta.isPresent()) {
              Perfil p = respuesta.get();
              p.getHistorial().add(compra);
         } else {
            throw new Exception("No existe ese perfil");
        }
    }
    
    @Transactional
    public void eliminarPerfil(String id) {
        pr.delete(pr.getById(id));
    }

    public void validator(String nombre, String apellido, Long dni, Date nacimiento, String email, String domicilio) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Necesita un nombre");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new Exception("Necesita un apellido");
        }
        if (dni < 1000000 || dni == null) {
            throw new Exception("Problemas con el dni");
        }
        if (nacimiento.getYear() > 2004 || nacimiento == null) {
            throw new Exception("Problemas con la edad");
        }
        if (!email.contains("@") || email == null || email.trim().isEmpty()) {
            throw new Exception("necesita un email");
        }
        if (domicilio == null || domicilio.trim().isEmpty()) {
            throw new Exception("necesita una domicilio");
        }
        if (pr.findByDni(dni)!=null){
            throw new Exception("El Dni ya le pertenece a otro usuario");
        }
    }
}
