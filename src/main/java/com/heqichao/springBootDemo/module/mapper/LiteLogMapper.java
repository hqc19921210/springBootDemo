package com.heqichao.springBootDemo.module.mapper;

import com.heqichao.springBootDemo.module.entity.LiteLog;
import com.heqichao.springBootDemo.module.entity.WarningLog;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by heqichao on 2018-7-15.
 */
public interface LiteLogMapper {



    @Select("<script>"
            +"SELECT *  FROM lite_log  order by  createTime desc "
            +"</script>")
     List<LiteLog> queryAll();

    @Insert("insert into lite_log (message,currenState,eventTime,createTime)"
            + " values( #{message},#{currenState}, #{eventTime},#{createTime} ) ")
     int saveLog(LiteLog log);


    @Delete("delete from  lite_log ")
    int deleteAll();


}
