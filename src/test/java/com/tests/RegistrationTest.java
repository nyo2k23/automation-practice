package com.tests;

import com.cta.pages.HomePage;
import com.cta.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{
    private HomePage homePage;
    private SignInPage signInPage;

    @BeforeTest
    public void setLoginPage(){
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        homePage.goTo("http://www.automationpractice.pl/index.php");
    }

    @Test
    public void signInPageTest(){
        signInPage.goTo();
        Assert.assertEquals(signInPage.authenticationContainerIsPresent(), true);
        Assert.assertEquals(signInPage.registerAndLoginFormsArePresent(), true);
    }

    @Test
    public void registerTest(){
        signInPage.enterEmail("notvalidemailhere");
        signInPage.submitEmailForRegistration();
        Assert.assertEquals(signInPage.isValidInput(), false);
        Assert.assertEquals(signInPage.invalidInputMessage(), "Invalid email address.");
    }

}

