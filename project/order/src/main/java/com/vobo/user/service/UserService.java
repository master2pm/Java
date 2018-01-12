package com.vobo.user.service;

import com.vobo.user.entity.User;

import java.util.Map;

public interface UserService {

    Boolean addUser(User user);

    User sel(String id,String password);
}
