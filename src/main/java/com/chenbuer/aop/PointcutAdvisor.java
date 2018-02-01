package com.chenbuer.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
