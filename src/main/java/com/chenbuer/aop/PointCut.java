package com.chenbuer.aop;

public interface PointCut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
