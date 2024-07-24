package simple.crm.backend.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
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
import simple.crm.backend.dto.client.ClientRequestDto;
import simple.crm.backend.dto.client.ClientResponseDto;
import simple.crm.backend.service.impl.ClientService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDto createClient(@RequestBody ClientRequestDto client) {
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    public ClientResponseDto updateClientInfo(@PathVariable Long id,
                                              @RequestBody ClientRequestDto request) {
        return clientService.update(id, request);
    }

    @GetMapping
    public List<ClientResponseDto> getAllClients() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ClientResponseDto getClientById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }
}
