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
import simple.crm.backend.dto.contact.ContactRequestDto;
import simple.crm.backend.dto.contact.ContactResponseDto;
import simple.crm.backend.service.impl.ContactService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/contacts")
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDto createContact(@RequestBody ContactRequestDto client) {
        return contactService.create(client);
    }

    @GetMapping("/{clientId}")
    List<ContactResponseDto> getAllContactsByClient(@PathVariable Long clientId) {
        return contactService.getAllContactByClient(clientId);
    }

    @PutMapping("/{id}")
    @CachePut(cacheNames = "ContactResponse", key = "#id")
    public ContactResponseDto updateContactInfo(@PathVariable Long id,
                                                @RequestBody ContactRequestDto request) {
        return contactService.update(id, request);
    }

    @GetMapping
    @Cacheable(value = "ContactResponse", key = "'allContacts'")
    public List<ContactResponseDto> getAllContacts() {
        return contactService.getAll();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "ContactResponse", key = "'contactById'")
    public ContactResponseDto getContactById(@PathVariable Long id) {
        return contactService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(cacheNames = "ContactResponse", key = "#id", beforeInvocation = true)
    public void deleteContact(@PathVariable Long id) {
        contactService.delete(id);
    }
}
