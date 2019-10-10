package com.example.springboot.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


public class IpUtil {


    /**
     * 获取Servlet请求的客户端远程Ip地址
     * @param request 当前请求
     * @return 客户端远程ip地址
     */
    public static String getRemoteClientIp(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            return getRemoteClientIp((HttpServletRequest) request);
        } else {
            return request.getRemoteAddr() ;
        }
    }

    /**
     * 获取Http请求的客户端远程Ip地址
     * @param request 当前请求
     * @return 客户端远程ip地址
     */
    public static String getRemoteClientIp(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        String unknown = "unknown";
        if(StringUtils.isNotEmpty(XFor) && !unknown.equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !unknown.equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || unknown.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || unknown.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || unknown.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || unknown.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || unknown.equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}