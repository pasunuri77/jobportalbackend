package com.jobportal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobportal.entity.Company;
import com.jobportal.entity.Job;
import com.jobportal.repository.CompanyRepo;
import com.jobportal.service.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/job")
@CrossOrigin("http://localhost:4200")
public class JobController {

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    private CompanyRepo companyRepo;

    // ✅ CREATE JOB
    @PostMapping("/create")
    public ResponseEntity<Job> createJob(
            @RequestPart("jobData") Job job,
            @RequestPart("file") MultipartFile file,
            Authentication auth) {

        String email = auth.getName();

        Company company = companyRepo.findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        job.setCompany(company);

        Job savedJob = jobService.register(job, file);

        return ResponseEntity.ok(savedJob);
    }

    // ✅ GET ALL JOBS
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // 🔥 FINAL: GET MY JOBS (LOGIN BASED)
    @GetMapping("/my-jobs")
    public ResponseEntity<List<Job>> getMyJobs(Authentication auth) {

        String email = auth.getName();

        Company company = companyRepo.findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        int userId = company.getUser().getId(); // 🔥 KEY LINE

        return ResponseEntity.ok(
                jobService.getJobsByUserId(userId)
        );
    }

    // ✅ UPDATE JOB
    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(
            @PathVariable int id,
            @RequestPart("jobData") String jobData,
            @RequestPart(value = "file", required = false) MultipartFile file,
            Authentication auth) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Job job = mapper.readValue(jobData, Job.class);

        String email = auth.getName();

        Company company = companyRepo.findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        job.setCompany(company);

        Job updatedJob = jobService.updateJob(id, job, file);

        return ResponseEntity.ok(updatedJob);
    }

    // ✅ DELETE JOB
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }
}