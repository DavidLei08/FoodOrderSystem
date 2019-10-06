package com.edu.ahtcm.xg.fos.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IPAddrUtil {
	protected static final Logger logger =LoggerFactory.getLogger(IPAddrUtil.class);
	
	public static String getLocalIpAddr() {
		 
	    String clientIP = null;
	    Enumeration<NetworkInterface> networks = null;
	    try {
	        //获取所有网卡设备
	        networks = NetworkInterface.getNetworkInterfaces();
	        if (networks == null) {
	            //没有网卡设备 打印日志  返回null结束
	            logger.info("networks  is null");
	            return null;
	        }
	    } catch (SocketException e) {
	        System.out.println(e.getMessage());
	    }
	    InetAddress ip;
	    Enumeration<InetAddress> addrs;
	    // 遍历网卡设备
	    while (networks.hasMoreElements()) {
	        NetworkInterface ni = networks.nextElement();
	        try {
	            //过滤掉 loopback设备、虚拟网卡
	            if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
	                continue;
	            }
	        } catch (SocketException e) {
	            logger.info(e.getMessage());
	        }
	        addrs = ni.getInetAddresses();
	        if (addrs == null) {
	            logger.info("InetAddress is null");
	            continue;
	        }
	        // 遍历InetAddress信息
	        while (addrs.hasMoreElements()) {
	            ip = addrs.nextElement();
	            if (!ip.isLoopbackAddress() && ip.isSiteLocalAddress() && ip.getHostAddress().indexOf(":") == -1) {
	                try {
	                    clientIP = ip.toString().split("/")[1];
	                } catch (ArrayIndexOutOfBoundsException e) {
	                    clientIP = null;
	                }
	            }
	        }
	    }
	    return clientIP;
	}
}
