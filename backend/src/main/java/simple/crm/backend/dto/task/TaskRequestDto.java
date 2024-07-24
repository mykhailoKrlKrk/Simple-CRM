package simple.crm.backend.dto.task;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;
import lombok.experimental.Accessors;
import simple.crm.backend.model.Task;

@Data
@Accessors(chain = true)
public class TaskRequestDto {
    @NotEmpty
    private String description;
    @NotNull
    private Task.Status status;
    @NotNull
    private LocalDate dueDate;
    @NotNull
    private Long contactId;
}
