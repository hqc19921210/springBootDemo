package com.heqichao.springBootDemo.base.mapper;

import com.heqichao.springBootDemo.base.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface UserInfoMapper {
    @Select("SELECT * FROM USER_INFO where 1=1  order by id desc ")
    @Results({
          //  @Result(property = "createTime",  column = "createTime", jdbcType= JdbcType.VARCHAR)
        /*    @Result(property = "NAME", column = "NAME")*/

    })
    List<UserInfo> getAll();

    @Select("SELECT * FROM USER_INFO  WHERE ID = #{id} ")
    @Results({
         //   @Result(property = "createTime",  column = "createTime", jdbcType= JdbcType.TIMESTAMP)
    })
    UserInfo getOne(String id);


    @Insert("insert into USER_INFO (ID,USER_NO)VALUES (#{ID},#{userNo})")
    void saveSystemUserInfo(UserInfo userInfo);
}
