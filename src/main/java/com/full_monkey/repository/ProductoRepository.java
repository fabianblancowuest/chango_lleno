
package com.full_monkey.repository;

import com.full_monkey.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    public List<Producto> finByNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
    public List<Producto> finByCategoria(@Param("categoria") String categoria);

}