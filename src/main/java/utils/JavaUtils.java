package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class JavaUtils {

    public static Properties readPropertiesFile(String fileName){
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                fis.close();
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
        return prop;
    }

    public static String generateRandomNumber(){
        Integer randomNumber = (int) Math.floor((Math.random() * 1000000000) + 1);
        return  randomNumber.toString();
    }
}
