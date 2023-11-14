package com.cta.pages.products;

import com.cta.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TshirtsPage extends BasePage {

    private final String THSIRTS_PAGE_HEADING = "T-SHIRTS ";
    private final String TSHIRTS_PAGE_TITLE = "T-shirts - My Shop";

    @FindBy(css = "#block_top_menu > ul > li:nth-child(3)")
    private WebElement tShirtsCatalogPortal;

    @FindBy(css = "h1.page-heading span.cat-name")
    private WebElement pageHeading;

    @FindBy(css = "div > a.product_img_link")
    private WebElement tShirtToBePurchased;

    @FindBy(css = "a.quick-view")
    private WebElement quickViewPortal;

    @FindBy(tagName = "h1")
    private WebElement quickViewFrameHeading;

    // the following 6 elems are the same for product catalogs
    @FindBy(id = "group_1")
    private WebElement size;

    @FindBy(id = "quantity_wanted")
    private WebElement quantity;

    @FindBy(id = "color_13")
    private WebElement orangeColourCheckBox;

    @FindBy(css = "#add_to_cart button")
    private WebElement addToCartBtn;

    @FindBy(css = "a[title='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    @FindBy(css = ".layer_cart_product h2")
    private WebElement cartSubHeading;

    public TshirtsPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        wait.until(ExpectedConditions.visibilityOf(tShirtsCatalogPortal));
        tShirtsCatalogPortal.click();
        wait.until(ExpectedConditions.titleIs(TSHIRTS_PAGE_TITLE));
    }

    public void hoverOverTshirt() {
        Actions action = new Actions(driver);
        action.moveToElement(tShirtToBePurchased).build().perform();
        wait.until(ExpectedConditions.visibilityOf(quickViewPortal));
    }

    public String getPageHeading() {
        return pageHeading.getText().toLowerCase().strip();
    }

    public void clickQuickView() {
        quickViewPortal.click();
        wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
    }

    public String getQuickViewFrameHeading() {
        return quickViewPortal.getText().toLowerCase().strip();
    }


    private void selectOrangeShirt() {
        orangeColourCheckBox.click();
    }

    private void selectSize() {
        Select dressSizeSelection = new Select(size);
        dressSizeSelection.selectByVisibleText("M");
    }

    private void selectQuantity() {
        quantity.clear();
        quantity.sendKeys("4");
    }

    public void addProductToCart() {
        selectOrangeShirt();
        selectSize();
        selectQuantity();
        addToCartBtn.click();
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutBtn));
    }

    public void goToCheckout() {
        proceedToCheckoutBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(proceedToCheckoutBtn));
    }

    public String cartSuccessMsg() {
        return this.cartSubHeading.getText();
    }
}


