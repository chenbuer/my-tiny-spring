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
    /**
     * todo：aspectj提供的pointcut的parser！！！
     */
    private PointcutParser pointcutParser;

    /**
     * 这个就是我们手写的pointcut的正则表达式，用来匹配pointcut的，如：excution com.chenbuer.*.*(..)
     */
    private String expression;

    /**
     * 利用pointParser解析expression之后就得到它
     * 把它看做在asepctJ下的具体的pointcut对象就好了
     */
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

    /**
     * todo：czy：利用aspectj的parser来解析Pointcut的正则表达式！！！！
     * 这里的构造函数主要是实例化字段ponitcutParser，他也是这里最重要的角色！
     *
     * @param supportedPrimitives
     */
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

    /**
     * 这个是ClassFilter的matches，用来匹配Class
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Class targetClass) {
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    /**
     * 这个是MethodMatcher的matches，用来匹配方法
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        }

        return false;
    }
}
