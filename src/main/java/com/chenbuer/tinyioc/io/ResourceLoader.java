package com.chenbuer.tinyioc.io;

import java.net.URL;

public class ResourceLoader {

    public Resource getResource(String path){
        URL url = getClass().getClassLoader().getResource(path);
        Resource resource = new UrlResource(url);
        return resource;
    }
}
