package com.ui_testing.pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LanguagePage extends BasePage {
    private static final By RUSSIAN_OPTION = AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().text(\"Русский\"))"
    );

    public LanguagePage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LanguagePage selectRussianLanguage() {
        WebElement russianLang = wait.until(
                ExpectedConditions.elementToBeClickable(RUSSIAN_OPTION)
        );

        russianLang.click();

        return this;
    }

    public HomePage returnToHome() {
        driver.navigate().back();
        driver.navigate().back();

        return new HomePage(driver, wait);
    }
}