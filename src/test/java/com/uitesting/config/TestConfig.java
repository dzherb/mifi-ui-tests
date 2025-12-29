package com.uitesting.config;

public final class TestConfig {

    private TestConfig() {
    }

    public static String webBaseUrl() {
        return System.getProperty("webBaseUrl", "https://en.wikipedia.org");
    }

    public static String browser() {
        return System.getProperty("browser", "chrome");
    }
}
