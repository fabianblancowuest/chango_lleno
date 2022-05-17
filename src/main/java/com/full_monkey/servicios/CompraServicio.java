package com.full_monkey.servicios;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Compra;
import com.full_monkey.entidades.Tarjeta;
import com.full_monkey.repository.CompraRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompraServicio {

    @Autowired
    private CompraRepository compraRepositorio;

    @Transactional
    public Compra crearCompra(Carrito carro, Tarjeta metodopago) throws Exception {
        Compra compra = new Compra();
        validator(LocalDateTime.now(), carro, metodopago);
        compra.setFecha_compra(LocalDateTime.now());
        compra.setCarro(carro);
        List<Compra> c = compraRepositorio.findAll();
        compra.setNumerp_orden(c.size());
        compra.setMetodopago(metodopago);
        compra.setPrecio_final(carro.getPrecio_total());
        return compraRepositorio.save(compra);
    }

    @Transactional
    public Compra modificar(String id, LocalDateTime fecha_compra, Carrito carro, Tarjeta metodopago, Double precio_final) throws Exception {
        validator(fecha_compra, carro, metodopago);
        Optional<Compra> compraTraida = compraRepositorio.findById(id);
        if (compraTraida.isPresent()) {
            Compra compra = compraTraida.get();
            compra.setFecha_compra(fecha_compra);

            compra.setCarro(carro);
            compra.setMetodopago(metodopago);
            compra.setPrecio_final(precio_final);
            return compraRepositorio.save(compra);
        } else {
            throw new Exception("no existe una compra con esde Id");
        }
    }

    @Transactional
    public void eliminar(Compra compra) {
        compraRepositorio.delete(compra);
    }

    @Transactional(readOnly = true)
    public Compra buscarPorId(String id) {
        return compraRepositorio.getById(id);

    }

    public void validator(LocalDateTime fecha_compra, Carrito carro, Tarjeta metodopago) throws Exception {
        if (fecha_compra == null) {
            throw new Exception("fecha invalida");
        }
        if (carro == null) {
            throw new Exception("el carro es nulo");
        }
        if (metodopago == null) {
            throw new Exception("metodo de pago incorrecto");
        }

    }
}
