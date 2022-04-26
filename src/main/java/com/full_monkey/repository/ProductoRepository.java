
package com.full_monkey.repository;

import com.full_monkey.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String>{
 
    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    public Producto finByNombre(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
    public Producto finByCategoria(@Param("categoria") String categoria);
}
