package io.anand.springboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public List<User> getUsers () {
		return userService.getUsers();
	}
	
	@RequestMapping("/users/{name}")
	public User getUser (@PathVariable String name) {
		return userService.getUser(name);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics/")
	public User addUser (@PathVariable String name) {
		return userService.getUser(name);
	}
}