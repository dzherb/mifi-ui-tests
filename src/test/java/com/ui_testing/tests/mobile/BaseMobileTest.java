package com.ui_testing.tests.mobile;

import com.ui_testing.driver.MobileDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseMobileTest {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        try {
            driver = MobileDriverFactory.createAndroidDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        } catch (Exception e) {
            driver = null;
            wait = null;
            throw new SkipException("Appium session not started: " + e.getMessage(), e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
