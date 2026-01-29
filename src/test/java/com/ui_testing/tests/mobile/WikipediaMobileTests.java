package com.ui_testing.tests.mobile;

import com.ui_testing.pages.mobile.ArticlePage;
import com.ui_testing.pages.mobile.HomePage;
import com.ui_testing.pages.mobile.OnboardingPage;
import com.ui_testing.pages.mobile.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WikipediaMobileTests extends BaseMobileTest {

    @BeforeMethod(alwaysRun = true)
    public void skipOnboarding() {
        OnboardingPage onboardingPage = new OnboardingPage(driver, wait);
        onboardingPage.skipIfPresent();
    }

    @Test(groups = "mobile")
    public void testLanguageSwitchToRussian() {
        HomePage homePage = new HomePage(driver, wait);

        homePage.openLanguageSetting()
                .selectRussianLanguage()
                .returnToHome();

        String expectedTitle = "Wikipedia";

        String actualTitle = homePage.searchFor(expectedTitle)
                .openArticle("Wikipedia");

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test(groups = "mobile")
    public void searchShowsTitlesAndDescriptions() {
        HomePage homePage = new HomePage(driver, wait);
        SearchPage searchPage = homePage.searchFor("Selenium");
        List<String> searchResults = searchPage.results();

        Assert.assertFalse(
                searchResults.isEmpty(),
                "Search results should not be empty"
        );
        Assert.assertTrue(
                searchResults.stream().anyMatch(t -> t.toLowerCase().contains("selenium")),
                "At least one title should contain the query");
        Assert.assertFalse(
                searchPage.resultDescriptions().isEmpty(),
                "Descriptions should be present for results"
        );
    }

    @Test(groups = "mobile")
    public void openArticleFromSearch() {
        HomePage homePage = new HomePage(driver, wait);
        SearchPage searchPage = homePage.searchFor("Software testing");
        String selectedTitle = searchPage.openArticle("Software testing");

        ArticlePage articlePage = new ArticlePage(wait);

        String openedTitle = articlePage.waitForTitleContaining(selectedTitle);

        Assert.assertTrue(
                openedTitle.toLowerCase().contains(selectedTitle.toLowerCase())
                        || selectedTitle.toLowerCase().contains(openedTitle.toLowerCase()),
                "Opened article should match selected search result");
    }

    @Test(groups = "mobile")
    public void clearSearchResetsResults() {
        HomePage homePage = new HomePage(driver, wait);
        SearchPage searchPage = homePage.searchFor("Selenium");

        Assert.assertFalse(
                searchPage.resultTitles().isEmpty(),
                "Results should appear after typing query"
        );

        searchPage.clearSearch();
        searchPage.waitResultsDisappear();

        Assert.assertEquals(
                searchPage.resultTitlesCount(), 0,
                "Results should disappear after clearing search"
        );
    }
}
