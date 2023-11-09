package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css="#header .login")
    private WebElement signInPortal;

    @FindBy(id = "authentication")
    private WebElement authenticationContainer;

    @FindBy(className = "page-heading")
    private WebElement pageHeading; // get text = Authentication

    @FindBy(css = "#create-account_form h3")
    private WebElement registerForm;  // get text = Create an account

    @FindBy(css = "#login_form h3")
    private WebElement loginForm;  // get text = Already registered?

    @FindBy(id = "email_create")
    private WebElement registerEmailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement registerEmailButton;

    @FindBy(id = "form-group form-ok")
    private WebElement emailInputFine;

    @FindBy(id = "create_account_error")
    private WebElement emailInputError;

    @FindBy(css = "#create_account_error ol li")
    private WebElement errorMessageElem;


    public SignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    public void goTo(){
        wait.until(ExpectedConditions.visibilityOf(signInPortal));
        signInPortal.click();
    }

    public boolean authenticationContainerIsPresent(){
        return authenticationContainer.isDisplayed();
    }

    public boolean registerAndLoginFormsArePresent(){
        return registerForm.isDisplayed() && loginForm.isDisplayed();
    }

    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(registerForm));
        registerEmailInput.sendKeys(email);
    }

    public void submitEmailForRegistration(){
        registerEmailButton.click();
    }

    public boolean isValidInput(){
        return emailInputFine.isDisplayed();
    }

    public String invalidInputMessage(){
        return errorMessageElem.getText();
    }

}
