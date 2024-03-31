package com.jmalltech.controller;

import com.jmalltech.entity.Client;
import com.jmalltech.helper.ResponseHelper;
import com.jmalltech.service.ClientDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private ClientDomainService clientService;

    @Autowired
    public ClientController(ClientDomainService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Client client = clientService.getById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseHelper.notFoundResponse("Client not found for ID: " + id);
        }
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<?> getClientByUsername(@PathVariable String username) {
        Client client = clientService.getByUsername(username);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseHelper.notFoundResponse("Client not found for username: " + username);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        Client updatedClient = clientService.update(client);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseHelper.badRequestResponse("Client not found.");
        }
    }
}
