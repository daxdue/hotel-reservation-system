package dao.interfaces;

public interface CrudInterface <E, K> {
    E save(E object);
    void update(E object, K id);
    E get(Long id);
    void remove(K id);
}
