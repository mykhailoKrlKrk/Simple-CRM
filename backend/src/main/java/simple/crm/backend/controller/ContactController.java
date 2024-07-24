package simple.crm.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.crm.backend.service.impl.ContactService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/contacts")
public class ContactController {
    private final ContactService contactService;
}
