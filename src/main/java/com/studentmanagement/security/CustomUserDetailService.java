package com.studentmanagement.security;

import com.studentmanagement.entities.Users;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by username
		Users user = this.userRepo.findByEmail(username);
		if(user==null)
		new ResourceNotFoundException("User ", " email : " + username, "");

		return user;
	}

}
