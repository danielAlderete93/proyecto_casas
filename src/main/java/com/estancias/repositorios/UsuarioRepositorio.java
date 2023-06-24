package com.estancias.repositorios;

import com.estancias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {


    @Query("select u from usuarios u where u.alias = :alias")
    Optional<Usuario> findByAlias(@Param("alias") String alias);
}
