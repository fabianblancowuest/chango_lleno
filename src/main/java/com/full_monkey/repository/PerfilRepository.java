
package com.full_monkey.repository;

import com.full_monkey.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, String> {

    @Query("SELECT p FROM Perfil p WHERE p.dni = :dni")
    public Perfil findByDni(@Param("dni") Long dni);
}
