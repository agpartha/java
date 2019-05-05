package io.anand.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

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
        // Hack to save on first get of all users
        return (List<User>) userRepository.findAll();
        /*
        userRepository.saveAll(users);
        return users;
         */
    }

    public User getUser (String userName) {
        return userRepository.findByName(userName);
        /*
        try {
            return users.stream().filter(u -> u.getName().equals(userName)).findFirst().get();
        } catch (Exception e) {
            System.out.println("Caught Exception looking up user: " + userName + ", e: " + e);
        }
        return null;
        */
    }

    public User updUser (String userName, User updUser) {
        userRepository.save(updUser);
        int index = users.indexOf(users.stream().filter(u -> u.getName().equals(userName)).findFirst().get());
        users.set(index, updUser);
        return users.get(index);
    }

    public boolean addUser (User user) {

        userRepository.save(user);
        boolean userAdded = users.add(user);
        return userAdded;
    }

    public boolean remUser (User user) {
        userRepository.delete(user);
        boolean deleted = users.remove(getUser(user.getName()));
        System.out.println(deleted ? "Able " : " Unable " + "to remove the user: " + user.toString());
        return deleted;
    }
}
