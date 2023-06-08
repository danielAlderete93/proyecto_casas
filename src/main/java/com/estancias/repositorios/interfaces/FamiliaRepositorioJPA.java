package com.estancias.repositorios.interfaces;

import com.estancias.entidades.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepositorioJPA extends JpaRepository<Familia, Integer> {
}
