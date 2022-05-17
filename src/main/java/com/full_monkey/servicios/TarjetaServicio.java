package com.full_monkey.servicios;

import com.full_monkey.entidades.Tarjeta;
import com.full_monkey.entidades.Usuario;
import com.full_monkey.repository.TarjetaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarjetaServicio {

    @Autowired
    private TarjetaRepository tr;

    @Transactional
    public Tarjeta crearTarjeta(String titular, String fotoempresa, Long numero, Integer numfinal, Integer clave, String expiracion, Usuario user) throws Exception {
        validar(titular, fotoempresa, numero, numfinal, clave, expiracion, user);
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setTitular(titular);
        tarjeta.setFotoempresa(fotoempresa);
        tarjeta.setNumero(numero);
        tarjeta.setNumfinal(numfinal);
        tarjeta.setClave(clave);
        tarjeta.setExpiracion(expiracion);
        tarjeta.setUser(user);
        return tr.save(tarjeta);
    }

    @Transactional
    public Tarjeta modificarTarjeta(String id, String titular, String fotoempresa, Long numero, Integer numfinal, Integer clave, String expiracion, Usuario user) throws Exception {
        validar(titular, fotoempresa, numero, numfinal, clave, expiracion, user);
        Tarjeta tarjeta = tr.getById(id);
        if (tarjeta == null) {
            throw new Exception("No Existe una tarjeta con ese id");
        }
        tarjeta.setTitular(titular);
        tarjeta.setFotoempresa(fotoempresa);
        tarjeta.setNumero(numero);
        tarjeta.setNumfinal(numfinal);
        tarjeta.setClave(clave);
        tarjeta.setExpiracion(expiracion);
        tarjeta.setUser(user);
        return tr.save(tarjeta);
    }

    @Transactional(readOnly = true)
    public Tarjeta findById(String id) throws Exception {
        Tarjeta tarjeta = tr.getById(id);
        if (tarjeta == null) {
            throw new Exception("No Existe una tarjeta con ese id");
        }
        return tr.getById(id);
    }

    @Transactional(readOnly = true)
    public List<Tarjeta> findByUser(Usuario user) {
        return tr.findByUser(user);
    }

    public void eliminarTarjeta(String id) throws Exception {
        Tarjeta tarjeta = tr.getById(id);
        if (tarjeta == null) {
            throw new Exception("No Existe una tarjeta con ese id");
        }
        tr.delete(tarjeta);
    }

    public void validar(String titular, String fotoempresa, Long numero, Integer numfinal, Integer clave, String expiracion, Usuario user) throws Exception {

        if (titular == null || titular.trim().isEmpty()) {
            throw new Exception("titular no tiene que estar vacío");

        }

//        if (fotoempresa == null || fotoempresa.trim().isEmpty()) {
//            throw new Exception("fotoempresa no tiene que estar vacío");
//        }

        if (numero == null || numero < 1) {
            throw new Exception("numero no puede estar vacio");

        }

        if (numfinal == null || numfinal < 1 || numfinal > 9999) {
            throw new Exception("numero no puede estar vacío, no puede ser 0 o negativo ni puede contener más de 4 dígitos");
        }

        if (clave == null || clave < 1 || clave > 999) {

            throw new Exception("clave no puede venir vacío, no puede ser un numero negativo o 0 ni contener más de 3 dígitos");

        }
        if (expiracion == null || !expiracion.contains("/") || expiracion.length() != 5) {
            throw new Exception("expiración no puede venir vacío, debe contener 5 caracteres y debe contener / ");

        }

        if (user == null) {
            throw new Exception("el usuario no puede estar vacío");
        }

    }
}
