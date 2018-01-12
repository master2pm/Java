package com.testplay.user.mapper;

import com.testplay.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface UserMapper {

    @Insert("insert into user (id,username,password,realname,phone,bith) values(#{id},#{user.username},#{user.password},#{user.realname},#{user.phone},#{user.bith})")
    @SelectKey(statement = "select replace(uuid(),'-','') from dual", before = true,keyProperty = "id" ,resultType = String.class)
    public boolean add(@Param("User") User user);
}
