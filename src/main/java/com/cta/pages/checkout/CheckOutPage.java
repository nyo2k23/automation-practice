package com.cta.pages.checkout;

import com.cta.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckOutPage extends BasePage {

    @FindBy(className = "breadcrumb")
    private WebElement checkoutPageTitle; // " > Your shopping cart"

    @FindBy(css = "#cart_summary tbody")
    private List<WebElement> cartItems;

    @FindBy(css = ".cart_navigation > a.button")
    private WebElement proceedToCheckOutBtn;


    @FindBy(id = "carrier_area")
    private WebElement shippingStageSubHeading;

    @FindBy(id = "cgv")
    private WebElement termsCheckBox;

    @FindBy(css = "checker > span.checked")
    private WebElement checkBoxStatus;

    @FindBy(css = "button [name='processCarrier']")
    private WebElement processCarrierBtn;


    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle(){
        return checkoutPageTitle.getText();
    }

    public int numberOfItemsInCart(){
        //List<WebElement> items = driver.findElements(By.cssSelector("tr.cart_item"));
        return cartItems.size();
    }

    public String getCartItemInfoForCartWithASingleProduct() {
        WebElement cartItem = cartItems.get(0);
//        cartItems.forEach(cartItem -> {
//            cartItem.findElement(By.cssSelector("td.cart_description > small:nth-child(3) > a"));
//                }
//        );
        return cartItem.findElement(By.cssSelector("td.cart_description > small:nth-child(3) > a")).getText();
    }

    public void proceedToCheckout(){
        proceedToCheckOutBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(proceedToCheckOutBtn));
    }



    public String getPageSubHeading(){
        return shippingStageSubHeading.getText();
    }

    public boolean termsChecked(){
        if(checkBoxStatus.isDisplayed()) return true;
        return false;
    }

    public void agreeToTerms(){
        termsCheckBox.click();
    }

    public void submitCarrier(){
        processCarrierBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(shippingStageSubHeading));
    }


}

