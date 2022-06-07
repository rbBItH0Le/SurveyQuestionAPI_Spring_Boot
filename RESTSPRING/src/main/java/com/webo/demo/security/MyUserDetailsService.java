package com.webo.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webo.demo.model.User;
import com.webo.demo.repository.UserioRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserioRepository userio;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userio.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("User 404");
		return new UserPrincipal(user);
	}

}
