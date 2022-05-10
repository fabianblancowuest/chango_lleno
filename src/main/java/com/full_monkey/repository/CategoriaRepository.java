
package com.full_monkey.repository;

import com.full_monkey.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,String> {

    @Query("SELECT c FROM Categoria c WHERE c.nombre = :nombre")
    public Categoria findByName(@Param("nombre") String nombre);
}
