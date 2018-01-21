package com.chenbuer.tinyioc.context;

import com.chenbuer.tinyioc.HelloWorldService;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void test() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService hw = (HelloWorldService) applicationContext.getBean("hw");
        System.out.println(hw);
    }
}
