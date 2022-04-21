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

@Service
public class PerfilServicio {

    @Autowired
    private PerfilRepository pr;

    public Perfil crearPerfil(String nombre, String apellido, List<Compra> historial, Carrito pendiente, Long dni, Date nacimiento, String email, String domicilio, String fotoPerfil) throws Exception {
        validator(nombre, apellido, dni, nacimiento, email, domicilio);
        Perfil p = new Perfil();
        p.setApellido(apellido);
        p.setDni(dni);
        p.setDomicilio(domicilio);
        p.setEmail(email);
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

    public Perfil modifPerfil(String id, String nombre, String apellido, List<Compra> historial, Carrito pendiente, Long dni, Date nacimiento, String email, String domicilio, String fotoPerfil) throws Exception {
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
            p.setHistorial(historial);
            p.setNacimiento(nacimiento);
            p.setNombre(nombre);
            p.setPendiente(pendiente);
            return pr.save(p);
        } else {
            throw new Exception("No existe ese perfil");
        }
    }
    
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
    }
}
