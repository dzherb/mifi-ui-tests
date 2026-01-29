package com.ui_testing.tests.web;

import com.ui_testing.pages.web.IMDbMainPage;
import com.ui_testing.pages.web.IMDbMoviePage;
import com.ui_testing.pages.web.IMDbSearchResultsPage;
import com.ui_testing.pages.web.IMDbTop250Page;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IMDbTest extends BaseWebTest {

    @Test(groups = "web")
    void mainPageShouldLoad() {
        IMDbMainPage mainPage = new IMDbMainPage(driver, wait);
        Assert.assertTrue(mainPage.isMainPageLoaded());
    }

    @Test(groups = "web")
    void searchShouldReturnResults() {
        IMDbMainPage mainPage = new IMDbMainPage(driver, wait);
        IMDbSearchResultsPage results = mainPage.search("Inception");

        Assert.assertTrue(results.hasResults());
    }

    @Test(groups = "web")
    void openingMovieFromSearchShouldOpenMoviePage() {
        IMDbMainPage mainPage = new IMDbMainPage(driver, wait);
        IMDbMoviePage moviePage = mainPage
                .search("Interstellar")
                .openFirstTitle();

        Assert.assertEquals(moviePage.getTitle(), "Interstellar");
        Assert.assertTrue(moviePage.isRatingVisible());
    }

    @Test(groups = "web")
    void top250ShouldBeAccessibleFromMenu() {
        IMDbMainPage mainPage = new IMDbMainPage(driver, wait);
        IMDbTop250Page top250 = mainPage.openTop250();

        Assert.assertTrue(top250.isTop250TableVisible());
        Assert.assertNotNull(top250.firstMovieTitle());
    }
}