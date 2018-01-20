package com.chenbuer.tinyioc;

/**
 * Created by buer on 2018/1/20.
 */
public class BeanReference {
    private String name;
    private Object bean;

    // todo: 为什么构造函数只有name。bean什么时候设置的？
    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
