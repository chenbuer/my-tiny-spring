package com.chenbuer.tinyioc.context;

import com.chenbuer.tinyioc.HelloWorldServiceImpl;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void test() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldServiceImpl hw = (HelloWorldServiceImpl) applicationContext.getBean("hw");
        System.out.println(hw);
    }
}
