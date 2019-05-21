package io.anand.springboot;

import java.util.Arrays;
import java.util.List;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
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

	@Autowired
	private MetricsService metricsService;

	private MeterRegistry meterRegistry;

	private Counter getAllCounter;
	private Counter getCounter;
	private Counter putCounter;
	private Counter postCounter;
	private Counter deleteCounter;

	private void initMetrics () {

		getAllCounter 	= Metrics.counter("simplerest.GET.ALL", "resource", "User");
		getCounter 		= Metrics.counter("simplerest.GET", "resource", "User");
		putCounter 		= Metrics.counter("simplerest.PUT", "resource", "User");
		postCounter		= Metrics.counter("simplerest.POST", "resource", "User");
		deleteCounter	= Metrics.counter("simplerest.DELETE", "resource", "User");
	}

	private void initMetrics (MeterRegistry meterRegistry) {

		getAllCounter 	= meterRegistry.counter("simplerest.GET.ALL", "resource", "User");
		getCounter 		= meterRegistry.counter("simplerest.GET", "resource", "User");
		putCounter 		= meterRegistry.counter("simplerest.PUT", "resource", "User");
		postCounter		= meterRegistry.counter("simplerest.POST", "resource", "User");
		deleteCounter	= meterRegistry.counter("simplerest.DELETE", "resource", "User");
	}

	public UserController (MeterRegistry meterRegistry) {

		// Create the counter using the helper method on the builder
		//myCounter = meterRegistry.counter("my.counter", "mytagname", "mytagvalue");
		this.meterRegistry = meterRegistry;
		initMetrics();
	}

	//	@RequestMapping(method=RequestMethod.GET, value="/users")
	@GetMapping("/users")
	@ApiOperation(value = "Returns list of users",
			notes = "")
	public List<User> getUsers () {
		getAllCounter.increment();
		return userService.getUsers();
	}

//	@RequestMapping(method=RequestMethod.GET, value="/users/{name}")
	@GetMapping("/users/{name}")
	@ApiOperation(value = "Lookup an user information by name",
			notes = "")
	public User getUser (@PathVariable String name) {
		getCounter.increment();
		User user = userService.getUser(name);
		if (null != user)
			return user;
		throw new ResponseStatusException(NOT_FOUND, "Unable to find student");
	}

//	@RequestMapping(method=RequestMethod.PUT, value="/users/{name}")
	@PutMapping("/users/{name}")
	@ApiOperation(value = "Update an user information",
			notes = "")
	public User updUser (@PathVariable String name, @RequestBody User newUser) {
		putCounter.increment();
		User updUser = userService.updUser(name, newUser);
		if (null != updUser)
			return updUser;
		throw new ResponseStatusException(NOT_FOUND, "Unable to update student");
	}

//	@RequestMapping(method=RequestMethod.POST, value="/users")
	@PostMapping("/users")
	@ApiOperation(value = "Create a new user",
			notes = "")
	public User addUser (@RequestBody User user) {
		postCounter.increment();
		User addedUser = userService.addUser(user);
		if (null != addedUser)
			return addedUser;
		throw new ResponseStatusException(NOT_ACCEPTABLE, "Unable to add student");
	}

//	@RequestMapping(method=RequestMethod.DELETE, value="/users/{name}")
	@DeleteMapping("/users/{name}")
	@ApiOperation(value = "Delete an user",
			notes = "")
	public User remUser (@PathVariable String name) {
		deleteCounter.increment();
		User remUser = userService.remUser(name);
		if (null != remUser)
			return remUser;
		throw new ResponseStatusException(NOT_FOUND, "Unable to delete student");
	}
}
