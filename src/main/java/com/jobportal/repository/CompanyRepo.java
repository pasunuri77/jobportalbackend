package com.jobportal.repository;


import com.jobportal.entity.Job;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.Company;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface CompanyRepo  extends JpaRepository<Company, Integer>{


    Optional<Company> findByUser_Email(String email);

    Optional<Company> findByUser_Id(int userId);


}
