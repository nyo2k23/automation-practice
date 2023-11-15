package com.cta.tests;

import com.cta.models.User;
import com.cta.pages.AccountPage;
import com.cta.pages.HomePage;
import com.cta.pages.RegistrationPage;
import com.cta.pages.SignInPage;
import com.cta.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SignInTest extends BaseTest{
    private HomePage homePage;
    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private AccountPage accountPage;
    private ObjectMapper objectMapper;

    @BeforeTest
    public void setLoginPage(){
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        this.registrationPage = new RegistrationPage(driver);
        this.accountPage = new AccountPage(driver);
        this.objectMapper = new ObjectMapper();
        homePage.goTo("http://www.automationpractice.pl/index.php");
    }

    @BeforeMethod
    public void goToSignIn(){
        signInPage.goTo();
    }

    @Test(priority = 1)
    public void signInPageTest(){
        Assert.assertEquals(signInPage.authenticationContainerIsPresent(), true);
        Assert.assertEquals(signInPage.registerAndLoginFormsArePresent(), true);
    }

    @Test(priority = 2)
    public void invalidEmailSubmissionForRegistrationTest(){
        signInPage.enterEmailToRegister("notvalidemailhere");
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(signInPage.isInvalidInput(), true);
        Assert.assertEquals(signInPage.invalidRegistrationInputMessage(), "Invalid email address.");
    }

    @Test(priority = 3)
    public void successfulEmailSubmissionForRegistrationTest(){
        signInPage.enterEmailToRegister("email@em.com");
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(registrationPage.registrationFormIsPresent(), true);
    }

    @Test(priority = 4)
    public void invalidEmailLogin() {
        signInPage.enterLoginDetails("ud", "PSNSJ");
        signInPage.submitLogin();
        Assert.assertEquals(signInPage.invalidLoginInputMsg(), Constants.LOGIN_EMAIL_INVALID_ERROR_MSG);
    }

    @Test(priority = 5)
    public void invalidPasswordLogin() {
        signInPage.enterLoginDetails("df@jsnjkds.com", "9U");
        signInPage.submitLogin();
        Assert.assertEquals(signInPage.invalidLoginInputMsg(), Constants.LOGIN_PASSWORD_INVALID_ERROR_MSG);
    }

    @Test(priority = 6)
    public void invalidUserLogin() {
        signInPage.enterLoginDetails("email@jks.com", "psnsj");
        signInPage.submitLogin();
        Assert.assertEquals(signInPage.invalidLoginInputMsg(), Constants.AUTHENTICATION_FAIL_MSG);
    }

    @Test(priority = 7)
    public void successfulLogin() throws IOException {
        File file = new File("src/test/java/com/cta/testdata/users/valid-user-login.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterLoginDetails(user.email(), user.password());
        signInPage.submitLogin();
        Assert.assertEquals(
                accountPage.getNameOfRegisteredUser(), user.fName() + " " + user.lName()
        );
    }
}

