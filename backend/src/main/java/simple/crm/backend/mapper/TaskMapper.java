package simple.crm.backend.mapper;


import org.mapstruct.Mapper;
import simple.crm.backend.config.MapperConfig;
import simple.crm.backend.dto.task.TaskRequestDto;
import simple.crm.backend.dto.task.TaskResponseDto;
import simple.crm.backend.model.Task;

@Mapper(config = MapperConfig.class)
public interface TaskMapper {
    TaskResponseDto toDto(Task service);

    Task toModel(TaskRequestDto requestDto);
}
