package com.jobportal.repository;

import com.jobportal.entity.Company;
import com.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.Application;
import com.jobportal.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application, Integer> {

    List<Application> findByUser(User user);

    List<Application> findByCompany_User_Email(String email);


    void deleteByUser_Id(int userId);


    void deleteByCompany_Id(int companyId);


    void deleteByJob_Id(int jobId);

    List<Application> findByUser_Id(int userId);

    List<Application> findByUser_Email(String email);

    boolean existsByUser_IdAndJob_Id(int userId, int jobId);


    void deleteByCompanyId(int companyId);

    void deleteByJob_Company_Id(int companyId);


}
