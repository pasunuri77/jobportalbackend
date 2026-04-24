package com.jobportal.dto;

import com.jobportal.entity.Job;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobDto {

    // ✅ CREATE JOB
    Job register(Job job, MultipartFile file);

    // ✅ GET ALL JOBS
    List<Job> getAllJobs();

    Job updateJob(int id,Job job, MultipartFile file);

    // ✅ GET JOB BY ID
    List<Job>  getJobsByUserId(int userId);

    // ✅ DELETE JOB
    void deleteJob(int id);
}
