package com.chenbuer.tinyioc;

/**
 * Created by buer on 2018/1/20.
 */
public class OutputService {
    HelloWorldService helloWorldService;

    public HelloWorldService getHelloWorldService() {
        return helloWorldService;
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @Override
    public String toString() {
        return "OutputService{" +
                "helloWorldService=" + helloWorldService +
                '}';
    }
}
