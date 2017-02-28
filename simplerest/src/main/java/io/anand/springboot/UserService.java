package io.anand.springboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private List<User> users = Arrays.asList(
			new User ("John", "Elementary", 192838, 145354545),
			new User ("Ravi", "Middle", 192298, 145312144),
			new User ("David", "Elementary", 192848, 145354652),
			new User ("Mary", "Middle", 192858, 145351234),
			new User ("Vinh", "High School", 192538, 14534567),
			new User ("Surya", "High School", 193138, 14538613)
			);
	
	public List<User> getUsers () {
		return users;
	}
	
	public User getUser (String userName) {
		return users.stream().filter(u -> u.getName().equals(userName)).findFirst().get();
	}
 	
}
