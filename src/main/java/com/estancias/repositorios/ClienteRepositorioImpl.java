package com.estancias.repositorios;

import com.estancias.entidades.Cliente;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepositorioImpl extends CRUDBaseRepository<Cliente> {
    @Autowired
    public ClienteRepositorioImpl(JpaRepository<Cliente, Integer> repository) {
        super(repository);
    }
}
