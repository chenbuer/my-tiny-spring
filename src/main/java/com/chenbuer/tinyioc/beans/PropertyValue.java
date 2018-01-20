package com.chenbuer.tinyioc.beans;


/**
 * propertyValue设置
 */
public class PropertyValue {
    private String name;

    // todo:相当注意：这里的value设置为Object，是为了适应value为不同的类型，同样的这个类型可以是其他的bean！
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
