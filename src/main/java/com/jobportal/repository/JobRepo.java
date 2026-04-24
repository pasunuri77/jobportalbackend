package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import com.jobportal.entity.Job;

import java.util.List;

public interface JobRepo extends JpaRepository<Job, Integer>{

    // 🔥 BEST QUERY (based on logged-in user)
    List<Job> findDistinctByCompany_User_Id(int userId);

    // Optional (keep if needed)
    List<Job> findByCompany_Id(int companyId);

    @Transactional
    @Modifying
    void deleteByCompany_Id(int companyId);
}