package com.estancias.repositorios.interfaces;

import com.estancias.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorioJPA extends JpaRepository<Cliente, Integer> {
}
