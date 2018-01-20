package com.chenbuer.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource {

    private URL url;

    public UrlResource(URL url){
        this.url = url;
    }
    @Override
    public InputStream getInputStream() {
        try {
            URLConnection urlConnection = this.url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
