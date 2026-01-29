package com.ui_testing.tests.web;

import com.ui_testing.pages.web.IMDbMainPage;
import com.ui_testing.pages.web.IMDbMoviePage;
import com.ui_testing.pages.web.IMDbSearchResultsPage;
import com.ui_testing.pages.web.IMDbTop250Page;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IMDbTests extends BaseWebTest {

    @Test(groups = "web")
    void mainPageShouldLoad() {
        IMDbMainPage mainPage = new IMDbMainPage(driver, wait);
        Assert.assertTrue(
                mainPage.isMainPageLoaded(),
                "Main page should be loaded"
        );
    }

    @Test(groups = "web")
    void searchShouldReturnResults() {
        IMDbMainPage mainPage = new IMDbMainPage(driver, wait);
        IMDbSearchResultsPage results = mainPage.search("Inception");

        Assert.assertTrue(
                results.hasResults(),
                "Should have get search results"
        );
    }

    @Test(groups = "web")
    void openingMovieFromSearchShouldOpenMoviePage() {
        IMDbMainPage mainPage = new IMDbMainPage(driver, wait);
        IMDbMoviePage moviePage = mainPage
                .search("Interstellar")
                .openFirstTitle();

        Assert.assertEquals(
                moviePage.getTitle(),
                "Interstellar",
                "Should have found correct movie title"
        );
        Assert.assertTrue(
                moviePage.isRatingVisible(),
                "Should have found movie rating on the page"
        );
    }

    @Test(groups = "web")
    void top250ShouldBeAccessibleFromMenu() {
        IMDbMainPage mainPage = new IMDbMainPage(driver, wait);
        IMDbTop250Page top250 = mainPage.openTop250();

        Assert.assertTrue(
                top250.isTop250TableVisible(),
                "Should have found top 250 chart on the page"
        );
        Assert.assertNotNull(
                top250.firstMovieTitle(),
                "Top movie title should not be empty"
        );
    }
}