package simple.crm.backend.service;

public interface GenericService<T, R> {
    T create(R request);
    T update(R request);
    void delete(Long id);
}
