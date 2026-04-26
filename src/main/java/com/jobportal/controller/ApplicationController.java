package com.jobportal.controller;

import com.jobportal.entity.Application;
import com.jobportal.service.ApplicationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/application")

public class ApplicationController {

    @Autowired
    private ApplicationServiceImpl applicationService;

    // ✅ APPLY JOB
    @PostMapping("/apply")
    public ResponseEntity<Application> apply(@RequestParam String email, @RequestParam int jobId, @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(applicationService.apply(email, jobId, file));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Application> updateStatus(
            @PathVariable int id,
            @RequestParam String status) {

        return ResponseEntity.ok(applicationService.updateStatus(id, status));
    }

    @GetMapping
    public ResponseEntity<?> apply() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @GetMapping("/company/{email}")
    public ResponseEntity<List<Application>> getCompanyApplications(Authentication auth) {

        String email = auth.getName();

        List<Application> applications = applicationService.getApplicationsByCompany(email);

        return ResponseEntity.ok(applications);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Application>> getUserApplications(Authentication auth) {

        String email = auth.getName(); // 🔥 from JWT

        List<Application> applications = applicationService.getApplicationsByUser(email);

        return ResponseEntity.ok(applications);
    }


    // ✅ DOWNLOAD RESUME
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable int id, Authentication authentication) {

        return applicationService.downloadResume(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable int id) {

        applicationService.deleteApplication(id);

        return ResponseEntity.ok("Application deleted successfully");
    }
}