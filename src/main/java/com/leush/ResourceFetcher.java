package com.leush;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class ResourceFetcher {

    public String readFromFile(String filePath){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        assert inputStream != null;
        try {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file : " + filePath);
        }
    }

}
