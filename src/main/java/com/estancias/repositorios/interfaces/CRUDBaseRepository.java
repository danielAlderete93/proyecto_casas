package com.estancias.repositorios.interfaces;

import com.estancias.entidades.EntidadPersistente;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public abstract class CRUDBaseRepository<T extends EntidadPersistente> implements CrudRepository<T, Integer> {
    private JpaRepository<T, Integer> repository;

    @Override
    public T obtenerPorID(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> listarTodas() {
        return repository.findAll();
    }

    @Override
    public Integer guardar(T o) {

        return repository.save(o).getId();
    }



    @Override
    public boolean borrar(Integer id) {
        T o = this.repository.findById(id).orElse(null);

        if (o == null) {
            return false;
        }

        repository.delete(o);

        return this.repository.findById(id).isEmpty();
    }
}
