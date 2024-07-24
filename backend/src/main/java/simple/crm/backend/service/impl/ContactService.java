package simple.crm.backend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple.crm.backend.dto.contact.ContactRequestDto;
import simple.crm.backend.dto.contact.ContactResponseDto;
import simple.crm.backend.mapper.ContactMapper;
import simple.crm.backend.model.Contact;
import simple.crm.backend.repository.ContactRepository;
import simple.crm.backend.service.GenericService;

@Service
@RequiredArgsConstructor
public class ContactService implements GenericService<ContactResponseDto, ContactRequestDto> {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public ContactResponseDto create(ContactRequestDto request) {
        Contact contact = contactMapper.toModel(request);
        return contactMapper.toDto(contactRepository.save(contact));
    }

    @Override
    public ContactResponseDto update(Long id, ContactRequestDto request) {
        contactRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find contact with id: " + id));
        Contact contact = contactMapper.toModel(request);
        contact.setId(id);
        return contactMapper.toDto(contact);
    }

    @Override
    public List<ContactResponseDto> getAll() {
        return contactRepository.findAll().stream()
                .map(contactMapper::toDto)
                .toList();
    }

    @Override
    public ContactResponseDto getById(Long id) {
        return contactMapper.toDto(
                contactRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Can't find contact with id: " + id)));
    }

    @Override
    public void delete(Long id) {
        getById(id);
        contactRepository.deleteById(id);
    }
}
