package com.chenbuer.aop.jdkDynamicProxy;

/**
 * Created by buer on 2018/1/24.
 */
public class MySubject implements Subject {

    @Override
    public void sayHello() {
        System.out.println("sayHello");
    }
}
