package com.estancias.repositorios.interfaces;

import com.estancias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorioJPA extends JpaRepository<Usuario, Integer> {
}
