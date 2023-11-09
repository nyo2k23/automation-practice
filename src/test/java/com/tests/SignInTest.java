package com.tests;

import com.cta.pages.HomePage;
import com.cta.pages.SignInPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest{
    private HomePage homePage;
    private SignInPage signInPage;

    @BeforeTest
    public void setLoginPage(){
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        ObjectMapper objectMapper = new ObjectMapper();
        homePage.goTo("http://www.automationpractice.pl/index.php");
    }

    @Test
    public void signInPageTest(){
        signInPage.goTo();
        Assert.assertEquals(signInPage.authenticationContainerIsPresent(), true);
        Assert.assertEquals(signInPage.registerAndLoginFormsArePresent(), true);
    }

    @Test
    public void invalidRegisterTest(){
        signInPage.goTo();
        signInPage.enterEmail("notvalidemailhere");
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(signInPage.isInvalidInput(), true);
        Assert.assertEquals(signInPage.invalidInputMessage(), "Invalid email address.");
    }

    @Test
    public void successfulEmailSubmissionForRegistrationTest(){
        signInPage.goTo();
        signInPage.enterEmail("email@em.com");
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(signInPage.isValidInput(), true);
    }

}

