package com.cta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage{

    @FindBy(id = "search_query_top")
    private WebElement searchBoxTop;

    @FindBy(css = "button[name='submit_search']")
    private WebElement getSearchSubmitBtn;

    @FindBy(css = "p .alert .alert-warning")
    private WebElement searchErrorMsg;

    @FindBy(css = "h1.page-heading.product-listing")
    private WebElement searchResultsHeading;

    @FindBy(css = "span.heading-counter")
    private WebElement searchResultCounterMsg;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void search(String product){
        searchBoxTop.sendKeys(product);
        getSearchSubmitBtn.click();
        wait.until(ExpectedConditions.visibilityOf(searchResultsHeading));
    }

    public String getPageHeading(){
        return searchResultsHeading.getText().strip();
    }

    public String getSearchErrorMsg() {
        return searchErrorMsg.getText().strip().toLowerCase();
    }

    public String getSearchResultCounterMsg() {
        return searchResultCounterMsg.getText().strip();
    }
}
