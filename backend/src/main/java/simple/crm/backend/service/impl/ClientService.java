package simple.crm.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.crm.backend.dto.client.ClientRequestDto;
import simple.crm.backend.dto.client.ClientResponseDto;
import simple.crm.backend.repository.ClientRepository;
import simple.crm.backend.service.GenericService;

@Service
@RequiredArgsConstructor
public class ClientService implements GenericService<ClientResponseDto, ClientRequestDto> {
    private final ClientRepository clientRepository;

    @Override
    public ClientResponseDto create(ClientRequestDto request) {
        return null;
    }

    @Override
    public ClientResponseDto update(ClientRequestDto request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
