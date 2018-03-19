package com.heqichao.springBootDemo.base.mapper;

import com.heqichao.springBootDemo.system.entity.SystemUserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface SystemUserInfoMapper {
    @Select("SELECT * FROM SYSTEM_USER_INFO WHERE ID ='1' ")
    @Results({
            @Result(property = "userNo",  column = "USER_NO"),
            @Result(property = "NAME", column = "NAME")
    })
    List<SystemUserInfo> getAll();

    @Select("SELECT * FROM SYSTEM_USER_INFO  WHERE ID = #{id} ")
    @Results({
            @Result(property = "password",  column = "USER_PASSWORD")
    })
    SystemUserInfo getOne(String id);


    @Insert("insert into SYSTEM_USER_INFO (ID,USER_NO)VALUES (#{ID},#{userNo})")
    void saveSystemUserInfo(SystemUserInfo userInfo);
}
