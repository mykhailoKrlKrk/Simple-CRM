package simple.crm.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.crm.backend.service.impl.ClientService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clients")
public class ClientController {
    private final ClientService clientService;


}
