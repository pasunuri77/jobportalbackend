package com.jobportal.service;


import java.util.List;
import java.util.Map;

import com.jobportal.entity.Application;
import com.jobportal.entity.Company;
import com.jobportal.repository.ApplicationRepo;
import com.jobportal.repository.CompanyRepo;
import com.jobportal.repository.JobRepo;
import com.jobportal.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.dto.UserDto;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepo;

@Service
public class ServiceImplementation implements UserDto {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private JwtUtil jwtUtil;

    public User register(User user) {
        try {
            return userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email already exists");
        }
    }

    @Override
    public Map<String, Object> login(String email, String password) {

        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name(), user.getId());
        System.out.println("JWT generated :" + token);

        return Map.of("token", token, "role", user.getRole());
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public boolean verifyEmail(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    public String updatePasswordByEmail(String email, String newPassword) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (newPassword == null || newPassword.length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters");
        }

        // ❗ plain password (only for practice)
        user.setPassword(newPassword);

        userRepo.save(user);

        return "Password updated successfully";
    }


    @Override
    @Transactional
    public void deleteUser(int userId) {

        try {
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // ✅ delete user applications
            applicationRepo.deleteByUser_Id(userId);

            // ✅ get company
            Company company = companyRepo.findByUser_Id(userId).orElse(null);

            if (company != null) {

                int companyId = company.getId();

                // ✅ delete applications of company
                applicationRepo.deleteByCompany_Id(companyId);

                // ✅ delete jobs (this also deletes job_requirements automatically)
                jobRepo.deleteByCompany_Id(companyId);

                // ✅ delete company
                companyRepo.delete(company);
            }

            // ✅ delete user
            userRepo.delete(user);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting user: " + e.getMessage());
        }

    }



}
