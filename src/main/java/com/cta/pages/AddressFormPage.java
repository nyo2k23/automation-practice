package com.cta.pages;

import com.cta.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

//    {
//        "address1": "200 Address Line",
//            "city": "Ashley",
//            "id_state": "Ohio",
//            "postcode": "43003",
//            "phone": "020248202",
//            "phone_mobile": "02943022020"
//    }

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
    ){
        addressInputField.sendKeys(addressLine1);
        stateInputField.sendKeys(state);
        postCodeInputField.sendKeys(postCode);
        phoneInputField.sendKeys(phoneNumber);
        mobilePhoneInputField.sendKeys(mobileNumber);

    }
}
