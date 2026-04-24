package com.jobportal.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.jobportal.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);


    Optional<User> findByEmail(String email);


    @Modifying
    @Transactional
    void deleteById(int id);
}
