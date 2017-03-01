package io.anand.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private ArrayList<User> users;

    public UserService () {
    	users = new ArrayList<User> ();
    	users.add(new User ("John", "Elementary", 192838, 145354545));
		users.add(new User ("Ravi", "Middle", 192298, 145312144));
		users.add(new User ("David", "Elementary", 192848, 145354652));
		users.add(new User ("Mary", "Middle", 192858, 145351234));
		users.add(new User ("Vinh", "High School", 192538, 14534567));
		users.add(new User ("Surya", "High School", 193138, 14538613));
    }
			
	public List<User> getUsers () {
		return users;
	}
	
	public User getUser (String userName) {
		return users.stream().filter(u -> u.getName().equals(userName)).findFirst().get();
	}
 	
}
