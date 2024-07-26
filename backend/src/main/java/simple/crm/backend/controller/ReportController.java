package simple.crm.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.crm.backend.dto.client.ClientResponseDto;
import simple.crm.backend.dto.contact.ContactResponseDto;
import simple.crm.backend.dto.task.TaskResponseDto;
import simple.crm.backend.service.impl.ClientService;
import simple.crm.backend.service.impl.ContactService;
import simple.crm.backend.service.impl.ExelConvertService;
import simple.crm.backend.service.impl.TaskService;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Tag(name = "Reports management", description = "Endpoints for managing reports")
public class ReportController {
    private final ExelConvertService exelConvertService;
    private final ClientService clientService;
    private final ContactService contactService;
    private final TaskService taskService;

    @GetMapping("/allClientsExel")
    @Operation(summary = "Get all client as a report in Exel file")
    public ResponseEntity<ByteArrayResource> getClientsReport()
            throws IOException, IllegalAccessException {
        List<ClientResponseDto> clients = clientService.getAll();
        return exelConvertService.generateExcelResponse(clients, ClientResponseDto.class,
                "clients_report.xlsx");
    }

    @GetMapping("/allContactsExel")
    @Operation(summary = "Get all contacts as a report in Exel file")
    public ResponseEntity<ByteArrayResource> getContactsReport()
            throws IOException, IllegalAccessException {
        List<ContactResponseDto> contacts = contactService.getAll();
        return exelConvertService.generateExcelResponse(contacts, ContactResponseDto.class,
                "contacts_report.xlsx");
    }

    @GetMapping("/allTasksExel")
    @Operation(summary = "Get all tasks as a report in Exel file")
    public ResponseEntity<ByteArrayResource> getTasksReport()
            throws IOException, IllegalAccessException {
        List<TaskResponseDto> tasks = taskService.getAll();
        return exelConvertService.generateExcelResponse(tasks, TaskResponseDto.class,
                "tasks_report.xlsx");
    }

    @GetMapping("/allContactsByClientExel/{id}")
    @Operation(summary = "Get all contacts by client as a report in Exel file")
    public ResponseEntity<ByteArrayResource> getAllContactsByClientReport(@PathVariable Long id)
            throws IOException, IllegalAccessException {
        List<ContactResponseDto> contacts = contactService.getAllContactByClient(id);
        return exelConvertService.generateExcelResponse(contacts, ContactResponseDto.class,
                "allContactsByClient_report.xlsx");
    }

    @GetMapping("/allTasksByContactExel/{id}")
    @Operation(summary = "Get all tasks by contact as a report in Exel file")
    public ResponseEntity<ByteArrayResource> getAllTasksByContactReport(@PathVariable Long id)
            throws IOException, IllegalAccessException {
        List<TaskResponseDto> tasksByContact = taskService.getAllTasksByContact(id);
        return exelConvertService.generateExcelResponse(tasksByContact, TaskResponseDto.class,
                "allTasksByContact_report.xlsx");
    }
}

