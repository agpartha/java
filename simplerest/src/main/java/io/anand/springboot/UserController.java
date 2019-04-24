package io.anand.springboot;

import java.util.Arrays;
import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Users API", description="Operations pertaining to Users")
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
	
	@RequestMapping(method=RequestMethod.PUT, value="/users/{name}")
	public User updUser (@PathVariable String name, @RequestBody User newUser) {
	    return userService.updUser(name, newUser);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/")
	public boolean addUser (@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{name}")
	public boolean remUser (@RequestBody User user) {
	    return userService.remUser(user);
	}
}
