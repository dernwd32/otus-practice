package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Settings {
    public Map<String,String> getSettings() {

        Properties properties = new Properties();
        Map<String,String> prop = new HashMap<>();

        try {
            //String rootDir = System.getProperty("user.dir");
            String rootDir = "C:/Users/dernw/IdeaProjects/otus1";
            properties.load(new FileInputStream((rootDir
                    + "/src/main/resources/SQLSettings.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        properties.stringPropertyNames().forEach(key ->  prop.put(key, properties.getProperty(key)) );

        return prop;
    }
}
