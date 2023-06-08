package com.estancias.repositorios;

import com.estancias.entidades.Comentario;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioRepositorioImpl extends CRUDBaseRepository<Comentario> {
    @Autowired
    public ComentarioRepositorioImpl(JpaRepository<Comentario, Integer> repository) {
        super(repository);
    }
}
