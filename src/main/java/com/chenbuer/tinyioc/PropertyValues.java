package com.chenbuer.tinyioc;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public void add(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);

    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

}
