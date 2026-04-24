package com.jobportal.dto;


import java.util.List;
import java.util.Map;

import com.jobportal.entity.Application;
import com.jobportal.entity.User;

public interface UserDto {
	
	Map<String,Object> login(String email, String password);

	List<User> getAllUser();
//
//	List<Application> getAllApplications(int userId);

	String updatePasswordByEmail(String email, String newPassword);

	void deleteUser(int id);


}
