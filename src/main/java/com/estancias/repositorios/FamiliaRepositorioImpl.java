package com.estancias.repositorios;

import com.estancias.entidades.Familia;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FamiliaRepositorioImpl extends CRUDBaseRepository<Familia> {
    @Autowired
    public FamiliaRepositorioImpl(JpaRepository<Familia, Integer> repository) {
        super(repository);
    }
}
