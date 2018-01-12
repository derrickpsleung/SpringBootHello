package com.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hello.model.BaseUser;
import com.hello.model.BaseUserDetails;
import com.hello.repository.BaseUserRepository;

@Service
public class HelloUserDetailsService implements UserDetailsService {

	@Autowired
	private BaseUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BaseUser baseUser = repository.findByUsername(username);
        if (baseUser == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return new BaseUserDetails(baseUser);
	}

}
