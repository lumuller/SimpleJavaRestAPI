package com.luana.videolocadora.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luana.videolocadora.models.User;
import com.luana.videolocadora.service.UserService;


@RestController
@RequestMapping(value="/www.videolocadora.com")
public class UserResource {
	@Autowired
	UserService userService;
		
	//Métodos para CRUD de Usuarios
	@Secured("ROLE_ADMIN")
	@PostMapping("/users")	
	public User saveUser(@RequestBody User user) {		
		return userService.saveUser(user);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/users")
	public List<User> listUsers(){
		return userService.listUsers();
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/users/{username}")
	public String deleteUser(@PathVariable(value="username") String username)  {
		return userService.deleteUser(username);
	}
}
