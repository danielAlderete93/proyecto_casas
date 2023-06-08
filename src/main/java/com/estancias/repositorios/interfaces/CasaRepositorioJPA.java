package com.estancias.repositorios.interfaces;

import com.estancias.entidades.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CasaRepositorioJPA extends JpaRepository<Casa, Integer> {
}
