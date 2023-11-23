package com.cta.pages.account;

import com.cta.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    @FindBy(css = "#header div:nth-child(1) > a > span")
    private WebElement accountHolder;

    @FindBy(className = "a.logout")
    private WebElement logOutBtn;

    @FindBy(id = "my-account")
    private WebElement pageId;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getNameOfRegisteredUser() {
        return accountHolder.getText();
    }
}
