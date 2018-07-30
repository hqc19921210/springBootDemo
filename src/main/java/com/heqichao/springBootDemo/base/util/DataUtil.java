package com.heqichao.springBootDemo.base.util;

import com.heqichao.springBootDemo.base.exception.ResponeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by heqichao on 2018-7-30.
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


    public static void main(String[] args) {
        System.out.println(getLocalIP());
    }

}
