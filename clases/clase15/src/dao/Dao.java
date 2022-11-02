package dao;

import java.util.List;

public interface Dao<T> {

    T guardar(T t);
    T buscar(Integer id);
    void actualizar(T t);
    void eliminar(Integer id);
    List<T> buscarTodo();
}
