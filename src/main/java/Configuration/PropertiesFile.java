package Configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

    protected Properties properties;
    protected FileInputStream fis;
    protected FileOutputStream fo;

    public String readConfigProperties(String key) throws IOException {
        String value = null;
        fis = new FileInputStream(System.getProperty("user.dir") + "/src/Config.Properties");
        properties = new Properties();
        properties.load(fis);
        if (value == null) {
            value = properties.getProperty(key);
        } else {
            System.out.println("Key not found in the properties file.");
        }
        fis.close();
        return value;
    }

    public void setConfigProperties(String key, String value) throws IOException {
        fo = new FileOutputStream(System.getProperty("user.dir") + "/src/Config.Properties");
        properties = new Properties();
        properties.setProperty(key, value);
        properties.store(fo, value);
        System.out.println("Value written in file are :" + key + value);
        fo.close();
    }

}
