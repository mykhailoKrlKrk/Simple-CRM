package simple.crm.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
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
import simple.crm.backend.dto.client.ClientRequestDto;
import simple.crm.backend.dto.client.ClientResponseDto;
import simple.crm.backend.service.impl.ClientService;

@RestController
@RequiredArgsConstructor
@CacheConfig(cacheNames = "ClientResponse")
@RequestMapping(value = "/clients")
@Tag(name = "Clients management", description = "Endpoints for managing clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create client")
    public ClientResponseDto createClient(@RequestBody ClientRequestDto client) {
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update client")
    @CachePut(cacheNames = "ClientResponse", key = "#id")
    public ClientResponseDto updateClientInfo(@PathVariable Long id,
                                              @RequestBody ClientRequestDto request) {
        return clientService.update(id, request);
    }

    @GetMapping
    @Operation(summary = "Get all clients")
    @Cacheable(value = "ClientResponse", key = "'allClients'")
    public List<ClientResponseDto> getAllClients() {
        return clientService.getAll();
    }

    @GetMapping("/area/{area}")
    @Operation(summary = "Get all clients by area")
    @Cacheable(value = "ClientResponse", key = "#area")
    public List<ClientResponseDto> getClientsByArea(@PathVariable String area) {
        return clientService.getAllClientsBySpecificArea(area);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by id")
    @Cacheable(value = "ClientResponse", key = "#id")
    public ClientResponseDto getClientById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete client")
    @CacheEvict(cacheNames = "ClientResponse", key = "#id", beforeInvocation = true)
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }
}
