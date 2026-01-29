package com.ui_testing.pages.web;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDbMoviePage extends BaseWebPage {

    private static final By MOVIE_TITLE = By.cssSelector(
            "span.hero__primary-text"
    );
    private static final By MOVIE_RATING = By.cssSelector(
            "div[data-testid='hero-rating-bar__aggregate-rating__score']"
    );

    public IMDbMoviePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(MOVIE_TITLE)
        ).getText();
    }

    public boolean isRatingVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(MOVIE_RATING)
        ).isDisplayed();
    }
}
