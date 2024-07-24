package simple.crm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simple.crm.backend.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
