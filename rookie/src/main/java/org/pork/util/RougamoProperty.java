package org.pork.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RougamoProperty {
    private static final Logger LOGGER = LogManager.getLogger(RougamoProperty.class);
    private static Properties properties = new Properties();

    static {
        String filePath = RougamoProperty.class.getResource("/").getPath() + "rougamo.properties";
        File serverProperty = new File(filePath);
        try {
            properties.load(new FileInputStream(serverProperty));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key, "");
    }
}
