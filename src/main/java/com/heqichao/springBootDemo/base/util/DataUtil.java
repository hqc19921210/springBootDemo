package com.heqichao.springBootDemo.base.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heqichao.springBootDemo.base.exception.ResponeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heqichao on 2018-7-30.
 * 
 * @version heqichao 	2018-7-30 	v1.0	init 		<br/>		
 * 			Muzzy Xu. 	2018-08-27 	v1.1	add json to map		 
 */
public class DataUtil {

    private static final Logger logger = LoggerFactory.getLogger(DataUtil.class);
    /**
     * 获取本机IP
     * @return
     */
    private static String ip;
    public static String getLocalIP() {
        if(ip!=null){
            return ip;
        }
        try {
            //		InetAddress address = InetAddress.getLocalHost();
            //		ip=address.getHostAddress();
            if(getLocalIPSByGateway().size()>0){
                ip=getLocalIPSByGateway().get(0);
            }else if(getLocalIPS().length>0){
                ip=getLocalIPS()[0];
            }else{
                ip="";
            }
            return ip;
        } catch (Exception e) {
            logger.error("getLocalIP",e);
            throw new ResponeException("getLocalIP",e);
        }
    }

    /**
     * 获取本机IPS
     * @return
     */
    private static String[] getLocalIPS() {
        try {
            ArrayList ipList=new ArrayList();
            Enumeration<NetworkInterface> netInterfaces= NetworkInterface.getNetworkInterfaces();
            while(netInterfaces.hasMoreElements()){
                NetworkInterface ni = netInterfaces
                        .nextElement();
                Enumeration nii = ni.getInetAddresses();
                while (nii.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) nii.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() &&  inetAddress.isSiteLocalAddress()) {
                        ipList.add(inetAddress.getHostAddress());
                    }
                }
            }
            String[] ips=new String[]{};
            ips=(String[])ipList.toArray(ips);
            return ips;
        } catch (Exception e) {
            logger.error("getLocalIPs",e);
            throw new ResponeException("getLocalIPs",e);
        }
    }

    /**
     * 根据网关过滤获取本机IPS
     * @return
     */
    private static List<String> getLocalIPSByGateway() {
        try {
            ArrayList ipList=new ArrayList();
            Enumeration<NetworkInterface> netInterfaces= NetworkInterface.getNetworkInterfaces();
            while(netInterfaces.hasMoreElements()){
                NetworkInterface ni = netInterfaces
                        .nextElement();
                Enumeration nii = ni.getInetAddresses();
                while (nii.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) nii.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() ) {
                        ipList.add(inetAddress.getHostAddress());
                    }
                }
            }
            return ipList;
        } catch (Exception e) {
            logger.error("getLocalIPSByGateway",e);
            throw new ResponeException("getLocalIPSByGateway",e);
        }
    }
    
    /**
     * 转换json字符串 to HashMap
     * @param String
     * @return
     */
    
    public static HashMap<String, Object> DecodejsonToHashMap(String object){  
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (object != null) {
			try {
				object = java.net.URLDecoder.decode(object, "UTF-8");
				ObjectMapper mapper = new ObjectMapper();
				map = mapper.readValue(object,
						new TypeReference<Map<String, Object>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	       return map;  
	   }  


    public static void main(String[] args) {
        System.out.println(getLocalIP());
    }

}
