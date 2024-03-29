package com.jmalltech.security;

import com.jmalltech.entity.Staff;
import com.jmalltech.service.StaffCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private StaffCRUDService staffService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Attempting to login with username: " + loginRequest.getUsername());
        Staff staff = staffService.getByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (staff != null) {
            String token = JwtUtil.generateToken(staff);
            System.out.println("Generated token: " + token);
            return ResponseEntity.ok(new TokenResponse(token));
        } else {
            System.out.println("Login failed for username: " + loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
