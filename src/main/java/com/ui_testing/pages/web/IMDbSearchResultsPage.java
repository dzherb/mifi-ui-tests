package com.ui_testing.pages.web;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDbSearchResultsPage extends BaseWebPage {

    private static final By RESULTS_LIST = By.cssSelector(
            "ul.ipc-metadata-list"
    );
    private static final By FIRST_RESULT_LINK = By.cssSelector(
            "ul.ipc-metadata-list " +
                    "li.ipc-metadata-list-summary-item " +
                    "a.ipc-title-link-wrapper"
    );

    public IMDbSearchResultsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean hasResults() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(RESULTS_LIST)
        ).isDisplayed();
    }

    public IMDbMoviePage openFirstTitle() {
        wait.until(
                ExpectedConditions.elementToBeClickable(FIRST_RESULT_LINK)
        ).click();

        return new IMDbMoviePage(driver, wait);
    }
}