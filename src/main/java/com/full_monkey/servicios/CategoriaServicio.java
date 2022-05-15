package com.full_monkey.servicios;

import com.full_monkey.entidades.Categoria;
import com.full_monkey.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServicio {

    @Autowired
    CategoriaRepository cr;

    @Transactional
    public Categoria crearCategoria(String nombre) throws Exception {
        validator(nombre);
        Categoria c = new Categoria();
        if (findByName(nombre.toLowerCase()) == null) {
            c.setNombre(nombre.toLowerCase());
        } else {
            c = findByName(nombre);
        }
        return cr.save(c);
    }

    @Transactional
    public Categoria modifCategoria(String id, String nombre) throws Exception {
        validator(nombre);
        Categoria c = cr.getById(id);
        c.setNombre(nombre);
        return cr.save(c);
    }

    @Transactional(readOnly = true)
    public Categoria findByName(String nombre) {
        return cr.findByName(nombre);
    }

    @Transactional
    public void eliminarCategoria(String id) {
        Categoria c = cr.getById(id);
        cr.delete(c);
    }
    @Transactional(readOnly = true)
    public List<Categoria> findAll(){
        return cr.findAll();
    }

    public void validator(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Necesita un nombre");
        }
    }
}
