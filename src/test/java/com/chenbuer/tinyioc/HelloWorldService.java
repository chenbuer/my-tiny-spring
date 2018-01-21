package com.chenbuer.tinyioc;

/**
 * Created by buer on 2018/1/20.
 */
public class HelloWorldService {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "HelloWorldService{" +
                "text='" + text + '\'' +
                '}';
    }
}
