package com.example.proyecto_Integrador.dao;

import java.util.List;

public interface IDao<T> {

    T guardar(T t);
    T buscar(Integer id);
    T buscarXString (String string);
    void actualizar(T t);
    void eliminar(Integer id);
    List<T> buscarTodo();
}