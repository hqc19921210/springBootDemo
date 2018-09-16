package com.heqichao.springBootDemo.module.mapper;

import com.heqichao.springBootDemo.module.entity.LightningLog;
import com.heqichao.springBootDemo.module.entity.LiteLog;
import com.heqichao.springBootDemo.module.entity.WarningLog;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by heqichao on 2018-7-15.
 * 
 * @version heqichao 	2018-7-15 	v1.0	init 		<br/>		
 * 			Muzzy Xu. 	2018-08-27 	v1.1	add 'l_status','deviceId' to table [lite_log] and update SQL
 */
public interface LiteLogMapper {


	@Select("<script>"
            +"SELECT *  FROM lite_log  where 1=1 "
            + "<if test =\"cmp == 3 \"> and deviceId in (select deviceId from lite_equipment where own_id= #{uid})  </if>"
            + "<if test =\"cmp == 4 \"> and deviceId in (select deviceId from lite_equipment where own_id= #{pId})  </if>"
            + "<if test =\"deviceId !=null  and deviceId!='' \"> and deviceId like CONCAT(CONCAT('%',#{deviceId}),'%')  </if>"
            + "<if test =\"start !=null  and start!=''\"> and eventTime &gt;= #{start} </if>" //大于等于
            + "<if test =\"end !=null  and end!='' \"> and eventTime &lt;= #{end} </if>"  // 小于等于
            +" and l_status not in ('D') order by createTime desc "
            +"</script>")
     List<LiteLog> queryLites(
    		 @Param("uid") Integer uid,
    		 @Param("pId") Integer pId,
    		 @Param("cmp") Integer cmp,
    		 @Param("deviceId") String deviceId,
    		 @Param("start") String start,
    		 @Param("end") String end);

    @Select("<script>"
            +"SELECT *  FROM lite_log where l_status = 'N'  order by  createTime desc "
            +"</script>")
     List<LiteLog> queryAll();

    @Insert("insert into lite_log (deviceId,message,currenState,eventTime,createTime,l_status)"
            + " values( #{deviceId},#{message},#{currenState}, #{eventTime},#{createTime},'N' ) ")
     int saveLog(LiteLog log);


    @Delete("update lite_log set l_status = 'C' where l_status = 'N'   ")
    int deleteAll();


}
