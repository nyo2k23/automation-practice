package com.cta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class CheckOutPage extends BasePage{

    @FindBy(className = "breadcrumb")
    private WebElement checkoutPageTitle; // " > Your shopping cart"

    @FindBy(css = "#cart_summary tbody")
    private List<WebElement> cartItems;




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

}

