package com.chenbuer.xml.dom4j;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class XMLParseTest {

    @Test
    public void test(){
        dom4jParseXML("students.xml  ");
    }

    public void dom4jParseXML(String path){
        try {
            // 1. 获取SAXReader对象
            org.dom4j.io.SAXReader reader = new SAXReader();
            // 获取resouce
            URL resource = getClass().getClassLoader().getResource(path);
            // 2. 利用SAXReader对象的read方法获取Docment对象
            org.dom4j.Document document = reader.read(resource);
            // 3. 获取document对象的根节点
            org.dom4j.Element rootElement = document.getRootElement();
            // 4. 通过根节点的elementIterator方法获取对应下面的所有子节点
            Iterator it = rootElement.elementIterator();
            // 5. 循环迭代器
            while (it.hasNext()){
                // 5.1 获取到每一个学生节点
                org.dom4j.Element ele = (org.dom4j.Element) it.next();

                System.out.println("======开始一个学生的解析======");
                //开始某一个学生的解析
                List<Attribute> attributes = ele.attributes();
                for (Attribute attribute : attributes) {
                    System.out.println(attribute.getName() + ":" + attribute.getValue());
                }
                //获取该学生的所有的子节点
                Iterator stdAtt = ele.elementIterator();
                while (stdAtt.hasNext()){
                    org.dom4j.Element oneAttr = (org.dom4j.Element) stdAtt.next();
                    System.out.println(oneAttr.getQName().getName() +":"+ oneAttr.getText());
                }
                System.out.println("======结束一个学生的解析======");

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}