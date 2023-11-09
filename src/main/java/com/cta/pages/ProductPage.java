package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductPage {
    private WebDriver driver;

    @FindBy(id = "layered_block_left > p")
    private WebDriver titleBlock;  // get tesxt == Catalog
    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    // driver.switchTo().alert().dismiss();
}
