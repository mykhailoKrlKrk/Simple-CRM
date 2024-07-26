package simple.crm.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.crm.backend.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> getClientsByArea(String area);
}
