package com.mfq.utils;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DnsUtil {

	private static Logger logger = LoggerFactory.getLogger(DnsUtil.class);
	
	private static String localHost = "";
	private static String hostAddress = "";
	private static String hostName = "";
	private static String localSiteAddress = "";
	private static String localAddress = "";

	static {
		try {
			localHost = InetAddress.getLocalHost().toString();
			hostAddress = InetAddress.getLocalHost().getHostAddress();
			hostName = InetAddress.getLocalHost().getHostName();
			String addrs = "";
			String addrp = "";
			try {
				for (NetworkInterface ifc : Collections.list(NetworkInterface.getNetworkInterfaces())) {
					if (ifc.isUp() && !ifc.isLoopback()) {
						for (InetAddress addr : Collections.list(ifc.getInetAddresses())) {
							if (addr instanceof Inet4Address){
								addrs += "-" + addr.getHostAddress();
								//若是私有地址
								if(addr.isSiteLocalAddress()) {
									addrp += "-" + addr.getHostAddress();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				//ignore all exception
			}
			localSiteAddress = addrp.length() > 0 ? addrs.substring(1) : "127.0.0.1";
			localAddress = addrs.length() > 0 ? addrs.substring(1) : "127.0.0.1";
		}
		catch (UnknownHostException e) {
			logger.error("UnknownHostException:", e);
		}
	}

	public static String getLocalHost() {
		return localHost;
	}

	public static String getHostAddress() {
		return hostAddress;
	}

	public static String getHostName() {
		return hostName;
	}


	/**
	 * 获取所有私有地址信息（不包括公网IP地址信息），以'-'分隔多个私有地址，不包括回环地址。当且仅当无法获取私有地址时返回回环地址(
	 * 127.0.0.1)
	 * <p>
	 * 私有地址包括如下三类：
	 * <ul>
	 * <li>A类 10.0.0.0 --10.255.255.255</li>
	 * <li>B类 172.xx.xx.xx--172.xx.255.255</li>
	 * <li>C类 192.168.0.0--192.168.255.255</li>
	 * </ul>
	 * 忽略IPv6的所有地址
	 * </p>
	 *
	 * @return 所有私有地址信息
	 */
	public static String getAllLocalSiteAddress() {
		return localSiteAddress;
	}

	/**
	 * 获取本机的所有IP地址列表 ，包括公网地址和私有地址，如果仅仅想获取私有地址，请使用
	 * {@link #getAllLocalSiteAddress()}<br/>
	 * 如果获取不到地址则返回回环地址"127.0.0.1"</br> 忽略IPv6的所有地址
	 *
	 * @return 本机所有IP地址，例如"10.11.105.106-201.106.0.20"
	 * @see {@link #getAllLocalSiteAddress()}
	 */
	public static String getAllLocalAddress() {
		return localAddress;
	}
}
