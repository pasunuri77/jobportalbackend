package com.jobportal.service;

import com.jobportal.dto.CompanyDto;
import com.jobportal.entity.Company;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyDto {

    @Autowired
    private CompanyRepo companyRepo;

    // ✅ CREATE
    @Override
    public Company createCompany(Company company, MultipartFile file) {

        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("Logo file is required");
            }

            if (!file.getContentType().startsWith("image/")) {
                throw new RuntimeException("Only image files allowed");
            }

            // ✅ ABSOLUTE PATH (VERY IMPORTANT)
            String uploadDir = System.getProperty("user.dir") + "/uploads/companies/";

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(uploadDir, fileName);

            // ✅ Create directory
            Files.createDirectories(path.getParent());

            // ✅ Save file
            file.transferTo(path.toFile());

            // ✅ Save path in DB
            company.setLogo("/uploads/companies/" + fileName);

            return companyRepo.save(company);

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 ADD THIS
            throw new RuntimeException("Error uploading logo: " + e.getMessage());
        }

    }

    // ✅ GET ALL
    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getCompanyByEmail(String email) {
        return companyRepo.findByUser_Email(email).orElseThrow(()
                -> new ResourceNotFoundException("company not found with email: " + email));

    }


    // ✅ GET BY ID
    @Override
    public Company getCompanyById(int id) {
        return companyRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Company not found with id: " + id));
    }

    // ✅ DELETE
    @Override
    public void deleteCompany(int id) {
        Company company = getCompanyById(id);
        companyRepo.delete(company);
    }
}