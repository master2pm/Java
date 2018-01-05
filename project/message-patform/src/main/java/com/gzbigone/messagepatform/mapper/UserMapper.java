package com.gzbigone.messagepatform.mapper;

import com.gzbigone.messagepatform.entity.Jurisdiction;
import com.gzbigone.messagepatform.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from jurisdiction")
    List<Jurisdiction> getJurisdiction();
}
