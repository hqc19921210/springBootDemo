package com.heqichao.springBootDemo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.system.entity.SystemUserInfo;
import com.heqichao.springBootDemo.system.service.LoginService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//controller注解
@RestController
//application注解
// @SpringBootApplication = @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
//@SpringBootConfiguration = @Configuration = @Component
@MapperScan("com.heqichao.springBootDemo.*.mapper") //扫描mapper的路劲
@EnableTransactionManagement // 开启事物
//@ComponentScan(basePackages = {"com.heqichao.page"})  扫描包下的子包、类
//@EnableScheduling  整合quartz  开启定时任务功能
//@EnableAsync  开启异步任务
@SpringBootApplication
public class SpringBootDemoApplication {


	@Autowired
	private LoginService loginSerice;


	/**
	 * 在这里我们使用 @Bean注入 fastJsonHttpMessageConvert
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 1、需要先定义一个 convert 转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		//3、在convert中添加配置信息.
		//处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	/*	int x=-1230;
		String a =x+"";
		String b="";
		int i=a.length()-1;
		while(i>=0){
			b=b+a.charAt(i);
			i--;
		}
		if(b.startsWith("0")){
			b=b.substring(1,b.length());
		}
		if(b.indexOf("-")>0){
			b="-"+b.substring(0,b.length()-1);
		}

		try{
			i=  Integer.parseInt(b);
		}catch(Exception e){
			i=0;
		}
		System.out.println(i);*/
	}




	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	@ResponseBody
	public ResponeResult login(String userNo,String password){
		ResponeResult responeResult =new ResponeResult(false,"userIsNotLogin");

		if(loginSerice.volicateLogin(userNo,password)){
			SystemUserInfo userInfo =new SystemUserInfo();
			userInfo.setPassword("123456");
			userInfo.setUserNo("admin");
			userInfo.setNAME("管理员");
			responeResult.setResultObj(userInfo);
			responeResult.setSuccess(true);
		}
		return responeResult;
	}
}
