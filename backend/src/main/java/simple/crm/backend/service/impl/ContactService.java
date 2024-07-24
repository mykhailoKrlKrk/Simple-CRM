package simple.crm.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.crm.backend.dto.contact.ContactRequestDto;
import simple.crm.backend.dto.contact.ContactResponseDto;
import simple.crm.backend.repository.ContactRepository;
import simple.crm.backend.service.GenericService;

@Service
@RequiredArgsConstructor
public class ContactService implements GenericService<ContactResponseDto, ContactRequestDto> {
    private final ContactRepository contactRepository;
    @Override
    public ContactResponseDto create(ContactRequestDto request) {
        return null;
    }

    @Override
    public ContactResponseDto update(ContactRequestDto request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
