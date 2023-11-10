package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {

    @FindBy(id = "account-creation_form")
    private WebElement registrationForm;

    @FindBy(css = ".radio-inline:nth-of-type(1)")
    private WebElement selectMale;

    @FindBy(css = ".radio-inline:nth-of-type(2)")
    private WebElement selectFemale;

    @FindBy(id = "customer_firstname")
    private WebElement fNameInputField;

    @FindBy(id = "customer_lastname")
    private WebElement lNameInputField;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "passwd")
    private WebElement passwordInputField;

    @FindBy(id = "days")
    private WebElement dateOfBirthInputField;

    @FindBy(id = "months")
    private WebElement monthOfBirthInputField;

    @FindBy(id = "years")
    private WebElement yearOfBirthInputField;

    @FindBy(id = "submitAccount")
    private WebElement submitBtn;

    @FindBy(className = "alert-danger")
    private WebElement alertContainer;

    @FindBy(css = ".alert-danger ol li")
    private WebElement alertErrorMsg;

    @FindBy(className = "alert-success")
    private WebElement registrationSuccessAlert;

    @FindBy (css = "#header div:nth-child(1) > a > span")
    private WebElement accountHolder;

    @FindBy (className = "a.logout")
    private WebElement logOutBtn;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void submitUserDetails() {
        submitBtn.click();
    }

    public boolean registrationFormIsPresent() {
        if (registrationForm.isDisplayed()) {
            return true;
        }
        return false;
    }

    public void enterUserDetails(
            String gender,
            String fnAME,
            String lName,
            String password,
            int dateOfBirth,
            String monthOfBirth,
            int yearOfBirth
    ) {
        if (gender.equalsIgnoreCase("m")) {
            selectMale.click();
        } else if (gender.equalsIgnoreCase("f")) {
            selectFemale.click();
        }
        fNameInputField.sendKeys(fnAME);
        lNameInputField.sendKeys((lName));
        passwordInputField.sendKeys(password);
        Select birthDateSelection = new Select(dateOfBirthInputField);
        Select birthMonthSelection = new Select(monthOfBirthInputField);
        Select birthYearSelection = new Select(yearOfBirthInputField);
        birthDateSelection.selectByValue(String.valueOf(dateOfBirth));
        birthMonthSelection.selectByVisibleText(monthOfBirth + " ");
        birthYearSelection.selectByVisibleText(String.valueOf(yearOfBirth) + "  ");
    }

    public void submitAccount(){
        submitBtn.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(alertContainer),
                ExpectedConditions.invisibilityOf(submitBtn)
        ));

    }

    public String getRegistrationSuccessMsg(){
        return registrationSuccessAlert.getText();
    }

    public String getRegistrationErrorMsg(){
        return alertErrorMsg.getText();
    }

    public String getNameOfRegisteredUser(){
        return accountHolder.getText();
    }
    // alertContainer -->  text()
    // firstname is too long. Maximum length: 32
    //  firstname is required.
    // lastname is too long. Maximum length: 32
    // lastname is required.
    // passwd is too long. Maximum length: 32
    // passwd is invalid.
    // email is invalid.
}
