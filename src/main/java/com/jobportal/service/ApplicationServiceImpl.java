package com.jobportal.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.jobportal.entity.Company;
import com.jobportal.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.dto.ApplicationDto;
import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.enums.ApplicationStatus;
import com.jobportal.repository.ApplicationRepo;
import com.jobportal.repository.JobRepo;
import com.jobportal.repository.UserRepo;

@Service
public class ApplicationServiceImpl implements ApplicationDto {

    @Autowired
    private ApplicationRepo applicationRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public Application apply(String email, int jobId, MultipartFile file) {

        validateFile(file);

        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));


        // ✅ CHECK USING IDs
        if (applicationRepo.existsByUser_IdAndJob_Id(user.getId(), jobId)) {
            throw new RuntimeException("You have already applied for this job");
        }


        Company company = job.getCompany();

        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/resumes/";

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(uploadDir, fileName);

            Files.createDirectories(path.getParent());

            file.transferTo(path.toFile());

            Application app = new Application();
            app.setUser(user);
            app.setJob(job);
            app.setCompany(company);
            app.setStatus(ApplicationStatus.APPLIED);

            // ✅ STORE RELATIVE PATH
            app.setResumePath("/uploads/resumes/" + fileName);

            return applicationRepo.save(app);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    @Override
    public Application updateStatus(int id, String status) {

        Application app = applicationRepo.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));

        try {
            app.setStatus(ApplicationStatus.valueOf(status.toUpperCase()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid status value");
        }

        return applicationRepo.save(app);
    }

    @Override
    public void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Resume file is required");
        }

        long maxSize = 10 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new RuntimeException("File size should not exceed 10MB");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null || !(fileName.toLowerCase().endsWith(".pdf") || fileName.toLowerCase().endsWith(".doc") || fileName.toLowerCase().endsWith(".docx"))) {

            throw new RuntimeException("Only PDF, DOC, DOCX files are allowed");
        }
    }

    // 🔥 DOWNLOAD METHOD

    @Override
    public ResponseEntity<Resource> downloadResume(int id) {

        Application app = applicationRepo.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));


        try {
            // ✅ FIX PATH HERE
            Path path = Paths.get("").toAbsolutePath().resolve(app.getResumePath().replaceFirst("/", ""));

            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                throw new RuntimeException("File not found: " + path);
            }

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName().toString() + "\"").body(resource);

        } catch (Exception e) {
            throw new RuntimeException("Error downloading file: " + e.getMessage());
        }
    }

    @Override
    public List<Application> getApplicationsByCompany(String email) {
        return applicationRepo.findByCompany_User_Email(email);
    }

    //get all
    public List<Application> getAllApplications() {
        return applicationRepo.findAll();
    }


    //delete
    @Override
    public void deleteApplication(int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        try {
            // 🔥 STEP 1: delete applications of user
            applicationRepo.deleteById(id);

            // 🔥 STEP 2: get company
            Company company = companyRepo.findByUser_Email(user.getEmail()).orElse(null);

            if (company != null) {

                int companyId = company.getId();

                // 🔥 STEP 3: delete applications of company
                applicationRepo.deleteByCompanyId(companyId);

                // 🔥 STEP 4: delete jobs of company
//                jobRepo.deleteByCompanyId(companyId);

                // 🔥 STEP 5: delete company
                companyRepo.delete(company);
            }

            // 🔥 STEP 6: delete user
            userRepo.delete(user);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting user: " + e.getMessage());
        }


    }

    @Override
    public List<Application> getApplicationsByUser(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return applicationRepo.findByUser_Id(user.getId());
    }
}