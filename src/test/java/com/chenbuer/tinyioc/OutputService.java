package com.chenbuer.tinyioc;

/**
 * Created by buer on 2018/1/20.
 */
public class OutputService {
    HelloWorldServiceImpl helloWorldServiceImpl;

    public HelloWorldServiceImpl getHelloWorldServiceImpl() {
        return helloWorldServiceImpl;
    }

    public void setHelloWorldServiceImpl(HelloWorldServiceImpl helloWorldServiceImpl) {
        this.helloWorldServiceImpl = helloWorldServiceImpl;
    }

    @Override
    public String toString() {
        return "OutputService{" +
                "helloWorldServiceImpl=" + helloWorldServiceImpl +
                '}';
    }
}
