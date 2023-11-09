package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class RegistrationPage extends BasePage{

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
    private WebElement dayOfBirthInputField;

    @FindBy(id = "months")
    private WebElement monthOfBirthInputField;

    @FindBy(id = "years")
    private WebElement yearOfBirthInputField;

    @FindBy(id = "submitAccount")
    private WebElement submitBtn;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void submitUserDetails(){
        submitBtn.click();
    }

    public void enterUserDetails(
            String gender,
            String fnAME,
            String lName,
            String email,
            String password,
            int dateOfBirth,
            String monthOfBirth,
            int yearOfBirth
    ){
        if (gender.equalsIgnoreCase("m")) {
            selectMale.click();
        } else if (gender.equalsIgnoreCase("f")) {
            selectFemale.click();
        }

        fNameInputField.sendKeys(fnAME);
        lNameInputField.sendKeys((lName));
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);

    }
}
