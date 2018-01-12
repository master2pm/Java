package com.vobo.user.mapper;

import com.vobo.user.entity.User;

import java.util.Map;

public interface UserMapper {

    Boolean addUser(User user);

    User sel(Map map);
}
