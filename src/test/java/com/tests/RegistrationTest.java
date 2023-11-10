package com.tests;

import com.cta.models.User;
import com.cta.pages.HomePage;
import com.cta.pages.RegistrationPage;
import com.cta.pages.SignInPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.Constants;
import com.utils.RandomStringGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
    }

    @BeforeMethod
    public void goToSignInStage(){
        signInPage.goTo();
    }

    @Test
    public void invalidRegistrationTest1() throws IOException {
        File file = new File("src/test/java/com/testdata/users/invalid-user-no-fname.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterEmailToRegister(user.email());
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(registrationPage.registrationFormIsPresent(), true);
        registrationPage.enterUserDetails(
                user.gender(),
                user.fName(),
                user.lName(),
                user.password(),
                user.dateOfBirth(),
                user.monthOfBirth(),
                user.yearOfBirth()
        );
        registrationPage.submitUserDetails();
        Assert.assertEquals(registrationPage.getRegistrationErrorMsg(), Constants.F_NAME_REQUIRED_ERROR_MSG);
    }

    @Test
    public void invalidRegistrationTest2() throws IOException {
        File file = new File("src/test/java/com/testdata/users/invalid-user-fname-max-len.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterEmailToRegister(user.email());
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(registrationPage.registrationFormIsPresent(), true);
        registrationPage.enterUserDetails(
                user.gender(),
                user.fName(),
                user.lName(),
                user.password(),
                user.dateOfBirth(),
                user.monthOfBirth(),
                user.yearOfBirth()
        );
        registrationPage.submitUserDetails();
        Assert.assertEquals(registrationPage.getRegistrationErrorMsg(), Constants.F_NAME_MAX_LENGTH_ERROR_MSG);
    }


    @Test
    public void invalidRegistrationTest3() throws IOException {
        File file = new File("src/test/java/com/testdata/users/invalid-user-pwd-max-len.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterEmailToRegister(user.email());
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(registrationPage.registrationFormIsPresent(), true);
        registrationPage.enterUserDetails(
                user.gender(),
                user.fName(),
                user.lName(),
                user.password(),
                user.dateOfBirth(),
                user.monthOfBirth(),
                user.yearOfBirth()
        );
        registrationPage.submitUserDetails();
        Assert.assertEquals(registrationPage.getRegistrationErrorMsg(), Constants.PASSWORD_MAX_LENGTH_ERROR_MSG);
    }

    @Test
    public void invalidRegistrationTest4() throws IOException {
        File file = new File("src/test/java/com/testdata/users/invalid-user-no-pwd.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterEmailToRegister(user.email());
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(registrationPage.registrationFormIsPresent(), true);
        registrationPage.enterUserDetails(
                user.gender(),
                user.fName(),
                user.lName(),
                user.password(),
                user.dateOfBirth(),
                user.monthOfBirth(),
                user.yearOfBirth()
        );
        registrationPage.submitUserDetails();
        Assert.assertEquals(registrationPage.getRegistrationErrorMsg(), Constants.REGISTRATION_PASSWORD_INVALID_ERROR_MSG);
    }


    @Test
    public void validRegistrationTest() throws IOException {
        File file = new File("src/test/java/com/testdata/users/valid-user.json");
        User user = objectMapper.readValue(file, User.class);
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        String randomEmail = randomStringGenerator.generateEmail();
        signInPage.enterEmailToRegister(randomEmail);
        signInPage.submitEmailToBeginRegistration();
        Assert.assertEquals(registrationPage.registrationFormIsPresent(), true);
        registrationPage.enterUserDetails(
                user.gender(),
                user.fName(),
                user.lName(),
                user.password(),
                user.dateOfBirth(),
                user.monthOfBirth(),
                user.yearOfBirth()
        );
        registrationPage.submitUserDetails();
        Assert.assertEquals(registrationPage.getRegistrationSuccessMsg(), Constants.ACCOUNT_CREATION_SUCCESS_MSG);
        Assert.assertEquals(registrationPage.getNameOfRegisteredUser(), user.fName() + " " + user.lName());

    }

    @Test(dependsOnMethods = "validRegistrationTest")
    public void validLoginTest(){

    }
}
