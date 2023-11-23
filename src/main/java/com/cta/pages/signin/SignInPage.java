package com.cta.pages.signin;

import com.cta.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends BasePage {

    @FindBy(css = "#header .login")
    private WebElement signInPortal;

    @FindBy(id = "authentication")
    private WebElement authenticationContainer;

    @FindBy(className = "page-heading")
    private WebElement pageHeading; // get text = Authentication

    @FindBy(css = "#create-account_form h3")
    private WebElement registerForm;  // get text = Create an account

    @FindBy(css = "#login_form h3")
    private WebElement loginForm;

    @FindBy(id = "email_create")
    private WebElement registerEmailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement registerEmailButton;

    @FindBy(id = "form-group form-ok")
    private WebElement emailInputFine;

    @FindBy(css = ".alert-danger ol li")
    private WebElement loginErrorMsgElem;

    @FindBy(css = "#create_account_error ol li")
    private WebElement registrationErrorMessageElem;

    @FindBy(css = "h3.page-subheading")
    private WebElement pageSubHeading;

    @FindBy(id = "email")
    private WebElement emailLoginField;

    @FindBy(id = "passwd")
    private WebElement passwordLoginField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLoginBtn;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        wait.until(ExpectedConditions.visibilityOf(signInPortal));
        signInPortal.click();
    }

    public boolean authenticationContainerIsPresent() {
        return authenticationContainer.isDisplayed();
    }

    public boolean registerAndLoginFormsArePresent() {
        return registerForm.isDisplayed() && loginForm.isDisplayed();
    }

    public void enterEmailToRegister(String email) {
        wait.until(ExpectedConditions.visibilityOf(registerForm));
        registerEmailInput.sendKeys(email);
    }

    public void submitEmailToBeginRegistration() {
        registerEmailButton.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(registrationErrorMessageElem),
                ExpectedConditions.invisibilityOf(registerEmailButton)
        ));
    }


    public void enterLoginDetails(String email, String password) {
        emailLoginField.sendKeys(email);
        passwordLoginField.sendKeys(password);
    }

    public void submitLogin() {
        submitLoginBtn.click();
        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.visibilityOf(loginErrorMsgElem),
                        ExpectedConditions.invisibilityOf(submitLoginBtn)
                )
        );
    }

    public boolean isValidInput() {
        return emailInputFine.isDisplayed();
    }

    public boolean isInvalidInput() {
        return registrationErrorMessageElem.isDisplayed();
    }

    public String invalidRegistrationInputMessage() {
        return registrationErrorMessageElem.getText();
    }

    public String invalidLoginInputMsg() {
        return loginErrorMsgElem.getText();
    }
}
