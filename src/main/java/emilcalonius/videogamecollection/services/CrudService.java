package emilcalonius.videogamecollection.services;

import java.util.Collection;

public interface CrudService <T, ID> {
    // Generic CRUD
    T findById(ID id);
    Collection<T> findAll();
    T add(T entity);
    T update(T entity);
    void deleteById(ID id);
    void delete(T entity);
}
