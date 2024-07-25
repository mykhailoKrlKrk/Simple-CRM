package simple.crm.backend.dto.task;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.experimental.Accessors;
import simple.crm.backend.model.Task;

@Data
@Accessors(chain = true)
public class TaskResponseDto implements Serializable {
    private Long id;
    private String description;
    private Task.Status status;
    private LocalDate dueDate;
    private Long contactId;
}
