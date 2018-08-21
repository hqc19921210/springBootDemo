package com.heqichao.springBootDemo.module.mapper;

import com.heqichao.springBootDemo.module.entity.WarningLog;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by heqichao on 2018-7-15.
 */
public interface WarningLogMapper {

    @Select("SELECT * "
            + " FROM warning_log "
            + "where id = #{id}  ")
    WarningLog queryWarningLog(@Param("id") String id);

    @Select("<script>"
            +"SELECT *  FROM warning_log  where devEUI in  "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
            + "<if test =\"status !=null  and status!=''\"> and status = #{start} </if>"
            +"  order by status asc, createTime desc "
            +"</script>")
     List<WarningLog> queryWarningLogByDevIds(@Param("list") List<String> list, @Param("status") String status);

    @Insert("insert into warning_log (devEUI,time,fPort,gatewayCount,rssi,fCnt,loRaSNR,data,devicePath,functionCode,dataLen,status,createTime)"
            + " values( #{devEUI},#{time},#{fPort},#{gatewayCount},#{rssi},#{fCnt},#{loRaSNR},#{data},#{devicePath},#{functionCode},#{dataLen}, #{status},#{createTime} ) ")
     int saveWarningLog(WarningLog log);

    /**
     * 将故障的设备标记为已修复
     * @param status
     * @param updateTime
     * @param devEUI
     * @return
     */
    @Update("update warning_log set  status = #{status}, updateTime = #{updateTime}  where devEUI=#{devEUI} and status ='0' ")
    int updateStatus(@Param("status") String status, @Param("updateTime") Date updateTime, @Param("devEUI") String devEUI);

    @Select("<script>"
            +"SELECT count(id)  FROM warning_log  where devEUI in  "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
            + "<if test =\"status !=null  and status!=''\"> and status = #{status} </if>"
            +"</script>")
    int queryFaultCount(@Param("list") List<String> list, @Param("status") String status);
}
