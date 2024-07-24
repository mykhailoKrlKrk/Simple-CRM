package simple.crm.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import simple.crm.backend.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT cl FROM Client cl WHERE cl.area == :area AND cl.isDeleted = false")
    List<Client> getAllClientsFromSpecificArea(@Param("area") String area);
}
