package com.chenbuer.aop;

import com.chenbuer.tinyioc.HelloWorldService;
import com.chenbuer.tinyioc.HelloWorldServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by buer on 2018/2/4.
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() throws Exception {
        String expression ="execution(* com.chenbuer.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        Assert.assertTrue(matches);
//        System.out.println(matches);
    }

    @Test
    public void testMethodInterceptor() throws Exception{
        String expression ="execution(* com.chenbuer.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        MethodMatcher methodMatcher = aspectJExpressionPointcut.getMethodMatcher();
        Method sayHello = HelloWorldServiceImpl.class.getDeclaredMethod("sayHello");
        System.out.println(sayHello);
        boolean matches = methodMatcher.matches(HelloWorldServiceImpl.class.getDeclaredMethod("sayHello"), HelloWorldServiceImpl.class);
//        System.out.println(matches);
        Assert.assertTrue(matches);
    }

}
