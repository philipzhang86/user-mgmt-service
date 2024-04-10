package com.jmalltech.controller;

import com.jmalltech.service.ClientDomainService;
import com.jmalltech.service.StaffDomainService;
import com.jmalltech.entity.Client;
import com.jmalltech.entity.Staff;
import com.jmalltech.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staffs")
@CrossOrigin(origins = "http://localhost:4200") // 允许http://localhost:4200来源的跨源请求
public class StaffController {
    private StaffDomainService staffService;

    private ClientDomainService clientService;

    @Autowired
    public StaffController(StaffDomainService staffService, ClientDomainService clientService) {
        this.staffService = staffService;
        this.clientService = clientService;
    }

    @GetMapping("/my-info")
    public ResponseEntity<?> getMyInfo(@RequestAttribute("username") String username) {
        Staff staff = staffService.getByUsername(username);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseHelper.notFoundResponse("Staff not found for username: " + username);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable Long id) {
        Staff staff = staffService.getById(id);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseHelper.notFoundResponse("Staff not found for ID: " + id);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getStaffByUsername(@PathVariable String username) {
        Staff staff = staffService.getByUsername(username);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseHelper.notFoundResponse("Staff not found for username: " + username);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Staff> insertStaff(@RequestBody Staff staff) {
        Staff createdStaff = staffService.insert(staff);
        return ResponseEntity.ok(createdStaff);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateStaff(@RequestBody Staff staff) {
        Staff updatedStaff = staffService.update(staff);
        if (updatedStaff != null) {
            return ResponseEntity.ok(updatedStaff);
        } else {
            return ResponseHelper.badRequestResponse("Staff not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
        boolean isRemoved = staffService.remove(id);
        if (isRemoved) {
            return ResponseEntity.ok().body("{\"message\": \"Staff removed successfully.\"}");
        } else {
            return ResponseHelper.notFoundResponse("Staff not found for ID: " + id);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllStaffs() {
        List<Staff> staffList = staffService.getStaffList();
        if (staffList != null && !staffList.isEmpty()) {
            return ResponseEntity.ok(staffList);
        } else {
            return ResponseHelper.notFoundResponse("No staff members found.");
        }
    }

    @GetMapping("/client/by-username/{username}")
    public ResponseEntity<?> getClientByUsername(@PathVariable String username) {
        Client client = clientService.getByUsername(username);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseHelper.notFoundResponse("Client not found for username: " + username);
        }
    }

    @GetMapping("/client/by-id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Client client = clientService.getById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseHelper.notFoundResponse("Client not found for ID: " + id);
        }
    }

    @PostMapping("/client")//这里要搞个bad request 万一插入失败了呢
    public ResponseEntity<Client> insertClient(@RequestBody Client client) {
        Client createdClient = clientService.insert(client);
        return ResponseEntity.ok(createdClient);
    }

    @PutMapping("/client")//update must come with id and username, otherwise it will cause cache exception
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        Client updatedClient = clientService.update(client);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseHelper.badRequestResponse("Client update failed.");
        }
    }

    /**
     * Removes a client by ID or username.
     * If both ID and username are provided, ID is preferred.
     *
     * @param id       The ID of the client to be removed (optional if username is provided).
     * @param username The username of the client to be removed (optional if ID is provided).
     * @return A ResponseEntity indicating the result of the operation.
     */
    @DeleteMapping("/clients")
    public ResponseEntity<?> deleteClient(@RequestParam(value = "id", required = false) Long id,
                                          @RequestParam(value = "username", required = false) String username) {
        if (id == null && username == null) {
            return ResponseEntity.badRequest().body("Either 'id' or 'username' must be provided.");
        }

        boolean removed = clientService.remove(id, username);
        if (removed) {
            return ResponseEntity.ok().body("{\"message\": \"Client removed successfully.\"}");
        } else {
            return ResponseHelper.notFoundResponse("Client not found.");
        }
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllClients() {
        List<Client> clientList = clientService.getClientListWithPassword();
        if (clientList != null && !clientList.isEmpty()) {
            return ResponseEntity.ok(clientList);
        } else {
            return ResponseHelper.notFoundResponse("No clients found.");
        }
    }
}
