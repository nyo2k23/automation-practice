package com.tests;

import com.cta.models.User;
import com.cta.pages.HomePage;
import com.cta.pages.products.DressesPage;
import com.cta.pages.SignInPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class PurchaseTests extends BaseTest
{
    private HomePage homePage;
    private SignInPage signInPage;
    private DressesPage dressesPage;
    private ObjectMapper objectMapper;


    @BeforeTest
    public void setLoginPage(){
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        this.dressesPage = new DressesPage(driver);
        objectMapper = new ObjectMapper();
        homePage.goTo("http://www.automationpractice.pl/index.php");
        signInPage.goTo();
    }

    @Test
    public void purchaseDressTest() throws IOException {
        File file = new File("src/test/java/com/testdata/users/valid-user-login.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterLoginDetails(user.email(), user.password());
        signInPage.submitLogin();
        dressesPage.goTo();
        Assert.assertEquals(
                dressesPage.getTitleOfCatalog().toLowerCase(),
                Constants.DRESSES_PAGE_TITLE.toLowerCase()
        );
        dressesPage.selectDressFromCatalog();
        Assert.assertEquals(
                dressesPage.getProductPageTitle().toLowerCase(), Constants.DRESS_NAME.toLowerCase()
        );
        dressesPage.addProductToCart();
        Assert.assertEquals(
                dressesPage.cartSuccessMsg().toLowerCase(),
                Constants.SUCCESSFULLY_ADDED_TO_CART_MSG.toLowerCase()
        );
    }
}
