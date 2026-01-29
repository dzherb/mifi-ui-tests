package com.ui_testing.pages.web;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDbMainPage extends BaseWebPage {

    private static final By IMDB_LOGO = By.id(
            "home_img_holder"
    );

    private static final By SEARCH_INPUT = By.id(
            "suggestion-search"
    );

    private static final By MENU_BUTTON = By.id(
            "imdbHeader-navDrawerOpen"
    );

    private static final By TOP_250_LINK = By.cssSelector(
            "a[aria-label='Go to Top 250 movies']"
    );

    public IMDbMainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isMainPageLoaded() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(IMDB_LOGO)
        ).isDisplayed();
    }

    public IMDbSearchResultsPage search(String query) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(SEARCH_INPUT)
        );

        input.clear();
        input.sendKeys(query);
        input.sendKeys(Keys.ENTER);

        return new IMDbSearchResultsPage(driver, wait);
    }

    public IMDbTop250Page openTop250() {
        wait.until(
                ExpectedConditions.elementToBeClickable(MENU_BUTTON)
        ).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(TOP_250_LINK)
        ).click();

        return new IMDbTop250Page(driver, wait);
    }
}
