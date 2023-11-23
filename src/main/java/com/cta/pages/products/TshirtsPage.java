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
    private Actions action;

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

    // some of the following elems are the same for all product catalogs
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

    @FindBy(id = "product")
    private WebElement pageId;

    @FindBy(css = "#product .pb-center-column")
    private WebElement centralQuickViewArea;

    @FindBy(tagName = "iframe")
    private WebElement iframeProductDetail;

    public TshirtsPage(WebDriver driver) {
        super(driver);
        this.action = new Actions(driver);
    }

    public void goTo() {
        wait.until(ExpectedConditions.visibilityOf(tShirtsCatalogPortal));
        tShirtsCatalogPortal.click();
        wait.until(ExpectedConditions.titleIs(TSHIRTS_PAGE_TITLE));
    }

    public void hoverOverTshirt() {
        action.moveToElement(tShirtToBePurchased).build().perform();
        wait.until(ExpectedConditions.visibilityOf(quickViewPortal));
    }

    public String getPageHeading() {
        return pageHeading.getText().toLowerCase().strip();
    }

    public void clickQuickView() {
        quickViewPortal.click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeProductDetail));
        //driver.switchTo().frame(iframeProductDetail);
        wait.until(ExpectedConditions.visibilityOf(quickViewFrameHeading));
    }

    public String getQuickViewFrameHeading() {
        action.moveToElement(centralQuickViewArea).build().perform();
        return quickViewFrameHeading.getText().toLowerCase().strip();
    }


    private void selectOrangeShirt() {
        orangeColourCheckBox.click();
    }

    private void selectSize() {
        Select tShirtSizeSelection = new Select(size);
        tShirtSizeSelection.selectByVisibleText("S");
    }

    private void selectQuantity() {
        quantity.clear();
        quantity.sendKeys("1");
    }

    public void addProductToCart() {
        selectOrangeShirt();
        selectSize();
        selectQuantity();
        addToCartBtn.click();
        driver.switchTo().parentFrame();
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


