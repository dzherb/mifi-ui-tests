package com.ui_testing.pages.mobile;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private final By searchContainer = By.id("org.wikipedia.alpha:id/search_container");
    private final By searchInput = By.id("org.wikipedia.alpha:id/search_src_text");
    private final String moreButtonLocator =
            "(//android.widget.LinearLayout[@resource-id=\"org.wikipedia.alpha:id/navigation_bar_item_content_container\"])[5]";
    private final String settingsButtonLocator = "org.wikipedia.alpha:id/main_drawer_settings_container";


    public HomePage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public SearchPage searchFor(String query) {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchContainer));
        searchField.click();

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );
        input.clear();
        input.sendKeys(query);

        return new SearchPage(driver, wait);
    }

    public LanguagePage openLanguageSetting() {
        WebElement moreButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath(moreButtonLocator)
        ));
        moreButton.click();

        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id(settingsButtonLocator)
        ));
        settingsButton.click();

        WebElement languageOption;

        try {
            languageOption = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Language\")")
            ));
        } catch (Exception e) {
            languageOption = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Язык\")")
            ));
        }

        languageOption.click();

        return new LanguagePage(driver, wait);
    }
}