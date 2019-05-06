package io.anand.springboot;

import java.util.Arrays;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(value="/users", description="Operations pertaining to Users")
public class UserController {

	@Autowired
	private UserService userService;

//	@RequestMapping(method=RequestMethod.GET, value="/users")
	@GetMapping("/users")
	@ApiOperation(value = "Returns list of users",
			notes = "")
	public List<User> getUsers () {
		return userService.getUsers();
	}

//	@RequestMapping(method=RequestMethod.GET, value="/users/{name}")
	@GetMapping("/users/{name}")
	@ApiOperation(value = "Lookup an user information by name",
			notes = "")
	public User getUser (@PathVariable String name) {
		User user = userService.getUser(name);
		if (null != user)
			return user;
		throw new ResponseStatusException(NOT_FOUND, "Unable to find student");
	}

	@RequestMapping(method=RequestMethod.PUT, value="/users/{name}")
	@PutMapping("/users/{name}")
	@ApiOperation(value = "Update an user information",
			notes = "")
	public User updUser (@PathVariable String name, @RequestBody User newUser) {
		User updUser = userService.updUser(name, newUser);
		if (null != updUser)
			return updUser;
		throw new ResponseStatusException(NOT_FOUND, "Unable to update student");
	}

	@RequestMapping(method=RequestMethod.POST, value="/users")
	@PostMapping("/users")
	@ApiOperation(value = "Create a new user",
			notes = "")
	public User addUser (@RequestBody User user) {
		User addedUser = userService.addUser(user);
		if (null != addedUser)
			return addedUser;
		throw new ResponseStatusException(NOT_ACCEPTABLE, "Unable to add student");
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/users/{name}")
	@DeleteMapping("/users/{name}")
	@ApiOperation(value = "Delete an user",
			notes = "")
	public User remUser (@PathVariable String name) {
		User remUser = userService.remUser(name);
		if (null != remUser)
			return remUser;
		throw new ResponseStatusException(NOT_FOUND, "Unable to delete student");
	}
}
