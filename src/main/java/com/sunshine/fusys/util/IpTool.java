package com.sunshine.fusys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2014-2-10 <br>
 * 功能描述：IP操作工具类 <br>
 */
public class IpTool {
	/**
	 * long转为 ip
	 * 
	 * @param ipaddr
	 * @return
	 */
	public static String getIP(Long ipaddr) {
		long y = ipaddr % 256;
		long m = (ipaddr - y) / (256 * 256 * 256);
		long n = (ipaddr - 256 * 256 * 256 * m - y) / (256 * 256);
		long x = (ipaddr - 256 * 256 * 256 * m - 256 * 256 * n - y) / 256;
		return m + "." + n + "." + x + "." + y;
	}

	/**
	 * IP转long
	 * 
	 * @param ipaddr
	 * @return
	 */
	public static Long setIP(String ipaddr) {
		if(!isIpv4(ipaddr)){
			return 0L;
		}
		String ip[] = ipaddr.split("\\.");
		Long ipLong = 256 * 256 * 256 * Long.parseLong(ip[0]) + 256 * 256
				* Long.parseLong(ip[1]) + 256 * Long.parseLong(ip[2])
				+ Long.parseLong(ip[3]);
		return ipLong;
	}
	
	/**
     * 判断是否为合法IP
     * 网上摘的，自己验证下，怎么用，我就不用说了吧？
     * @return true or false
     */
    public static boolean isIpv4(String ipAddress) {
    	if(ipAddress == null){
    		return false;
    	}
        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
	
	/**
	 * 获取客户端ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientAddress(HttpServletRequest request) {
		/*
		 * request.getHeader("User-Agent"); //就是取得客户端的系统版本
		 * request.getRemoteAddr(); //取得客户端的IP request.getRemoteHost();
		 * //取得客户端的主机名 request.getRemotePort(); //取得客户端的端口
		 * request.getRemoteUser(); //取得客户端的用户 request.getLocalAddr(); //取得本地IP
		 * request.getLocalPort(); //取得本地端口
		 */
		String ip = request.getHeader("x-forwarded-for");
		String localIP = "127.0.0.1";
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 查询IP是否在指定ip范围之内
	 *  
	 * @param sourceIp
	 * @param ipArray
	 * @return boolean
	 * @author fantasy 
	 * @date 2013-12-9
	 */
	public static boolean isInIpArray(String sourceIp , String[] ipArray){
		Long ip = setIP(sourceIp);
		if(Long.parseLong(ipArray[0]) <= ip && Long.parseLong(ipArray[1]) >= ip){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(IpTool.setIP("127.0.0.1"));
		System.out.println(IpTool.getIP(3232266495L));
	}
}