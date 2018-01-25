package com.chenbuer.aop;

/**
 * 被代理的对象
 * Created by buer on 2018/1/21.
 */
public class TargetSource {
    private Class targetClass;

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

