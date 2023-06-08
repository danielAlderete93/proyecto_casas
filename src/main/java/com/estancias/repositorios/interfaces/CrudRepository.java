package com.estancias.repositorios.interfaces;

import java.util.List;

public interface CrudRepository<T, ID> {

    T obtenerPorID(ID id);

    List<T> listarTodas();

    ID guardar(T o);


    boolean borrar(ID o);


}
