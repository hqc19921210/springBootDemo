package com.heqichao.springBootDemo.module.mapper;

import com.heqichao.springBootDemo.base.entity.User;
import com.heqichao.springBootDemo.module.entity.LightningLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by heqichao on 2018-7-15.
 */
public interface LightningLogMapper {

    @Select("SELECT * "
            + " FROM user "
            + "where ACCOUNT = #{account}  "
            + "and PASSWORD = #{password} "
            + "and STATUS = 'Y' ")
    public LightningLog getLightningLog(@Param("id") String id);

    @Select("<script>SELECT id,parent_id,account,company,contact,phone,fax,email,site,remark,competence"
            + " FROM user "
            + "where STATUS = 'Y'  "
            + "<if test=\"competence == 3 \"> and id = #{uId} or parent_id = #{uId} </if>"
            + " </script>")
    public List<User> getUsers(User user);

    @Select("select count(1)>0 from user where ACCOUNT = #{account} and STATUS = 'Y' ")
    public boolean duplicatedAccount(String account);

    @Select("select id from user where ACCOUNT = #{account} and password = #{password} and STATUS = 'Y' limit 1")
    public Integer checkPassword(@Param("account") String account,@Param("password") String password);

    @Insert("insert into user (parent_id,account,password,company,contact,phone,fax,email,site,remark,competence,STATUS,create_time,update_uid)"
            + " values(#{parentId},#{account},#{password},#{company},#{contact},#{phone},#{fax},#{email},#{site},#{remark},#{competence}, 'Y', sysdate(), #{upadteUID}) ")
    public int insertUser(User user);

    @Update("update user set password=#{password} , update_time = sysdate() , update_uid = #{udid} where id=#{id} and STATUS = 'Y' ")
    public int updateUserPassword(@Param("id")Integer uid,@Param("udid")Integer udid,@Param("password") String password);

    @Update("update user set  update_time = sysdate() , update_uid = #{udid} , STATUS ='N' where id=#{id} and STATUS = 'Y' ")
    public int delUserById(@Param("id")Integer uid,@Param("udid")Integer udid);
}
