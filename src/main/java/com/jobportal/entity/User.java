package com.jobportal.entity;

import com.jobportal.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// 👤 Name
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 50, message = "Name must be 3-50 characters")
	private String name;

	// 📧 Email
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Column(unique = true)
	private String email;

	// 🔐 Password
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	// 🎭 Role

	@Enumerated(EnumType.STRING)
	private Role role;

}
