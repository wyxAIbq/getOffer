package com.library.util;

/**
 * Created by 王彦鑫 on 2018/10/22
 */

import com.library.entity.BookCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * Cookie 工具类
 *
 */
public final class CookieUtils {

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }


    /**
     * 得到Cookie的值,是否编码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie aCookieList : cookieList) {
                if (aCookieList.getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(aCookieList.getValue(), "UTF-8") + "_";
                    } else {
                        retValue = aCookieList.getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 得到Cookie的值,编码为String
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * 删除Cookie带cookie域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                          String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                          String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到cookie的域名
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }

    /**
     * 将图书存入cookie
     * @param request
     * @param response
     * @param cookieName
     * @param isDecoder
     * @param bookCart
     */
    public static void addBookToCookie(HttpServletRequest request, HttpServletResponse response,
                                       String cookieName, boolean isDecoder, BookCart bookCart){
        String cookieValue = getCookieValue(request,cookieName,isDecoder);//旧的cookie
        if(cookieValue == null){
            setCookie(request,response,cookieName,StringUtiles.transMapToStr(bookCart.getMap()),60*60*24*7,true);
        }else {
            String str = StringUtiles.transMapToStr(bookCart.getMap()); //新的cookie

            if("".equals(str)){
                setCookie(request,response,cookieName, str,
                        60*60*24*7,true);
            }else{
                //如果旧的长度小于新的，说明是添加；如果大于则是删除；如果等于是更新
                setCookie(request,response,cookieName, compareStrCookie(cookieValue,str),
                        60*60*24*7,true);

            }
        }
    }

    /**
     * 1.添加：将旧的图书cookie中与新的重复了的去掉
     * 2.删除：将新的图书cookie中重复的标记出来，设值为空；把没重复的图书去掉（即删除）
     * @param oldStrCookie
     * @param newStrCookie
     * @return
     */
    public static String compareStrCookie(String oldStrCookie,String newStrCookie) {
        String[] oldStrCookies, newStrCookies;
        if (newStrCookie != null) {
            newStrCookies = newStrCookie.split("_");
            oldStrCookies = oldStrCookie.split("_");
            for (int i = 0; i < newStrCookies.length; i++) {
                for (int j = 0; j < oldStrCookies.length; j++) {
                    if (newStrCookies[i].substring(0,newStrCookies[i].indexOf("+")).equals(oldStrCookies[j].substring(0,oldStrCookies[j].indexOf("+")))) {
                        oldStrCookies[j] = "0+";
                    }
                }
            }
            if(oldStrCookie.length() >= newStrCookie.length()){
                StringBuffer sb1 = new StringBuffer();
                for(int i = 0; i < oldStrCookies.length;i++){
                    if("0+".equals(oldStrCookies[i])){
                        oldStrCookies[i] = "";
                        sb1.append(oldStrCookies[i]);
                    }
                }
                StringBuffer sb2 = new StringBuffer();
                for(int i = 0; i < newStrCookies.length;i++){
                    sb2.append(newStrCookies[i]).append("_");
                }
                return sb1.toString()+sb2.toString();
            }else {
                StringBuffer sb1 = new StringBuffer();
                for(int i = 0; i < oldStrCookies.length;i++){
                    if(!("0+".equals(oldStrCookies[i]))){
                        sb1.append(oldStrCookies[i]).append("_");
                    }
                }
                StringBuffer sb2 = new StringBuffer();
                for(int i = 0; i < newStrCookies.length;i++){
                    sb2.append(newStrCookies[i]).append("_");
                }
                return sb1.toString()+sb2.toString();
            }
        }
        return oldStrCookie;
    }

}

