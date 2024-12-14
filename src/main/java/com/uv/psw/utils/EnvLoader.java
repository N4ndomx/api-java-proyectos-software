package com.uv.psw.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    public static void loadDotenv() {
        try (FileInputStream fis = new FileInputStream(".env")) {
            Properties props = new Properties();
            props.load(fis);
            props.forEach((key, value) -> System.setProperty((String) key, (String) value));
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo .env", e);
        }
    }
}
