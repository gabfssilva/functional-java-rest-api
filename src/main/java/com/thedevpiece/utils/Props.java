package com.thedevpiece.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class Props {
    private static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        InputStream input = null;

        try {
            input = Props.class.getClassLoader().getResourceAsStream("app.properties");
            PROPERTIES.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String prop(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String prop(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }
}
