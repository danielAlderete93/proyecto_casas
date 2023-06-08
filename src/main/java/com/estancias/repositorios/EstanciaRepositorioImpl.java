package com.estancias.repositorios;

import com.estancias.entidades.Estancia;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EstanciaRepositorioImpl extends CRUDBaseRepository<Estancia> {
    @Autowired
    public EstanciaRepositorioImpl(JpaRepository<Estancia, Integer> repository) {
        super(repository);
    }
}
