package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(id = "homepage-slider")
    private WebElement homePageSlider;

    @FindBy(id = "htmlcontent_home")
    private WebElement homePageContentContainer;

    @FindBy(id = "home-page-tabs")
    private WebElement homePageTabs;

    @FindBy(className = "a.logout")
    private WebElement logOutBtn;

    @FindBy(css = "i.icon-home")
    private WebElement returnHomeIcon;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goTo(String homePageURL) {
        driver.get(homePageURL);
    }

    public boolean homePageSliderIsPresent() {
        return this.homePageSlider.isDisplayed();
    }

    public boolean uniqueHomePageElementsAreDisplayed() {
        return homePageSlider.isDisplayed() &&
                homePageTabs.isDisplayed() &&
                homePageContentContainer.isDisplayed();
    }

    public void logOut() {
        logOutBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(logOutBtn));
    }

    public boolean isAt(){
        return driver.getTitle().equalsIgnoreCase("My Shop");
    }
}
