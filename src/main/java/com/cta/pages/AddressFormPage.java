package com.cta.pages;

import com.cta.models.AddressForm;
import com.cta.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddressFormPage extends BasePage {

    public AddressFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1.page-subheading")
    private WebElement pageSubHeading;

    @FindBy(id = "address1")
    private WebElement addressInputField;

    @FindBy(id = "city")
    private WebElement cityInputField;

    @FindBy(id = "id_state")
    private WebElement stateInputField;

    @FindBy(id = "postcode")
    private WebElement postCodeInputField;

    @FindBy(id = "phone")
    private WebElement phoneInputField;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneInputField;

    @FindBy(id = "submitAddress")
    private WebElement submitBtn;

    @FindBy(css = "address_address1 address_address2")
    private WebElement savedAddress;

    @FindBy(css = "address_city address_state_name address_postcode")
    private WebElement savedCityStatePostCode;

    @FindBy(className = "address_phone")
    private WebElement savedPhone;

    @FindBy(className = "address_phone_mobile")
    private WebElement savedMobilePhone;
//    {
//        "address1": "200 Address Line",
//            "city": "Ashley",
//            "id_state": "Ohio",
//            "postcode": "43003",
//            "phone": "020248202",
//            "phone_mobile": "02943022020"
//    }
    @FindBy(css = "button[name='processAddress'")
    private WebElement processAddressBtn;



    public boolean isCurrentlyOpen() {
        return (
                pageSubHeading.isDisplayed() && pageSubHeading.getText().equalsIgnoreCase(Constants.ADDRESS_PAGE_SUB_HEADING)
        );
    }

    public void addAddressDetails(
            String addressLine1,
            String state,
            String postCode,
            String phoneNumber,
            String mobileNumber
    ) {
        addressInputField.sendKeys(addressLine1);
        stateInputField.sendKeys(state);
        postCodeInputField.sendKeys(postCode);
        phoneInputField.sendKeys(phoneNumber);
        mobilePhoneInputField.sendKeys(mobileNumber);

    }

    public void submitForm() {
        submitBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(submitBtn));
    }

    public boolean isCorrectAddressInfo(AddressForm address) {
        boolean isAddressSimilar = savedAddress.getText().equalsIgnoreCase(address.address1());
        boolean isCityStatePostCode = savedCityStatePostCode.getText()
                .equalsIgnoreCase(
                        address.city() + ", " + address.id_state() + " " + address.postcode()
                );
        boolean isPhoneSimilar = savedPhone.getText().equalsIgnoreCase(address.phone());
        boolean isMobilePhoneSimilar = savedMobilePhone.getText().equalsIgnoreCase(address.phone_mobile());
        if (!isAddressSimilar || !isCityStatePostCode || !isPhoneSimilar || !isMobilePhoneSimilar) {
            return false;
        }
        return true;
    }

    public void processAddress(){
        processAddressBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(submitBtn));
    }
}
