package simple.crm.backend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.crm.backend.dto.task.TaskRequestDto;
import simple.crm.backend.dto.task.TaskResponseDto;
import simple.crm.backend.mapper.TaskMapper;
import simple.crm.backend.model.Task;
import simple.crm.backend.repository.TaskRepository;
import simple.crm.backend.service.GenericService;

@Service
@RequiredArgsConstructor
public class TaskService implements GenericService<TaskResponseDto, TaskRequestDto> {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDto create(TaskRequestDto request) {
        Task task = taskMapper.toModel(request);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto request) {
        taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find task with id: " + id));
        Task task = taskMapper.toModel(request);
        task.setId(id);
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskResponseDto> getAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public TaskResponseDto getById(Long id) {
        return taskMapper.toDto(
                taskRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Can't find task with id: " + id)));
    }

    public List<TaskResponseDto> getAllTasksByContact(Long id) {
        return taskRepository.findAll()
                .stream()
                .filter(ts -> ts.getId().equals(id))
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        getById(id);
        taskRepository.deleteById(id);
    }
}
