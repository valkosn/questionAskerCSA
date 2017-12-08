package com.asker.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Valko Serhii on 06-Sep-16.
 */
public class Settings implements ISettings {

    private Properties properties = new Properties();

    public Settings(String propFile) {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource(propFile).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
