package com.estancias.repositorios.interfaces;

import com.estancias.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorioJPA extends JpaRepository<Comentario, Integer> {
}
