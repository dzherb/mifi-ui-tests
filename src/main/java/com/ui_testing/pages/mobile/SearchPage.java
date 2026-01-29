package com.ui_testing.pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchPage {

    private static final By RESULT_TITLE = AppiumBy.id(
            "org.wikipedia:id/page_list_item_title"
    );
    private static final By RESULT_DESCRIPTION = AppiumBy.id(
            "org.wikipedia:id/page_list_item_description"
    );
    private static final By CLEAR_SEARCH = AppiumBy.id(
            "org.wikipedia:id/search_close_btn"
    );

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public SearchPage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public List<WebElement> resultTitles() {
        return wait.until(driver -> {
            List<WebElement> elements = driver.findElements(RESULT_TITLE);
            return elements.isEmpty() ? null : elements;
        });
    }

    public int resultTitlesCount() {
        return driver.findElements(RESULT_TITLE).size();
    }

    public List<WebElement> resultDescriptions() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(RESULT_DESCRIPTION));
        return driver.findElements(RESULT_DESCRIPTION);
    }

    public List<String> results() {
        return resultTitles().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String openArticle(String text) {
        String lower = text.toLowerCase();

        final int attempts = 5;

        for (int a = 0; a < attempts; a++) {
            List<WebElement> titles = resultTitles();
            Optional<WebElement> match = titles.stream()
                    .filter(e -> {
                        String t = e.getText();
                        return t != null && t.toLowerCase().contains(lower);
                    })
                    .findFirst();

            if (match.isPresent()) {
                String titleText = match.get().getText();
                match.get().click();

                return titleText;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        List<WebElement> titles = resultTitles();
        WebElement first = titles.getFirst();
        String titleText = first.getText();

        first.click();

        return titleText;
    }

    public void waitResultsDisappear() {
        wait.until(driver -> driver.findElements(RESULT_TITLE).isEmpty());
    }

    public void clearSearch() {
        wait.until(
                ExpectedConditions.elementToBeClickable(CLEAR_SEARCH)
        ).click();
    }
}
