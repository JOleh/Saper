package com.leush;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ImageFactory {

    private static final Map<String, ImageIcon> imageType = new HashMap<>();

    public ImageIcon getImageIcon(String imageName) {
        ImageIcon icon = imageType.get(imageName);
        if (Objects.isNull(icon)) {
            icon = uploadFromResources(imageName);
            imageType.put(imageName, icon);
        }

        return icon;
    }

    private ImageIcon uploadFromResources(String imageName) {
        ImageIcon imageIcon = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(imageName);
            if (Objects.isNull(inputStream)) {
                imageIcon = new ImageIcon();
                throw new RuntimeException("Image with name : " + imageName + " Not Found");
                // log.warn();
            } else {
                imageIcon = new ImageIcon(IOUtils.toByteArray(inputStream));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageIcon;
    }
}

