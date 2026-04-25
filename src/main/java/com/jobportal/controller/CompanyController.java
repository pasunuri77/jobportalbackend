package com.jobportal.controller;

import com.jobportal.entity.Company;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepo;
import com.jobportal.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = { "http://localhost:4200",
        "https://jobportalfrontend.vercel.app"})
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;
    @Autowired
    private UserRepo userRepo;

    // ✅ CREATE
    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(
            @ModelAttribute Company company,
            @RequestParam("file") MultipartFile file,
            Authentication auth) {

        String email = auth.getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        company.setUser(user);


        Company savedCompany = companyService.createCompany(company, file);
        return ResponseEntity.ok(savedCompany);
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    //get company by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Company> getCompaniesByUserEmail(Authentication authentication) {
        String email = authentication.getName();
        System.out.println("email" + email);
        Company company = companyService.getCompanyByEmail(email);
        return ResponseEntity.ok(company);
    }



    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted successfully");
    }
}