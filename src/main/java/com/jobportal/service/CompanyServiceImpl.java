package com.jobportal.service;

import com.jobportal.cloudinary.CloudinaryService;
import com.jobportal.dto.CompanyDto;
import com.jobportal.entity.Company;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyDto {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    // ✅ CREATE COMPANY
    @Override
    public Company createCompany(Company company, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Logo file is required");
        }

        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("Only image files allowed");
        }

        String imageUrl = cloudinaryService.upload(file, "jobportal/companies");

        company.setLogo(imageUrl);

        return companyRepo.save(company);
    }

    // ✅ GET ALL
    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getCompanyByEmail(String email) {
        return companyRepo.findByUser_Email(email)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    // ✅ GET BY ID
    @Override
    public Company getCompanyById(int id) {
        return companyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    // ✅ DELETE
    @Override
    public void deleteCompany(int id) {
        Company company = getCompanyById(id);
        companyRepo.delete(company);
    }
}