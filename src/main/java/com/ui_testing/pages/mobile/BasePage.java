package com.ui_testing.pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
}