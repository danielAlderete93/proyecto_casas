package com.estancias.repositorios.interfaces;

import com.estancias.entidades.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanciaRepositorioJPA extends JpaRepository<Estancia, Integer> {
}
