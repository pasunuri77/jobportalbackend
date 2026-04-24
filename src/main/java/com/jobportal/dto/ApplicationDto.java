package com.jobportal.dto;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.entity.Application;

import java.util.List;


public interface ApplicationDto {

    Application apply(String email, int JobId, MultipartFile file);

    void validateFile(MultipartFile file);

    ResponseEntity<Resource> downloadResume(int id);

    Application updateStatus(int id, String status);

    List<Application> getApplicationsByCompany(String email);

    List<Application> getApplicationsByUser(String email);


    void deleteApplication(int id);
}
