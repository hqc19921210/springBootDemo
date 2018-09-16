package com.heqichao.springBootDemo.module.mapper;

import com.heqichao.springBootDemo.module.entity.LiteCommand;
import com.heqichao.springBootDemo.module.entity.LiteEquipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Muzzy Xu.
 */
public interface LiteEquMapper {


	@Select("<script>"
            +"SELECT e.*,a.app_name  FROM lite_equipment e,lite_application a  where 1=1 "
            + "<if test =\"cmp == 3 \"> and  e.own_id= #{uid}  </if>"
            + "<if test =\"cmp == 4 \"> and  e.own_id= #{pId}  </if>"
            + "<if test =\"deviceId !=null  and deviceId!='' \"> and e.deviceId like CONCAT(CONCAT('%',#{deviceId}),'%')  </if>"
            + "<if test =\"name !=null  and name!='' \"> and e.name like CONCAT(CONCAT('%',#{name}),'%')  </if>"
            + "<if test =\"agreement !=null  and agreement!='' \"> and e.agreement like CONCAT(CONCAT('%',#{agreement}),'%')  </if>"
            +" and e.status = 'N' and e.app_id = a.id order by e.create_time desc "
            +"</script>")
     List<LiteEquipment> queryLiteEqus(
    		 @Param("uid") Integer uid,
    		 @Param("pId") Integer pId,
    		 @Param("cmp") Integer cmp,
    		 @Param("deviceId") String deviceId,
    		 @Param("name") String name,
    		 @Param("agreement") String agreement);
	
	@Select("SELECT e.*,a.app_id as app_id_detial,a.secret,a.app_auth,a.callback_url,a.post_asyn_cmd "
			+"  FROM lite_equipment e,lite_application a  where e.status = 'N' and e.app_id = a.id and e.id=#{eid} "
			)
	LiteCommand queryLiteEquForCmd(@Param("eid") Integer eid);

	@Insert("insert into lite_equipment (app_id,deviceId,name,verification,type,support_id,support_name,agreement,online_time,remark,own_id,status,update_uid)"
			+ " values(#{appId},#{deviceId},#{name},#{verification},#{type},#{supportId},#{supportName},#{agreement},sysdate(),#{remark},#{ownId},'N',#{updateUid}) ")
	public int insertLiteEquipment(LiteEquipment equ);
	
	@Update("update  lite_equipment set app_id=#{appId},deviceId=#{deviceId},name=#{name},verification=#{verification},"
			+ "type=#{type},support_id=#{supportId},support_name=#{supportName},agreement=#{agreement},"
			+ "remark=#{remark},own_id=#{ownId},update_time=sysdate(),update_uid=#{updateUid}"
			+ " where id=#{id} ")
	public int updateLiteEquipment(LiteEquipment equ);
	
	@Update("update  lite_equipment set service_id=#{serviceId},method=#{method},param_field=#{paramField},param_value=#{paramValue}"
			+ " where id=#{id} ")
	public int updateLiteEquipmentForCmd(LiteCommand equ);

	/**
	 * 检查设备与应用拥有者是否一致，true为不一致
	 * @param oid
	 * @return
	 */
	@Select("select count(1)=0 from lite_application where own_id = #{oId} and status = 'N' ")
	public boolean checkAppOwnId(@Param("oId")Integer oid);
	
	@Select("select count(1)>0 from lite_equipment where deviceId = #{deviceId} and status = 'N' ")
	public boolean duplicatedEid(@Param("deviceId")String deviceId);
	
	@Select("select count(1)>0 from lite_equipment where deviceId = #{deviceId} and status = 'N' and id not in (#{id}) ")
	public boolean duplicatedEidByUpd(@Param("deviceId")String deviceId,@Param("id")Integer id);

	@Select("<script>"
			+"select id as appId,app_name as appName from lite_application a where 1=1 "
			+ "<if test =\"cmp == 3 \"> and  a.own_id= #{uid}  </if>"
            + "<if test =\"cmp == 4 \"> and  a.own_id= #{pId}  </if>"
			+ "and STATUS = 'N' "
			+"</script>")
	public List<LiteEquipment> getAppSelectList(
		@Param("uid") Integer uid,
   		 @Param("pId") Integer pId,
   		 @Param("cmp") Integer cmp);
	
    @Delete("update lite_equipment set status = 'C',update_time=sysdate(),update_uid=#{uid} where status = 'N' and id= #{id}   ")
    int deleteById(@Param("id")Integer id,@Param("uid")Integer uid);


}
