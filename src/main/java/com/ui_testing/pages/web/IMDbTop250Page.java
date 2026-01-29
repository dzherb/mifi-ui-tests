package com.ui_testing.pages.web;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDbTop250Page extends BaseWebPage {

    private static final By CHART = By.cssSelector(
            "div[data-testid='chart-layout-main-column']"
    );

    private static final By FIRST_MOVIE_TITLE = By.cssSelector(
            "div[data-testid='chart-layout-main-column'] " +
                    "li h3"
    );

    public IMDbTop250Page(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isTop250TableVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(CHART)
        ).isDisplayed();
    }

    public String firstMovieTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(FIRST_MOVIE_TITLE)
        ).getText();
    }
}