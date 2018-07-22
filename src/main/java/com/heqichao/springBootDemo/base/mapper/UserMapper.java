package com.heqichao.springBootDemo.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.heqichao.springBootDemo.base.entity.User;

/**
 * @author Muzzy Xu.
 * 
 * 
 */
public interface UserMapper {
	
	@Select("SELECT id,parent_id,account,company,contact,phone,fax,email,site,remark,competence"
			+ " FROM user "
			+ "where ACCOUNT = #{account}  "
			+ "and PASSWORD = #{password} "
			+ "and STATUS = 'Y' ")
	public User getUserInfo(@Param("account") String account,@Param("password") String password);
	
	@Select("<script>SELECT id,parent_id,account,company,contact,phone,fax,email,site,remark,competence"
			+ " FROM user "
			+ "where STATUS = 'Y'  "
			+ "<if test=\"competence == 3 \"> and id = #{id} or parent_id = #{id} </if>"
			+ " </script>")
	public List<User> getUsers(User user);
	
	@Select("select id,company from user where competence = 3 and STATUS = 'Y'")
	public List<User> getCompanySelectList();
	
	@Select("select count(1)>0 from user where (ACCOUNT = #{account} or company = #{company}) and STATUS = 'Y' ")
	public boolean duplicatedAccount(@Param("account")String account,@Param("company")String company);
	
	@Select("select id from user where ACCOUNT = #{account} and password = #{password} and STATUS = 'Y' limit 1")
	public Integer checkPassword(@Param("account") String account,@Param("password") String password);
	
	@Insert("insert into user (parent_id,account,password,company,contact,phone,fax,email,site,remark,competence,STATUS,create_time,update_uid)"
			+ " values(#{parentId},#{account},#{password},#{company},#{contact},#{phone},#{fax},#{email},#{site},#{remark},#{competence}, 'Y', sysdate(), #{upadteUID}) ")
	public int insertUser(User user);
	
	@Update("update user set password=#{password}, update_time = sysdate(), update_uid = #{udid} where id=#{id} and STATUS = 'Y' ")
	public int updateUserPassword(@Param("id")Integer uid,@Param("udid")Integer udid,@Param("password") String password);
	
	@Update("update user set company=#{company}, contact=#{contact}, phone=#{phone}, fax=#{fax}, email=#{email}, site=#{site}, remark=#{remark}, update_time = sysdate(), update_uid = #{id} where id=#{id} and STATUS = 'Y' ")
	public int updateUserInfo(User user);
	
	@Update("update user set  update_time = sysdate(), update_uid = #{udid}, STATUS = 'N' where id=#{id} and STATUS = 'Y' ")
	public int delUserById(@Param("id")Integer uid,@Param("udid")Integer udid);

}
