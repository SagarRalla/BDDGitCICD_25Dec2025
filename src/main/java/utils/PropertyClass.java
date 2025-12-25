package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyClass {
    static Properties properties = null;
    static FileInputStream fis = null;

    public static Properties getProperty(String fileName){
        try {
            fis = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fis);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
