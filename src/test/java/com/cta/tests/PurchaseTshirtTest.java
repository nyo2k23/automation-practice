package com.cta.tests;

import com.cta.models.User;
import com.cta.pages.AccountPage;
import com.cta.pages.AddressFormPage;
import com.cta.pages.HomePage;
import com.cta.pages.SignInPage;
import com.cta.pages.checkout.CheckOutPage;
import com.cta.pages.products.DressesPage;
import com.cta.pages.products.TshirtsPage;
import com.cta.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;

public class PurchaseTshirtTest extends BaseTest{
    private HomePage homePage;
    private SignInPage signInPage;
    private TshirtsPage tshirtsPage;
    private AccountPage accountPage;
    private CheckOutPage checkOutPage;
    private AddressFormPage addressFormPage;
    private ObjectMapper objectMapper;


    @BeforeTest
    public void setLoginPage() {
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        this.tshirtsPage = new TshirtsPage(driver);
        this.accountPage = new AccountPage(driver);
        this.checkOutPage = new CheckOutPage(driver);
        this.addressFormPage = new AddressFormPage((driver));
        objectMapper = new ObjectMapper();
        homePage.goTo("http://www.automationpractice.pl/index.php");
        signInPage.goTo();
    }

    public void purchaseTshirtWithSavedAddressTest() throws IOException {
        File file = new File("src/test/java/com/cta/testdata/users/valid-user-login.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterLoginDetails(user.email(), user.password());
        signInPage.submitLogin();
        Assert.assertEquals(
                accountPage.getNameOfRegisteredUser(), user.fName() + " " + user.lName()
        );
        tshirtsPage.goTo();
        Assert.assertEquals(tshirtsPage.getPageHeading(), Constants.TSHIRT_PAGE_HEADING);
        tshirtsPage.hoverOverTshirt();
        tshirtsPage.clickQuickView();
        Assert.assertEquals(
                tshirtsPage.getQuickViewFrameHeading(),
                Constants.TSHIRT_TO_BE_BOUGHT.toLowerCase()
        );
        tshirtsPage.addProductToCart();
        Assert.assertEquals(
                tshirtsPage.cartSuccessMsg().toLowerCase().stripTrailing(),
                Constants.SUCCESSFULLY_ADDED_TO_CART_MSG.toLowerCase()
        );
    }
}
