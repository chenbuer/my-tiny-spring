package com.chenbuer.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理相关的元数据
 *
 * Created by buer on 2018/1/21.
 */
public class AdvisedSupport {

    // 被代理的对象（包括具体的对象和该对象所属的类）
    private TargetSource targetSource;

    // 此处是拦截器，设置具体的织入逻辑
    private MethodInterceptor methodInterceptor;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}

