package com.luana.videolocadora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luana.videolocadora.models.User;
import com.luana.videolocadora.repository.UsersRepository;

@Service
public class UserService {
	@Autowired
	UsersRepository usersRepository;
	
	public User saveUser(User user) {		
		return usersRepository.save(user);
	}		
	
	public List<User> listUsers(){					
		List<User> allUsers = usersRepository.findAll();		
		allUsers.stream().forEach(u -> u.setPassword("######"));
		return allUsers;
	}		
	
	public String deleteUser(String username)  {		
		try {
			usersRepository.deleteById(username);	
			return String.format("User %s was removed successfully.", username);
		}catch(Exception e) {
			return String.format("A error happened while removing the user. Error information: %s", e.getMessage());
		}		
	}
}
