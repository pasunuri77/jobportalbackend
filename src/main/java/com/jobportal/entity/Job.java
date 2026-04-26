package com.jobportal.entity;

import com.jobportal.enums.JobType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPosted() {
		return posted;
	}

	public void setPosted(String posted) {
		this.posted = posted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
