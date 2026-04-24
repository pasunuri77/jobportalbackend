package com.jobportal.dto;

import com.jobportal.entity.Company;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyDto {

    Company createCompany(Company company, MultipartFile file);

    List<Company> getAllCompanies();

    Company getCompanyByEmail(String email);

    Company getCompanyById(int id);

    @Modifying
    @Transactional
    void deleteCompany(int id);


}
