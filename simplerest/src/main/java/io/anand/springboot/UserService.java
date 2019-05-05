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
        return (List<User>) userRepository.findAll();
        // return users;
    }

    public User getUser (String userName) {
        User user = userRepository.findByName(userName);
        if (null == user)
            return user;

        try {
            return users.stream().filter(u -> u.getName().equals(user.getName())).findFirst().get();
        } catch (Exception e) {
            System.out.println("Caught Exception looking up user: " + userName + ", e: " + e);
        }
        return null;
    }

    public User updUser (String userName, User updUser) {
        User user = userRepository.save(updUser);
        if (null == user)
            return user;

        int index = users.indexOf(users.stream().filter(u -> u.getId() == (user.getId())).findFirst().get());
        users.set(index, updUser);
        return users.get(index);
    }

    public User addUser (User user) {
        User addedUser = userRepository.save(user);
        if (null == addedUser)
            return addedUser;

        users.add(user);
        return addedUser;
    }

    public boolean remUser (User user) {
        boolean deleted = users.remove(getUser(user.getName()));
        System.out.println(deleted ? "Able " : " Unable " + "to remove the user: " + user.toString());
        userRepository.delete(user);
        return deleted;
    }
}
