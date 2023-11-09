package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(id = "homepage-slider")
    private WebElement homePageSlider;

    @FindBy(id="htmlcontent_home")
    private WebElement homePageContentContainer;

    @FindBy(id = "home-page-tabs")
    private WebElement homePageTabs;

    public HomePage(WebDriver driver){
        super(driver);
    }




    public void goTo(String homePageURL){
        driver.get(homePageURL);
    }

    public boolean homePageSliderIsPresent(){
        return this.homePageSlider.isDisplayed();
    }

    public boolean uniqueHomePageElementsAreDisplayed(){
        return homePageSlider.isDisplayed() &&
                homePageTabs.isDisplayed() &&
                homePageContentContainer.isDisplayed();
    }
}
