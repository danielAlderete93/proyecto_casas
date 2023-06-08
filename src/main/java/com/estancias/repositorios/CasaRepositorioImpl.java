package com.estancias.repositorios;

import com.estancias.entidades.Casa;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CasaRepositorioImpl extends CRUDBaseRepository<Casa> {

    @Autowired
    public CasaRepositorioImpl(JpaRepository<Casa, Integer> repository) {
        super(repository);
    }


}
