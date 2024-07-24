package simple.crm.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.crm.backend.service.impl.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tasks")
public class TasksController {
    private final TaskService taskService;
}
