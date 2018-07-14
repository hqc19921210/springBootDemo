package com.heqichao.springBootDemo.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.heqichao.springBootDemo.base.entity.User;

/**
 * @author Muzzy Xu.
 * 
 * 
 */
public interface UserMapper {
	
	@Select("SELECT u_id,parent_id,account,company,contact,phone,fax,email,site,remark,competence"
			+ " FROM user "
			+ "where ACCOUNT = #{account}  "
			+ "and PASSWORD = #{password} "
			+ "and STATUS = 'Y' ")
	public User getUserInfo(@Param("account") String account,@Param("password") String password);
	
	@Select("<script>SELECT u_id,parent_id,account,company,contact,phone,fax,email,site,remark,competence"
			+ " FROM user "
			+ "where STATUS = 'Y'  "
			+ "<if test=\"parentId == 3 \"> and u_id = #{uId} or parent_id = #{uId} </if>"
			+ "and STATUS = 'Y' </script>")
	public List<User> getUsers(User user);
}
