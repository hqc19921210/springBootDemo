package com.heqichao.springBootDemo.module.mapper;

import com.heqichao.springBootDemo.module.entity.LightningLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by heqichao on 2018-7-15.
 */
public interface LightningLogMapper {

    @Select("SELECT * "
            + " FROM lightning_log "
            + "where id = #{id}  ")
     LightningLog getLightningLog(@Param("id") String id);

    @Select("<script>"
            +"SELECT *  FROM lightning_log  where devEUI in  "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
            + "<if test =\"functionCode !=null   and functionCode!=''  \"> and functionCode like CONCAT(CONCAT('%',#{functionCode}),'%')  </if>"
            + "<if test =\"devEUI !=null  and devEUI!='' \"> and devEUI like CONCAT(CONCAT('%',#{devEUI}),'%')  </if>"
            + "<if test =\"start !=null  and start!=''\"> and ligntningTime &gt;= #{start} </if>"
            + "<if test =\"end !=null  and end!='' \"> and ligntningTime &lt;= #{end} </if>"  // <=
            +"</script>")
     List<LightningLog> queryLightningLogByDevIds(@Param("list") List<String> list,@Param("devEUI") String devEUI,@Param("end") String end,@Param("start") String start,@Param("functionCode") String functionCode);

    @Insert("insert into lightning_log (devEUI,time,fPort,gatewayCount,rssi,fCnt,loRaSNR,data,devicePath,functionCode,dataLen,ligntningCount,ligntningTime,peakValue,effectiveValue,waveHeadTime,halfPeakTime,actionTime,energy,status)"
            + " values(#{devEUI},#{time},#{fPort},#{gatewayCount},#{rssi},#{fCnt},#{loRaSNR},#{data},#{devicePath},#{functionCode},#{dataLen}, #{ligntningCount}, #{ligntningTime}, #{peakValue}, #{effectiveValue}, #{waveHeadTime}, #{halfPeakTime}, #{actionTime}, #{energy}, #{status}) ")
     int saveLightningLog(LightningLog log);


    @Delete("delete from lightning_log" + "where devEUI in  "
            + "<foreach item='item' index='index' collection='devIds' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>")
     int deleteLightningLogByDevIds(@Param("devIds") List<String> devIds);
}
