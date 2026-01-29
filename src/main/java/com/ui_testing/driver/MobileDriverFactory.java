package com.ui_testing.driver;

import com.ui_testing.config.Config;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriverException;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class MobileDriverFactory {

    private MobileDriverFactory() {
    }

    public static AndroidDriver createAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(Config.deviceName());
        options.setUdid(Config.deviceName());

        if (!Config.platformVersion().isEmpty()) {
            options.setPlatformVersion(Config.platformVersion());
        }

        options.setAppPackage("org.wikipedia");
        options.setAppActivity("org.wikipedia.main.MainActivity");
        options.setNoReset(true);
        options.setNewCommandTimeout(Duration.ofSeconds(60));
        options.setAutoGrantPermissions(true);
        options.setCapability("forceAppLaunch", true);

        String baseUrl = Config.appiumServerUrl();

        try {
            return new AndroidDriver(new URL(baseUrl), options);
        } catch (WebDriverException first) {
            String fallbackUrl = toggleHubPath(baseUrl);

            if (fallbackUrl.equals(baseUrl)) {
                throw first;
            }

            try {
                return new AndroidDriver(new URL(fallbackUrl), options);
            } catch (MalformedURLException ignored) {
                throw first;
            }
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid Appium server URL: " + baseUrl, e);
        }
    }

    private static String toggleHubPath(String url) {
        if (url.endsWith("/wd/hub")) {
            return url.replace("/wd/hub", "");
        }
        if (url.endsWith("/")) {
            return url + "wd/hub";
        }

        return url + "/wd/hub";
    }
}
