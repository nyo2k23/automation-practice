package com.cta.tests.purchases;

import com.cta.models.AddressForm;
import com.cta.models.User;
import com.cta.pages.*;
import com.cta.pages.account.AccountPage;
import com.cta.pages.account.AddressFormPage;
import com.cta.pages.checkout.CheckOutPage;
import com.cta.pages.products.DressesPage;
import com.cta.pages.signin.SignInPage;
import com.cta.tests.BaseTest;
import com.cta.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class PurchaseSummerDressTest extends BaseTest {
    private HomePage homePage;
    private SignInPage signInPage;
    private DressesPage dressesPage;
    private AccountPage accountPage;
    private CheckOutPage checkOutPage;
    private AddressFormPage addressFormPage;
    private ObjectMapper objectMapper;


    @BeforeTest
    public void setLoginPage() {
        this.homePage = new HomePage(driver);
        this.signInPage = new SignInPage(driver);
        this.dressesPage = new DressesPage(driver);
        this.accountPage = new AccountPage(driver);
        this.checkOutPage = new CheckOutPage(driver);
        this.addressFormPage = new AddressFormPage((driver));
        objectMapper = new ObjectMapper();
        homePage.goTo("http://www.automationpractice.pl/index.php");
        signInPage.goTo();
    }

    @Test
    public void purchaseDressTestNoAddressSaved() throws IOException {
        File file = new File("src/test/java/com/cta/testdata/users/valid-user-login.json");
        User user = objectMapper.readValue(file, User.class);
        signInPage.enterLoginDetails(user.email(), user.password());
        signInPage.submitLogin();
        Assert.assertEquals(
                accountPage.getNameOfRegisteredUser(), user.fName() + " " + user.lName()
        );
        dressesPage.goTo();
        Assert.assertEquals(
                dressesPage.getTitleOfCatalog().toLowerCase().stripTrailing(),
                Constants.DRESSES_PAGE_SUB_HEADING.toLowerCase()
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
                checkOutPage.getPageTitle().toLowerCase(),
                Constants.SHOPPING_CART_PAGE_SUB_HEADING.toLowerCase()
        );

        Assert.assertEquals(checkOutPage.numberOfItemsInCart(), 1);

        Assert.assertEquals(checkOutPage.getCartItemInfoForCartWithASingleProduct(), Constants.SUMMER_DRESS_CART_INFO);

        checkOutPage.proceedToCheckout();

        //  Move to another method no address saved & remove if statement keep code inside
        File addressFile = new File("src/test/java/com/cta/testdata/addresses/valid-user-address-data.json");
        AddressForm address = objectMapper.readValue(addressFile, AddressForm.class);
        if(driver.getTitle().equalsIgnoreCase(Constants.ADDRESS_FORM_PAGE_TITLE)) {

            Assert.assertEquals(addressFormPage.isCurrentlyOpen(), true);
            addressFormPage.addAddressDetails(address);
            addressFormPage.submitForm();
        }
            Assert.assertEquals(addressFormPage.isCorrectAddressInfo(address), true);
            addressFormPage.processAddress();
            Assert.assertEquals(
                    checkOutPage.getPageSubHeading().toLowerCase(),
                    Constants.ORDER_SHIPPING_PAGE_SUB_HEADING.toLowerCase()
            );

        checkOutPage.agreeToTerms();
        Assert.assertEquals(checkOutPage.termsChecked(), true);
        checkOutPage.submitCarrier();
        checkOutPage.clickPayByBankWire();
        Assert.assertEquals(
                checkOutPage.getPaymentOptionConfirmation(),
                Constants.BANKWIRE_OPTION_CONFIRMATION.toLowerCase()
        );
        checkOutPage.submitOrder();
        Assert.assertEquals(
                checkOutPage.getOrderSuccessMsg(),
                Constants.ORDER_SUCCESS_MSG.toLowerCase()
        );
    }
}
