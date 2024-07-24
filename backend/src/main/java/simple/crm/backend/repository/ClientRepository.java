package simple.crm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.crm.backend.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
