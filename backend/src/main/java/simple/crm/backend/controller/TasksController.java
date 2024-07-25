package simple.crm.backend.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import simple.crm.backend.dto.task.TaskRequestDto;
import simple.crm.backend.dto.task.TaskResponseDto;
import simple.crm.backend.service.impl.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tasks")
public class TasksController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto createTask(@RequestBody TaskRequestDto client) {
        return taskService.create(client);
    }

    @PutMapping("/{id}")
    @CachePut(cacheNames = "TaskResponse", key = "#id")
    public TaskResponseDto updateTaskInfo(@PathVariable Long id,
                                          @RequestBody TaskRequestDto request) {
        return taskService.update(id, request);
    }

    @GetMapping("/{contactId}")
    @Cacheable(value = "TaskResponse", key = "#contactId")
    public List<TaskResponseDto> getAllTasksByContact(@PathVariable Long contactId) {
        return taskService.getAllTasksByContact(contactId);
    }

    @GetMapping
    @Cacheable(value = "TaskResponse", key = "'allTasks'")
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "TaskResponse", key = "#id")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(cacheNames = "TaskResponse", key = "#id", beforeInvocation = true)
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }
}
