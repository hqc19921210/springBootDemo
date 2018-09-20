package com.heqichao.springBootDemo.module.mapper;

import com.heqichao.springBootDemo.module.entity.LightningLog;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by heqichao on 2018-7-15.
 */
public interface LightningLogMapper {

    @Update("<script>"
            +"update  lightning_log set status='-1'  where devEUI in  "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
            + "<if test =\"functionCode !=null   and functionCode!=''  \"> and functionCode like CONCAT(CONCAT('%',#{functionCode}),'%')  </if>"
            + "<if test =\"devEUI !=null  and devEUI!='' \"> and devEUI like CONCAT(CONCAT('%',#{devEUI}),'%')  </if>"
            + "<if test =\"start !=null  and start!=''\"> and ligntningTime &gt;= #{start} </if>" //大于等于
            + "<if test =\"end !=null  and end!='' \"> and ligntningTime &lt;= #{end} </if>"  // 小于等于
            +" and status ='1111' "
            +"</script>")
    int deleteLightningLogByDevIds(@Param("list") List<String> list,@Param("devEUI") String devEUI,@Param("end") String end,@Param("start") String start,@Param("functionCode") String functionCode);

    @Select("<script>"
            +"SELECT *  FROM lightning_log  where devEUI in  "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
            + "<if test =\"functionCode !=null   and functionCode!=''  \"> and functionCode like CONCAT(CONCAT('%',#{functionCode}),'%')  </if>"
            + "<if test =\"devEUI !=null  and devEUI!='' \"> and devEUI like CONCAT(CONCAT('%',#{devEUI}),'%')  </if>"
            + "<if test =\"start !=null  and start!=''\"> and ligntningTime &gt;= #{start} </if>" //大于等于
            + "<if test =\"end !=null  and end!='' \"> and ligntningTime &lt;= #{end} </if>"  // 小于等于
            +" and status ='1111' order by createTime desc  "
            +"</script>")
     List<LightningLog> queryLightningLogByDevIds(@Param("list") List<String> list,@Param("devEUI") String devEUI,@Param("end") String end,@Param("start") String start,@Param("functionCode") String functionCode);

    @Select("<script>"
            +"SELECT id,peakValue,effectiveValue,waveHeadTime,halfPeakTime,actionTime,energy,createTime,ligntningTime  FROM lightning_log  where devEUI in  "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
            + " and id &lt;= #{logId} and devEUI = #{devEUI} and ligntningTime is not null and ligntningTime !='' " // 小于等于
            +" and status ='1111' order by ligntningTime desc  "
            +"  limit  0,${limitNum}  "
            +"</script>")
    List<Map> queryLigChatLeft(@Param("list") List<String> list,@Param("devEUI") String devEUI,@Param("logId") String logId,@Param("limitNum")int limitNum);

    @Select("<script>"
            +"SELECT id,peakValue,effectiveValue,waveHeadTime,halfPeakTime,actionTime,energy,createTime,ligntningTime  FROM lightning_log  where devEUI in  "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
            + " and id &gt; #{logId} and devEUI = #{devEUI} and ligntningTime is not null and ligntningTime !=''" // 大于
            +" and status ='1111' order by ligntningTime asc  "
            +"  limit  0,${limitNum}  "
            +"</script>")
    List<Map> queryLigChatRight(@Param("list") List<String> list,@Param("devEUI") String devEUI,@Param("logId") String logId,@Param("limitNum")int limitNum);


    @Select("<script>"
            +"SELECT  id,peakValue,effectiveValue,waveHeadTime,halfPeakTime,actionTime,energy,createTime,ligntningTime  FROM lightning_log  where devEUI in  "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
            + "<if test =\"devEUI !=null  and devEUI!='' \"> and devEUI = #{devEUI}  </if>"
            + "<if test =\"start !=null  and start!=''\"> and ligntningTime &gt;= #{start} </if>" //大于等于
            + "<if test =\"end !=null  and end!='' \"> and ligntningTime &lt;= #{end} </if>"  // 小于等于
            +" and status ='1111' and ligntningTime is not null and ligntningTime !='' order by ligntningTime asc  "
            +"</script>")
    List<Map> queryLigChatAll(@Param("list") List<String> list,@Param("devEUI") String devEUI,@Param("end") String end,@Param("start") String start);



    @Insert("insert into lightning_log (devEUI,time,fPort,gatewayCount,rssi,fCnt,loRaSNR,data,devicePath,functionCode,dataLen,ligntningCount,ligntningTime,peakValue,effectiveValue,waveHeadTime,halfPeakTime,actionTime,energy,status)"
            + " values(#{devEUI},#{time},#{fPort},#{gatewayCount},#{rssi},#{fCnt},#{loRaSNR},#{data},#{devicePath},#{functionCode},#{dataLen}, #{ligntningCount}, #{ligntningTime}, #{peakValue}, #{effectiveValue}, #{waveHeadTime}, #{halfPeakTime}, #{actionTime}, #{energy}, #{status}) ")
     int saveLightningLog(LightningLog log);


    @Select("select DISTINCT devEUI from lightning_log where createTime >= now()-interval #{time} minute")
    List<String> queryLogOnTime(@Param("time") int time);


    @Select("<script>"
            +"select DATE_FORMAT(ligntningTime,'%m') months,count(id) count from lightning_log "
    		+ " where ligntningTime is not null and ligntningTime > #{year} and devEUI in "
            + "<foreach  collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"uid\" >"
            + "#{uid}"
            + "</foreach>"
    		+ " group by months order by months asc"
            +"</script>")
    List<Map> queryLightCountByYear(@Param("year") String year,@Param("list") List<String> list);


}
