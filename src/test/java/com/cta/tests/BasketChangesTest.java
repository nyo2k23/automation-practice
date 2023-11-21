package com.cta.tests;

import com.cta.models.User;
import com.cta.pages.HomePage;
import com.cta.pages.SignInPage;
import com.cta.pages.checkout.CheckOutPage;
import com.cta.pages.products.DressesPage;
import com.cta.pages.products.TshirtsPage;
import com.cta.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class BasketChangesTest extends BaseTest{
    private HomePage homePage;
    private SignInPage signInPage;
    private DressesPage dressesPage;
    private TshirtsPage tshirtsPage;
    private CheckOutPage checkOutPage;
    private ObjectMapper objectMapper;


    @BeforeTest
    public void setLoginPage() {
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        this.dressesPage = new DressesPage(driver);
        this.tshirtsPage = new TshirtsPage(driver);
        this.checkOutPage = new CheckOutPage(driver);
        this.objectMapper = new ObjectMapper();
        homePage.goTo("http://www.automationpractice.pl/index.php");
        signInPage.goTo();
    }

    @Test
    public void addToCartAndChangeItemsTest() throws IOException {
        File file = new File("src/test/java/com/cta/testdata/users/valid-user-login.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterLoginDetails(user.email(), user.password());
        signInPage.submitLogin();
        dressesPage.goTo();
        dressesPage.selectDressFromCatalog();
        dressesPage.addProductToCart();
        dressesPage.goToCheckout();
        int totalNumberOfProductsInCart = checkOutPage.numberOfItemsInCart();
        tshirtsPage.goTo();
        tshirtsPage.hoverOverTshirt();
        tshirtsPage.clickQuickView();
        tshirtsPage.addProductToCart();
        tshirtsPage.goToCheckout();
        Assert.assertEquals(
                checkOutPage.numberOfItemsInCart(),
                totalNumberOfProductsInCart+1);
        int numOfSummerDressesInCart = checkOutPage.getCartItemQuantity(Constants.DRESS_NAME);
        checkOutPage.reduceQuantityOfCartItemBy1(Constants.DRESS_NAME);
        Assert.assertEquals(
                checkOutPage.getCartItemQuantity(Constants.DRESS_NAME),
                numOfSummerDressesInCart-1
        );
        int numberOfItemsInCartBeforeRemoval = checkOutPage.numberOfItemsInCart();
        System.out.println(numberOfItemsInCartBeforeRemoval);
        System.out.printf("Removing product...");
        checkOutPage.removeProductFromCart(Constants.TSHIRT_TO_BE_BOUGHT);
        int numberOfItemsInCartAfterRemoval = checkOutPage.numberOfItemsInCart();
        System.out.println(numberOfItemsInCartAfterRemoval);
        Assert.assertEquals(
                numberOfItemsInCartAfterRemoval,
                numberOfItemsInCartBeforeRemoval-1
        );
    }
}
