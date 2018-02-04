package com.chenbuer.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * czy：借鉴AspecJ的语法实现了一个Pointcut描述方法
 * AspectJ是实现AOP的众多方法中的一种。但是完全使用它很复杂，相当于重新学习一个语言。所以只利用了AspectJ描述Pointcut的语言。
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
    private PointcutParser pointcutParser;

    private String expression;

    private PointcutExpression pointcutExpression;

    private static final Set<PointcutPrimitive> DEFAULT_SUPPORT_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORT_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public AspectJExpressionPointcut() {
        this(DEFAULT_SUPPORT_PRIMITIVES);
    }

    public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
        pointcutParser = PointcutParser.
                getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
    }

    protected void checkReadyToMatch() {
        if (null == pointcutExpression) {
            pointcutExpression = buildPointcutExpression();
        }
    }

    private PointcutExpression buildPointcutExpression() {
        return pointcutParser.parsePointcutExpression(expression);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }


    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public boolean matches(Class targetClass) {
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();
        ShadowMatch shadowMatch = pointcutExpression.matchesAdviceExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        }

        return false;
    }
}
