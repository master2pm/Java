package com.test.mapper;

import com.test.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface UserMapper {



    @Insert("insert into users(id,userid,pwd) values (#{id},#{userId},#{pwd})")
    @SelectKey(statement="select replace(uuid(),'-','') from dual" ,keyProperty="id",resultType=String.class,before = true)
    public boolean insertUsers (@Param("userId")String userId,@Param("pwd")String pwd);

    public boolean add(String userid);

    @Select("select * from users where userId = #{userId}")
    public User findUserByUserid(@Param("userId")String userId);
}
