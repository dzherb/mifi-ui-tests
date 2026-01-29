package com.ui_testing.config;

public final class Config {

    private Config() {
    }

    public static String browser() {
        return System.getProperty("browser", "chrome");
    }

    public static String appiumServerUrl() {
        return System.getProperty("appiumServerUrl", "http://127.0.0.1:4723");
    }

    public static String deviceName() {
        return System.getProperty("deviceName", "Android Emulator");
    }

    public static String platformVersion() {
        return System.getProperty("platformVersion", "");
    }
}
