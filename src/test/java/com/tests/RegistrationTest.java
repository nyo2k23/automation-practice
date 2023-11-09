package com.tests;

import com.cta.models.User;
import com.cta.pages.HomePage;
import com.cta.pages.RegistrationPage;
import com.cta.pages.SignInPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class RegistrationTest extends BaseTest{
    private HomePage homePage;
    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private ObjectMapper objectMapper;

    @BeforeTest
    public void setLoginPage(){
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        this.registrationPage = new RegistrationPage(driver);
        objectMapper = new ObjectMapper();
        homePage.goTo("http://www.automationpractice.pl/index.php");
        signInPage.goTo();
    }

    @Test
    public void invalidRegistrationTest1() throws IOException {
        File file = new File("src/test/java/com/testdata/users/invalid-user-pwd-len.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterEmail(user.email());
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(registrationPage.registrationFormIsPresent(), true);
    }
}
