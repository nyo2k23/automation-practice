package com.tests;

import com.cta.models.User;
import com.cta.pages.AccountPage;
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

public class PurchaseTests extends BaseTest
{
    private HomePage homePage;
    private SignInPage signInPage;
    private DressesPage dressesPage;
    private AccountPage accountPage;
    private ObjectMapper objectMapper;


    @BeforeTest
    public void setLoginPage(){
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        this.dressesPage = new DressesPage(driver);
        this.accountPage = new AccountPage(driver);
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
        Assert.assertEquals(
                accountPage.getNameOfRegisteredUser(), user.fName() + " " + user.lName()
        );
        dressesPage.goTo();
        Assert.assertEquals(
                dressesPage.getTitleOfCatalog().toLowerCase().stripTrailing(),
                Constants.DRESSES_PAGE_TITLE.toLowerCase()
        );
        dressesPage.selectDressFromCatalog();


        Assert.assertEquals(
                dressesPage.getProductPageTitle().toLowerCase().stripTrailing(),
                Constants.DRESS_NAME.toLowerCase()
        );
        dressesPage.addProductToCart();
        Assert.assertEquals(
                dressesPage.cartSuccessMsg().toLowerCase().stripTrailing(),
                Constants.SUCCESSFULLY_ADDED_TO_CART_MSG.toLowerCase()
        );

        dressesPage.goToCheckout();
        Assert.assertEquals(
                dressesPage.getCheckoutPageTitle().toLowerCase().stripLeading(),
                Constants.CHECKOUT_PAGE_TITLE.toLowerCase());
    }
}
