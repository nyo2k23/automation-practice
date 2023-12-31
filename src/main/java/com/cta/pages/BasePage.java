package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    @FindBy(id = "header_logo")
    private WebElement siteLogo;

    @FindBy(css = "i.icon-home")
    private WebElement returnHomeIcon;

    private WebElement logOutBtn;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void returnHome(){
        siteLogo.click();
        wait.until(ExpectedConditions.invisibilityOf(returnHomeIcon));
    }
}
