package com.chenbuer.aop;

/**
 * 是否匹配这个的class
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
