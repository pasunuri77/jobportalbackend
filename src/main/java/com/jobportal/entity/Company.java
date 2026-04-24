package com.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // ✅ matches frontend
    @NotBlank(message = "Company name is required")
    private String name;

    @NotBlank(message = "Industry is required")
    private String industry;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Size is required")
    private String size;

    @NotBlank(message = "Description is required")
    @Column(length = 500)
    private String description;

    @NotBlank(message = "Logo is required")
    private String logo;

    // ✅ new fields
    @NotNull(message = "Rating is required")
    private Double rating;

    @NotNull(message = "Open positions required")
    private Integer openPositions;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}