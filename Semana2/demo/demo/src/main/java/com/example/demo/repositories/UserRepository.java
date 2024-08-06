package com.example.demo.repositories;
import com.example.demo.model.User;

import jakarta.annotation.PostConstruct;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<User>();
    }

    @PostConstruct
    public void init() {
        users.add(new User(1, "John", 25));
        users.add(new User(2, "Jane", 22));
        users.add(new User(3, "Mike", 30));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }


}
