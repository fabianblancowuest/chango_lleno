
package com.full_monkey.repository;

import com.full_monkey.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    public Usuario findByUsername(@Param("username") String username);
    
    @Query("SELECT u FROM Usuario u WHERE u.perfil.email = :email")
    public Usuario findByEmail(@Param("email") String email);
    
}
