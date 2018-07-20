package com.heqichao.springBootDemo.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.heqichao.springBootDemo.base.entity.Equipment;
import com.heqichao.springBootDemo.base.entity.User;

/**
 * @author Muzzy Xu.
 * 
 * 
 */
public interface EquipmentMapper {
	
	@Select("SELECT id,eid,type,amount,range,total,alarms,e_status,online_time,remark,own_id"
			+ " FROM user where id = #{id}  and STATUS = 'Y' ")
	public Equipment getUserInfo(@Param("id") Integer id);
	
	@Select("<script>SELECT id,eid,type,amount,range,total,alarms,e_status,online_time,remark,own_id"
			+ " FROM equipment "
			+ "where STATUS = 'Y'  "
			+ "<if test=\"competence == 3 \"> and own_id = #{id}  </if>"
			+ "<if test=\"competence == 4 \"> and own_id = #{parentId}  </if>"
			+ " </script>")
	public List<Equipment> getEquipments(User user);
	
	

}
