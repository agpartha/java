package io.anand.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers () {
        return (List<User>) userRepository.findAll();
    }

    public User getUser (String userName) {
        return userRepository.findByName(userName);
    }

    public User updUser (String userName, User updUser) {
        return userRepository.save(updUser);
    }

    public User addUser (User user) {
        return userRepository.save(user);
    }

    public User remUser (String userName) {
        User remUser = userRepository.findByName(userName);
        if (null != remUser)
            userRepository.delete(remUser);
        return remUser;
     }
}
