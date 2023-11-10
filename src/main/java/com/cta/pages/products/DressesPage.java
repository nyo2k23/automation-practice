package com.cta.pages.products;

import com.cta.pages.BasePage;
import com.cta.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class DressesPage extends BasePage {

    @FindBy(id = "layered_block_left > p")
    private WebDriver titleBlock;  // getText() == Catalog

//    @FindBy(id = "layered_id_attribute_group_14")
//    private WebElement blueColorCheckBox;

//    @FindBy(id = "layered_id_attribute_group_2")
//    private WebElement sizeMCheckBox;

    @FindBy(css = "#block_top_menu ul li:nth-child(2) a")
    private WebElement dressCatalogPortal;

    @FindBy(css = "li.ajax_block_product.last-in-line div div.left-block div a.product_img_link")
    private WebElement printedDress;

    @FindBy(id = "group_1")
    private WebElement dressSize;

    @FindBy(id = "quantity_wanted")
    private WebElement quantity;

    @FindBy(id = "color_14")
    private WebElement dressColourCheckBox;

    @FindBy(css = "#add_to_cart button")
    private WebElement addToCartBtn;

    @FindBy(css = "h1 > span.cat-name")
    private WebElement catalogTitle;

    @FindBy(tagName = "h1")
    private WebElement productPageTitle;

    @FindBy(css = "#layer_cart h2")
    private WebElement cartSubHeading;

    public DressesPage(WebDriver driver){
        super(driver);
    }

    public void goTo(){
        dressCatalogPortal.click();
        wait.until(ExpectedConditions.visibilityOf(catalogTitle));
    }

    public String getTitleOfCatalog(){
        return catalogTitle.getText();
    }

    public String getProductPageTitle(){
        return productPageTitle.getText();
    }

    public void selectDressFromCatalog(){
        printedDress.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(productPageTitle, Constants.DRESS_NAME));
    }

    public void selectBlueDress(){
        dressColourCheckBox.click();
    }

    public void selectSize(){
        Select dressSizeSelection = new Select(dressSize);
        dressSizeSelection.selectByVisibleText("M");
    }

    public void selectQuantity(){
        quantity.clear();
        quantity.sendKeys("4");
    }

    public void addProductToCart(){
        selectBlueDress();
        selectSize();
        selectQuantity();
        addToCartBtn.click();
    }

    public String cartSuccessMsg(){
        return this.cartSubHeading.getText();
        }

    // driver.switchTo().alert().dismiss();
}
