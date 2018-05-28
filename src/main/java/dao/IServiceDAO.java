package dao;

import java.util.List;

public interface IServiceDAO<T> {

    T getById(long id);
    List<T> getAll();

    void create(T t);
    void update(T t);
    void remove(T t);
    void remove(long id);
    void removeAll();

}
