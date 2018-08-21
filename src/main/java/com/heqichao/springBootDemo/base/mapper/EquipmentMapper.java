package com.heqichao.springBootDemo.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.heqichao.springBootDemo.base.entity.Equipment;
import com.heqichao.springBootDemo.base.entity.User;

/**
 * @author Muzzy Xu.
 * 
 * 
 */
public interface EquipmentMapper {
	
	@Select("SELECT id,eid,type,amount,range,alarms,IFNULL((select max(ligntningCount) from lightning_log where devEUI = equipment.eid and lightning_log.STATUS = '1111'),0) as total,e_status,online_time,remark,own_id"
			+ " FROM equipment where id = #{id}  and STATUS = 'Y' ")
	public Equipment getEquipmentById(@Param("id") Integer id);
	
	@Select("SELECT eid FROM equipment where own_id = #{uid}  and STATUS = 'Y' ")
	public List<String> getUserEquipmentIdList(@Param("uid") Integer uid);
	
	@Select("SELECT eid FROM equipment where e_status = #{status}  and STATUS = 'Y' ")
	public List<String> getEquipmentByStatus(@Param("status") String  status);
	
	@Select("SELECT eid FROM equipment where STATUS = 'Y' ")
	public List<String> getEquipmentIdListAll();
	
	@Select("<script>SELECT id,eid,e_type,IFNULL(amount,0) as amount,e_range,"
			+ "IFNULL((select max(ligntningCount) from lightning_log where devEUI = equipment.eid and lightning_log.STATUS = '1111'),0) as total,"
			+ "IFNULL(alarms,0) as alarms,e_status,online_time,remark,own_id"
			+ " FROM equipment where STATUS = 'Y'  "
			+ "<if test=\"competence == 3 \"> and own_id = #{id}  </if>"
			+ "<if test=\"competence == 4 \"> and own_id = #{parentId}  </if>"
			+ "<if test =\"sEid !=null  and sEid!='' \"> and eid like CONCAT(CONCAT('%',#{sEid}),'%')  </if>"
			+ "<if test =\"sType !=null  and sType!='' \"> and e_type like CONCAT(CONCAT('%',#{sType}),'%')  </if>"
			+ "<if test =\"sStatus !=null  and sStatus!='' \"> and e_status = #{sStatus}  </if>"
			+ " </script>")
	public List<Equipment> getEquipments(
			@Param("competence")Integer competence,
			@Param("id")Integer id,
			@Param("parentId")Integer parentId,
			@Param("sEid")String sEid,
			@Param("sType")String sType,
			@Param("sStatus")String sStatus);
	
	@Insert("insert into equipment (eid,e_type,amount,e_range,alarms,e_status,online_time,remark,own_id,status,update_uid)"
			+ " values(#{eid},#{eType},#{amount},#{eRange},#{alarms},#{eStatus},sysdate(),#{remark},#{ownId},'Y',#{updateUid}) ")
	public int insertEquipment(Equipment equ);
	

	
	@Update("update equipment set  update_time = sysdate(), update_uid = #{udid}, STATUS = 'N' where id=#{id} and STATUS = 'Y' ")
	public int delEquById(@Param("id")Integer eid,@Param("udid")Integer udid);
	
	@Update("update equipment set  e_status = #{status} where eid=#{eid} and STATUS = 'Y' ")
	public int setEquStatus(@Param("eid")String eid,@Param("status")String status);
	
	@Select("select count(1)>0 from equipment where eid = #{eid} and STATUS = 'Y' ")
	public boolean duplicatedEid(@Param("eid")String eid);

	@Update("update equipment set  e_range = #{range} where eid=#{eid} and STATUS = 'Y'")
	 int updateRange(@Param("eid")String eid,@Param("range")Integer range);

	@Select("select e_range from equipment where eid = #{eid} and STATUS = 'Y'")
	Integer queryRange(@Param("eid")String eid);
}
