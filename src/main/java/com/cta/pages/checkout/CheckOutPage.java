package com.cta.pages.checkout;

import com.cta.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Stream;

public class CheckOutPage extends BasePage {

    @FindBy(className = "breadcrumb")
    private WebElement shoppingCartSubHeading; // " > Your shopping cart"

    @FindBy(css = "#cart_summary tbody")
    private List<WebElement> cartItems;

    @FindBy(css = ".cart_navigation > a.button")
    private WebElement proceedToCheckOutBtn;

    @FindBy(id = "cart_quantity_down_4_17_0_1606")
    private WebElement reduceQuantity;

    @FindBy(id = "cart_quantity_up_4_17_0_1606")
    private WebElement increaseQuantity;

    @FindBy(css = "h1.page-heading")
    private WebElement shippingStageSubHeading;

    @FindBy(id = "cgv")
    private WebElement termsCheckBox;

    @FindBy(css = ".checker > span.checked")
    private WebElement checkBoxStatus;

    @FindBy(css = "button[name='processCarrier']")
    private WebElement processCarrierBtn;

    @FindBy(css = "a.bankwire")
    private WebElement payByBankWire;

    @FindBy(css = "a.cheque")
    private WebElement payByCheque;

    @FindBy(css = "h3.page-subheading")
    private WebElement paymentOptionConfirmationSubHeading;

    @FindBy(css = "#cart_navigation > button")
    private WebElement confirmOrder;

    @FindBy(css = "p.alert.alert-success")
    private WebElement orderSuccessMsg;

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return shoppingCartSubHeading.getText();
    }

    public int numberOfItemsInCart() {
        return cartItems.size();
    }

    private WebElement getCartItemContainer(String productName) {
        for (WebElement element : cartItems) {
            String elementText = element.findElement(By.cssSelector("td.cart_description .product_name")).getText();
            if (elementText.equals(productName)) {
                return element;
            }
        }
        return null;
    }

    public int cartItemQuantity(String itemName){
        WebElement cartItemContainer = getCartItemContainer(itemName);
        int count = Integer.parseInt(cartItemContainer.findElement(
                By.cssSelector(".cart_quantity_input")
                ).getAttribute("value"));
        return count;
    }

    public void reduceQuantityOfCartItemBy1(String itemName){
            WebElement cartItemContainer = getCartItemContainer(itemName);
            cartItemContainer.findElement(By.cssSelector(".cart_quantity .cart_quantity_down")).click();
    }


    public void removeProductFromCart(String productName){
        WebElement product = getCartItemContainer(productName);
        product.findElement(By.cssSelector(".cart_delete .cart_quantity_delete")).click();
        wait.until(ExpectedConditions.invisibilityOf(product));
    }

    public String getCartItemInfoForCartWithASingleProduct() {
        WebElement cartItem = cartItems.get(0);
        return cartItem.findElement(By.cssSelector("td.cart_description > small:nth-child(3) > a")).getText();
    }

    public void proceedToCheckout() {
        proceedToCheckOutBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(proceedToCheckOutBtn));
    }

    public String getPageSubHeading() {
        return shippingStageSubHeading.getText().toLowerCase().strip();
    }

    public boolean termsChecked() {
        if (checkBoxStatus.isDisplayed()) return true;
        return false;
    }

    public void agreeToTerms() {
        termsCheckBox.click();
        wait.until(ExpectedConditions.visibilityOf(checkBoxStatus));
    }

    public void submitCarrier() {
        processCarrierBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(processCarrierBtn));
    }

    public void clickPayByBankWire() {
        payByBankWire.click();
        wait.until(ExpectedConditions.invisibilityOf(payByBankWire));
    }

    public void clickPayByCheque() {
        payByCheque.click();
        wait.until(ExpectedConditions.invisibilityOf(payByCheque));
    }

    public String getPaymentOptionConfirmation() {
        return paymentOptionConfirmationSubHeading.getText().toLowerCase();
    }

    public void submitOrder() {
        confirmOrder.click();
        wait.until(ExpectedConditions.invisibilityOf(confirmOrder));
    }

    public String getOrderSuccessMsg() {
        return orderSuccessMsg.getText().strip().toLowerCase();
    }
}

