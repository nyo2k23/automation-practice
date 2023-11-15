package com.cta.tests;

import com.cta.pages.HomePage;
import com.cta.pages.SearchPage;
import com.cta.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {
    private HomePage homePage;
    private SearchPage searchPage;

    @BeforeTest
    public void setHomePage(){
        this.homePage = new HomePage(driver);
        this.searchPage = new SearchPage(driver);
        homePage.goTo("http://www.automationpractice.pl/index.php");
    }

    @BeforeMethod
    public void openHomePage(){
        homePage.returnHome();
    }

    @Test
    public void noResultSearchTest() {
        searchPage.search("jkak");
        Assert.assertEquals(searchPage.getSearchResultCounterMsg(), Constants.ERROR_SEARCH_RESULT_MSG);
    }

    @Test
    public void validSearchTest() {
        searchPage.search("dress");
        Assert.assertEquals(searchPage.getSearchResultCounterMsg(), Constants.DRESS_SEARCH_RESULT_COUNT);
    }
}
