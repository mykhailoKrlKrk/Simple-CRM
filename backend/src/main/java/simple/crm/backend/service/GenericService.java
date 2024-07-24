package simple.crm.backend.service;

import java.util.List;

public interface GenericService<T, R> {
    T create(R request);

    T update(Long id, R request);

    List<T> getAll();

    T getById(Long id);

    void delete(Long id);
}
