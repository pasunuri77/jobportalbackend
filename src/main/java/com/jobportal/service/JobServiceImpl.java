package com.jobportal.service;

import com.jobportal.dto.JobDto;
import com.jobportal.entity.Job;
import com.jobportal.exception.DatabaseException;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JobServiceImpl implements JobDto {

    @Autowired
    private JobRepo jobsRepo;

    // ✅ CREATE JOB
    @Override
    public Job register(Job job, MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("Logo file is required");
            }

            if (!file.getContentType().startsWith("image/")) {
                throw new RuntimeException("Only image files allowed");
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get("uploads/job/" + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            job.setLogo("/uploads/job/" + fileName);

            return jobsRepo.save(job);

        } catch (IOException e) {
            throw new DatabaseException("Error while saving logo");
        }
    }

    // ✅ UPDATE JOB
    @Override
    public Job updateJob(int id, Job job, MultipartFile file) {

        Job existing = jobsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        existing.setTitle(job.getTitle());
        existing.setLocation(job.getLocation());
        existing.setType(job.getType());
        existing.setSalary(job.getSalary());
        existing.setDescription(job.getDescription());
        existing.setRequirements(job.getRequirements());

        try {
            if (file != null && !file.isEmpty()) {

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                Path path = Paths.get("uploads/job/" + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                existing.setLogo("/uploads/job/" + fileName);
            }

        } catch (Exception e) {
            throw new RuntimeException("File upload failed");
        }

        return jobsRepo.save(existing);
    }



    // ✅ GET ALL JOBS
    @Override
    public List<Job> getAllJobs() {
        return jobsRepo.findAll();
    }

    // 🔥 IMPORTANT: GET JOBS BY USER (FINAL FIX)
    public List<Job> getJobsByUserId(int userId) {
        return jobsRepo.findDistinctByCompany_User_Id(userId);
    }

    // ✅ DELETE JOB
    public void deleteJob(int id) {

        Job job = jobsRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id)
                );

        try {
            jobsRepo.delete(job);
        } catch (DataAccessException e) {
            throw new DatabaseException("Error while deleting job");
        }
    }
}