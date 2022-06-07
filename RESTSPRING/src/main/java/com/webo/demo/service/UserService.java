package com.webo.demo.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webo.demo.model.Users;
import com.webo.demo.repository.UserRepository;

@Service
public class UserService {
@Autowired
private UserRepository userrepo;

public Users insert(Users user)
{
	Users user1=userrepo.save(user);
	return user1;
}

}
