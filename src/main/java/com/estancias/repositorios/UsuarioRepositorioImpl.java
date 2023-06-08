package com.estancias.repositorios;

import com.estancias.entidades.Usuario;
import com.estancias.repositorios.interfaces.CRUDBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public class UsuarioRepositorioImpl extends CRUDBaseRepository<Usuario> {

    @Autowired
    public UsuarioRepositorioImpl(JpaRepository<Usuario, Integer> repository) {
        super(repository);
    }
}
