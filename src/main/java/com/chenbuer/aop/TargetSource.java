package com.chenbuer.aop;

import com.chenbuer.tinyioc.HelloWorldService;

/**
 * 被代理的对象
 * Created by buer on 2018/1/21.
 */
public class TargetSource {
    /**
     * 被代理对象的类
     */
    private Class targetClass;

    /**
     * 被代理的对象实现的接口
     */
    private Object target;

    public TargetSource(Class targetClass, Object target) {
        this.targetClass = targetClass;
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}

