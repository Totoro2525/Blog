package com.totoro.service;

import com.totoro.dao.UserRepository;
import com.totoro.po.User;
import com.totoro.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User me() {
        List<User> all = userRepository.findAll();
        return all.get(0);
    }
}