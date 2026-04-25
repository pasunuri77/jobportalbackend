package com.jobportal.controller;


import com.jobportal.entity.User;
import com.jobportal.entity.UpdatePassword;
import com.jobportal.entity.VerifyEmailRequest;
import com.jobportal.repository.UserRepo;
import com.jobportal.service.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ServiceImplementation service;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.register(user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Map<String, Object> response = service.login(user.getEmail(), user.getPassword());

            return ResponseEntity.ok(response); // token + role
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/verifyemail")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest request) {

        try {
            boolean exists = service.verifyEmail(request.getEmail());

            return ResponseEntity.ok(exists); // true / false

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/updatepassword")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePassword request) {

        try {
            String message = service.updatePasswordByEmail(request.getEmail(), request.getNewPassword());

            return ResponseEntity.ok(message);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> allUser() {
        try {
            List<User> users = service.getAllUser();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No Details Found!");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
