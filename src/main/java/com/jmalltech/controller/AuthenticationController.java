
package com.jmalltech.controller;

import com.jmalltech.entity.IUser;
import com.jmalltech.service.ClientDomainService;
import com.jmalltech.service.StaffDomainService;
import com.jmalltech.util.JwtUtil;
import com.jmalltech.util.LoginRequest;
import com.jmalltech.util.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    @Autowired
    private ClientDomainService clientDomainService;

    @Autowired
    private StaffDomainService staffDomainService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Attempting to login with username: " + loginRequest.getUsername());

        IUser user = staffDomainService.getByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        String role = null;

        if(user != null) {
            role = "STAFF";
        } else {
            user = clientDomainService.getByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            if(user != null) {
                role = "CLIENT";
            }
        }
        if(user != null){
            String token = JwtUtil.generateToken(user, role);
            System.out.println("Generated token: " + token);
            return ResponseEntity.ok(new TokenResponse(token, role));
        }else {
            System.out.println("Login failed for username: " + loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}

