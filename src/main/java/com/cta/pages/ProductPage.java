package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy(id = "layered_block_left > p")
    private WebDriver titleBlock;  // get tesxt == Catalog
    public ProductPage(WebDriver driver){
        super(driver);
    }



    // driver.switchTo().alert().dismiss();
}
