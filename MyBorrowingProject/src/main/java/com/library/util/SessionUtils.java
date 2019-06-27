package com.library.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session工具类
 * Created by 王彦鑫 on 2018/10/23
 */
public class SessionUtils {

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * reqeust.getSession(false)获取session,如果session不存在，则返回null
     * @return
     */
    public static HttpSession getSession(){
        return getRequest().getSession(false);
    }

    /**
     * 获取Session中的Attribute
     * @param key
     * @return
     */
    public static Object getSessionAttribute(String key){
        return getRequest().getSession().getAttribute(key);
    }

    /**
     * 设置Session的Attribute
     * @param key
     * @param value
     */
    public static void setSessionAttribute(String key,Object value){
        getRequest().getSession().setAttribute(key,value);
    }

    /**
     * 删除Session中的Attribute
     * @param key
     */
    public static void removeSessionAttribute(String key){
        getRequest().getSession().removeAttribute(key);
    }
}
