package com.heqichao.springBootDemo.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Muzzy Xu.
 * 
 * 
 */
public interface HomeMapper {
	
	
	@Select("<script>select 0 from dual " 
			+" union all select count(1) from equipment where status = 'N'" 
			+ "<if test=\"competence == 3 \"> and own_id = #{id}  </if>"
			+ "<if test=\"competence == 4 \"> and own_id = #{parentId}  </if>"
			+" union all select count(1) from lite_equipment where status = 'N' "  
			+ "<if test=\"competence == 3 \"> and own_id = #{id}  </if>"
			+ "<if test=\"competence == 4 \"> and own_id = #{parentId}  </if>"
			+ " </script>")
	public List<Integer> queryPieData(
			@Param("competence")Integer competence,
			@Param("id")Integer id,
			@Param("parentId")Integer parentId);
	
	@Select("<script>" 
			+"select count(1) from user where status = 'Y' and competence= 3" 
			+" union all select count(1) from user where status = 'Y' and competence= 4"  
			+ " </script>")
	public List<Integer> queryUserData();
	
	@Select("<script>select sum(num) from (select 0 as num from dual " + 
			" union all select count(1) from lite_equipment where status = 'N' "  
			+ "<if test=\"competence == 3 \"> and own_id = #{id}  </if>"
			+ "<if test=\"competence == 4 \"> and own_id = #{parentId}  </if>"
			+" union all select count(1) from equipment where status = 'N'" 
			+ "<if test=\"competence == 3 \"> and own_id = #{id}  </if>"
			+ "<if test=\"competence == 4 \"> and own_id = #{parentId}  </if>"
			+ ") as t_all </script>")
	public Integer queryAllEqu(
			@Param("competence")Integer competence,
			@Param("id")Integer id,
			@Param("parentId")Integer parentId);
	
	@Select("<script>select count(1) from equipment where status = 'N' and e_status = 'N' "  
			+ "<if test=\"competence == 3 \"> and own_id = #{id}  </if>"
			+ "<if test=\"competence == 4 \"> and own_id = #{parentId}  </if>"
			+ " </script>")
	public Integer queryOnlineEqu(
			@Param("competence")Integer competence,
			@Param("id")Integer id,
			@Param("parentId")Integer parentId);
	
}
