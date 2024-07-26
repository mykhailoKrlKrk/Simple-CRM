package simple.crm.backend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.crm.backend.dto.client.ClientRequestDto;
import simple.crm.backend.dto.client.ClientResponseDto;
import simple.crm.backend.mapper.ClientMapper;
import simple.crm.backend.model.Client;
import simple.crm.backend.repository.ClientRepository;
import simple.crm.backend.service.GenericService;

@Service
@RequiredArgsConstructor
public class ClientService implements GenericService<ClientResponseDto, ClientRequestDto> {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponseDto create(ClientRequestDto request) {
        Client client = clientMapper.toModel(request);
        return clientMapper.toDto(clientRepository.save(client));
    }

    public List<ClientResponseDto> getAllClientsBySpecificArea(String area) {
        return clientRepository.getClientsByArea(area).stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public ClientResponseDto update(Long id, ClientRequestDto request) {
        clientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find client with id: " + id));
        Client client = clientMapper.toModel(request);
        client.setId(id);
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientResponseDto> getAll() {
        System.out.println("Called DB");
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public ClientResponseDto getById(Long id) {
        return clientMapper.toDto(
                clientRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Can't find client with id: " + id)));
    }

    @Override
    public void delete(Long id) {
        getById(id);
        clientRepository.deleteById(id);
    }
}
