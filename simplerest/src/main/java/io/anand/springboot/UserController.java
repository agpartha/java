package io.anand.springboot;

import java.util.Arrays;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

	@RequestMapping(method=RequestMethod.GET, value="/users")
	@ApiOperation(value = "Returns list of users",
			notes = "")
	public List<User> getUsers () {
		return userService.getUsers();
	}

	@RequestMapping(method=RequestMethod.GET, value="/users/{name}")
	@ApiOperation(value = "Lookup an user information by name",
			notes = "")
	public User getUser (@PathVariable String name) {
		return userService.getUser(name);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/users/{name}")
	@ApiOperation(value = "Update an user information",
			notes = "")
	public User updUser (@PathVariable String name, @RequestBody User newUser) {
	    return userService.updUser(name, newUser);
	}

	@RequestMapping(method=RequestMethod.POST, value="/users/")
	@ApiOperation(value = "Create a new user",
			notes = "")
	public boolean addUser (@RequestBody User user) {
		return userService.addUser(user);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/users/{name}")
	@ApiOperation(value = "Delete a new user",
			notes = "")
	public boolean remUser (@RequestBody User user) {
	    return userService.remUser(user);
	}
}
