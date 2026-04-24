package com.jobportal.entity;

import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "job_id", nullable = false)
	private Job job;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;

	// ✅ FIX NAME
	@Column(name = "resume_path")
	private String resumePath;
}