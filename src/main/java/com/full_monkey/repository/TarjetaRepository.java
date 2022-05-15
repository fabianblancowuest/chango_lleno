
package com.full_monkey.repository;

import com.full_monkey.entidades.Tarjeta;
import com.full_monkey.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,String> {

    @Query("SELECT t FROM Tarjeta t WHERE t.user = :user")
    public List<Tarjeta> findByUser(@Param("user") Usuario user);
}
