package simple.crm.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.crm.backend.dto.task.TaskRequestDto;
import simple.crm.backend.dto.task.TaskResponseDto;
import simple.crm.backend.repository.TaskRepository;
import simple.crm.backend.service.GenericService;

@Service
@RequiredArgsConstructor
public class TaskService implements GenericService<TaskResponseDto, TaskRequestDto> {
    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDto create(TaskRequestDto request) {
        return null;
    }

    @Override
    public TaskResponseDto update(TaskRequestDto request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
