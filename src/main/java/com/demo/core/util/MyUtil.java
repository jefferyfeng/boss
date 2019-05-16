package com.demo.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * 开发工具类
 *
 * @author jeffery
 */
public class MyUtil {
    /**
     * 是否为空字符串
     *
     * @param input 校验字符串
     * @return true: 空 false: 非空
     */
    public static boolean isEmpty(String input) {
        return input == null || "".equals(input.trim());
    }

    /**
     * 是否非空字符串
     *
     * @param input 校验字符串
     * @return true: 非空 false: 空
     */
    public static boolean isNotEmpty(String input) {
        return input != null && !"".equals(input.trim());
    }

    /**
     * 是否为空集合
     *
     * @param collection 校验集合
     * @return true: 空 false: 非空
     */
    public static boolean isEmpty(Collection collection) {
        return ((collection == null) || collection.isEmpty());
    }

    /**
     * 判断是否非空集合
     *
     * @param collection 校验集合
     * @return true: 非空 false: 空
     */
    public static boolean isNotEmpty(Collection collection) {
        return ((collection != null) && !(collection.isEmpty()));
    }

    /**
     * 是否为空数组
     *
     * @param array 校验数组
     * @return true: 空 false: 非空
     */
    public static boolean isEmpty(Object[] array) {
        return ((array == null) || array.length==0);
    }

    /**
     * 是否非空数组
     *
     * @param arrays 校验数组
     * @return true: 非空 false: 空
     */
    public static boolean isNotEmpty(Object[] arrays) {
        return ((arrays != null) && arrays.length>0);
    }

    /**
     * 获取web环境下 HttpServletRequest
     *
     * @return request
     */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取web环境下 HttpSession
     *
     * @return request
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }
}
