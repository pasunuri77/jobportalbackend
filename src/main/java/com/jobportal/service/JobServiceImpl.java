package com.jobportal.service;

import com.jobportal.cloudinary.CloudinaryService;
import com.jobportal.dto.JobDto;
import com.jobportal.entity.Job;
import com.jobportal.exception.DatabaseException;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class JobServiceImpl implements JobDto {

    @Autowired
    private JobRepo jobsRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    // ✅ CREATE JOB
    @Override
    public Job register(Job job, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Logo file is required");
        }

        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("Only image files allowed");
        }

        String imageUrl = cloudinaryService.upload(file, "jobportal/jobs");

        job.setLogo(imageUrl);

        return jobsRepo.save(job);
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

        if (file != null && !file.isEmpty()) {

            if (!file.getContentType().startsWith("image/")) {
                throw new RuntimeException("Only image files allowed");
            }

            String imageUrl = cloudinaryService.upload(file, "jobportal/jobs");
            existing.setLogo(imageUrl);
        }

        return jobsRepo.save(existing);
    }

    // ✅ GET ALL JOBS
    @Override
    public List<Job> getAllJobs() {
        return jobsRepo.findAll();
    }

    // ✅ GET JOBS BY USER
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