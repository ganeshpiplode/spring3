package com.studentmanagement.payloads;

import com.studentmanagement.entities.Users;
import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private Users user;
}
