package com.cta.pages.products;

import com.cta.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class DressesPage extends BasePage {

    @FindBy(id = "layered_block_left > p")
    private WebElement titleBlock;  // getText() == Catalog

    @FindBy(css = "#block_top_menu > ul > li:nth-child(2)")
    private WebElement dressCatalogPortal;

    @FindBy(css = "#center_column > ul > li.ajax_block_product.last-in-line h5 > a")
    private WebElement printedDress;

    @FindBy(id = "group_1")
    private WebElement size;

    @FindBy(id = "quantity_wanted")
    private WebElement quantity;

    @FindBy(id = "color_14")
    private WebElement blueColourCheckBox;

    @FindBy(css = "#add_to_cart button")
    private WebElement addToCartBtn;

    @FindBy(css = "h1 > span.cat-name")
    private WebElement catalogTitle;

    @FindBy(tagName = "#layer_cart_product_title")
    private WebElement cartProductTitle;

    @FindBy(id = "layer_cart")
    private WebElement cartLayer;

    @FindBy(css = "#center_column div.pb-center-column h1")
    private WebElement productPageTitle;

    @FindBy(css = ".layer_cart_product h2")
    private WebElement cartSubHeading;

    @FindBy(css = "a[title='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    @FindBy(className = "breadcrumb")
    private WebElement checkoutPageTitle;

    public DressesPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        wait.until(ExpectedConditions.visibilityOf(dressCatalogPortal));
        dressCatalogPortal.click();
        wait.until(ExpectedConditions.visibilityOf(catalogTitle));
    }

    public String getTitleOfCatalog() {
        return catalogTitle.getText();
    }

    public String getCartProductTitle() {
        return cartProductTitle.getText();
    }

    public String getProductPageTitle() {
        return productPageTitle.getText();
    }

    public void selectDressFromCatalog() {
        printedDress.click();
        wait.until(ExpectedConditions.visibilityOf(productPageTitle));
    }

    public void selectBlueDress() {
        blueColourCheckBox.click();
    }

    public void selectSize() {
        Select dressSizeSelection = new Select(size);
        dressSizeSelection.selectByVisibleText("M");
    }

    public void selectQuantity() {
        quantity.clear();
        quantity.sendKeys("4");
    }

    public void addProductToCart() {
        selectBlueDress();
        selectSize();
        selectQuantity();
        addToCartBtn.click();
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutBtn));
    }

    public void goToCheckout() {
        proceedToCheckoutBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(proceedToCheckoutBtn));
    }

    public String getCheckoutPageTitle() {
        return checkoutPageTitle.getText();
    }

    public String cartSuccessMsg() {
        return this.cartSubHeading.getText();
    }
}
