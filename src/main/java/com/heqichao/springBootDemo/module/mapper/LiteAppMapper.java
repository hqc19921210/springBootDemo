package com.heqichao.springBootDemo.module.mapper;

import com.heqichao.springBootDemo.module.entity.LiteApplication;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Muzzy Xu.
 */
public interface LiteAppMapper {


	@Select("<script>"
            +"SELECT *  FROM lite_application  where 1=1 "
            + "<if test =\"cmp == 3 \"> and  own_id= #{uid}  </if>"
            + "<if test =\"cmp == 4 \"> and  own_id= #{pId}  </if>"
            + "<if test =\"appId !=null  and appId!='' \"> and app_id like CONCAT(CONCAT('%',#{appId}),'%')  </if>"
            + "<if test =\"appName !=null  and appName!='' \"> and app_name like CONCAT(CONCAT('%',#{appName}),'%')  </if>"
            +" and status = 'N' order by create_time desc "
            +"</script>")
     List<LiteApplication> queryLiteApps(
    		 @Param("uid") Integer uid,
    		 @Param("pId") Integer pId,
    		 @Param("cmp") Integer cmp,
    		 @Param("appId") String appId,
    		 @Param("appName") String appName);

	@Insert("insert into lite_application (app_id,app_name,secret,platform_ip,app_auth,callback_url,post_asyn_cmd,subscribe_notifycation,remark,own_id,status,update_uid)"
			+ " values(#{appId},#{appName},#{secret},#{platformIp},#{appAuth},#{callbackUrl},#{postAsynCmd},#{subscribeNotifycation},#{remark},#{ownId},'N',#{updateUid}) ")
	public int insertLiteApplication(LiteApplication app);
	
	@Update("update  lite_application set app_id=#{appId},app_name=#{appName},"
			+ "platform_ip=#{platformIp},app_auth=#{appAuth},callback_url=#{callbackUrl},post_asyn_cmd=#{postAsynCmd},subscribe_notifycation=#{subscribeNotifycation},"
			+ "remark=#{remark},own_id=#{ownId},update_time=sysdate(),update_uid=#{updateUid}"
			+ " where id=#{id} and status = 'N' ")
	public int updateLiteEquipment(LiteApplication app);
	
	@Update("update  lite_application set secret=#{secret},update_time=sysdate(),update_uid=#{updateUid}"
			+ " where id=#{id} and status = 'N' ")
	public int resetAppSecret(LiteApplication app);

	@Select("select count(1)>0 from lite_application where app_id = #{appId} and status = 'N' ")
	public boolean duplicatedAid(@Param("appId")String appId);
	
	@Select("select count(1)>0 from lite_application where app_id = #{appId} and status = 'N' and id not in (#{id}) ")
	public boolean duplicatedEidByUpd(@Param("appId")String appId,@Param("id")Integer id);


    @Delete("update lite_application set status = 'D',update_time=sysdate(),update_uid=#{uid} where status = 'N' and id= #{id}   ")
    int deleteById(@Param("id")Integer id,@Param("uid")Integer uid);
    
    @Delete("<script>"
    		+"update lite_log l,lite_equipment e set l.l_status = 'D',"
    		+ "l.updateTime=sysdate(),l.update_uid=#{uid} where l.l_status = 'N' and e.deviceId=l.deviceId "
    		+ "<if test =\"cmp == 3 \"> and  e.own_id= #{uid}  </if>"
            + "<if test =\"cmp == 4 \"> and  e.own_id= 0  </if>"
    		+ "   "
    		+"</script>")
    int deleteLiteAll(@Param("uid") Integer uid,
   		 @Param("pId") Integer pId,
   		 @Param("cmp") Integer cmp);
    
//    @Delete("update lite_application a,lite_equipment e set a.status = 'D',e.status = 'D',"
//    		+ "a.update_time=sysdate(),e.update_time=sysdate(),a.update_uid=#{uid},e.update_uid=#{uid} where a.status = 'N' or e.status = 'N'   ")
//    int deleteLiteAll(@Param("uid")Integer uid);


}
