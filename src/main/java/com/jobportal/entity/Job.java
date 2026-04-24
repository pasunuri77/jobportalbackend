package com.jobportal.entity;

import com.jobportal.enums.JobType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jobs")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Title is required")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String title;

	@NotBlank(message = "Company is required")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String companyName;

	@NotBlank(message = "Location is required")
	@Column(nullable = false)
	private String location;

	@NotNull(message = "Job type is required")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private JobType type;

	@NotBlank(message = "Salary is required")
	@Column(nullable = false)
	private String salary;

	@NotBlank(message = "Posted info is required")
	@Column(nullable = false)
	private String posted;

	@NotBlank(message = "Description is required")
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;

	@NotEmpty(message = "At least one requirement is required")
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(
			name = "job_requirements",
			joinColumns = @JoinColumn(name = "job_id")
	)
	@Column(name = "requirement")
	private List<String> requirements;

	@NotBlank(message = "Logo URL is required")
	@Column(nullable = false)
	private String logo;


	@ManyToOne
	@JoinColumn(name = "company_id",nullable = false)
	private Company company;

}
