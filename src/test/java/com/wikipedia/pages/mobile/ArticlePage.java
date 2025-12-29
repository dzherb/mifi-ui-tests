package com.wikipedia.pages.mobile;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ArticlePage {

    private static final By ARTICLE_TITLE = AppiumBy.id("org.wikipedia:id/view_page_title_text");
    private static final By ARTICLE_TITLE_ALT = AppiumBy.id("org.wikipedia:id/page_title_text");
    private static final By ARTICLE_TOOLBAR_TITLE = AppiumBy.id("org.wikipedia:id/page_toolbar_title");
    private static final By ARTICLE_TITLE_HEADER = AppiumBy.id("org.wikipedia:id/page_header_title");
    private static final By ARTICLE_TITLE_HEADER_ALT = AppiumBy.id("org.wikipedia:id/view_page_header_title");
    private static final By ARTICLE_PROGRESS = AppiumBy.id("org.wikipedia:id/page_load_progress");

    private final WebDriverWait wait;

    public ArticlePage(WebDriverWait wait) {
        this.wait = wait;
    }

    public String waitForTitleContaining(String expectedText) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ARTICLE_PROGRESS));
        List<By> candidates = new ArrayList<>(List.of(
                ARTICLE_TITLE,
                ARTICLE_TITLE_ALT,
                ARTICLE_TOOLBAR_TITLE,
                ARTICLE_TITLE_HEADER,
                ARTICLE_TITLE_HEADER_ALT,
                AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*title\")")
        ));

        if (expectedText != null && !expectedText.isBlank()) {
            candidates.add(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + expectedText + "\")"));
            candidates.add(AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"" + expectedText + "\")"));
        }

        WebElement title = wait.until(driver -> firstDisplayedElement(driver, candidates));
        return title.getText();
    }

    private WebElement firstDisplayedElement(org.openqa.selenium.WebDriver driver, List<By> candidates) {
        for (By candidate : candidates) {
            List<WebElement> elements = driver.findElements(candidate);
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    return element;
                }
            }
        }
        return null;
    }
}
