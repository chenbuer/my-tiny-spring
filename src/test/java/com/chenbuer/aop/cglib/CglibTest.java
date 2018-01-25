package com.chenbuer.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * Created by buer on 2018/1/25.
 */
public class CglibTest {

    @Test
    public void test(){
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(CglibSubject.class);
        enhancer.setCallback(new MyMethodInterceptor());
        CglibSubject proxy = (CglibSubject) enhancer.create();
        proxy.sayHello();
    }
}
