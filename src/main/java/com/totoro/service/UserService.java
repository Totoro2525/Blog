package com.totoro.service;

import com.totoro.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
