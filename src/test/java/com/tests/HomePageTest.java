package com.tests;

import com.cta.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @BeforeTest
    public void setHomePage(){
        this.homePage = new HomePage(driver);
        homePage.goTo("http://www.automationpractice.pl/index.php");
    }

    @Test
    public void HomePageTest(){
        Assert.assertEquals(homePage.uniqueHomePageElementsAreDisplayed(), true);
    }

}
