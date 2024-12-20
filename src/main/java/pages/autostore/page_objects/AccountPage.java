package pages.autostore.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import pages.TestConstants;
import pages.auto_store_page_components.MainHeader;
import pages.autostore.page_objects.model.RegistrationModel;
import pages.autostore.page_objects.model.enums.Region;

import java.util.ArrayList;
import java.util.List;

public class AccountPage extends MainHeader {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@type='submit' and @title='Continue']")
    private WebElement continueButton;
    @FindBy(xpath = "//input[@id='AccountFrm_firstname']")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[@id='AccountFrm_lastname']")
    private WebElement lastNameInput;
    @FindBy(xpath = "//input[@id='AccountFrm_email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='AccountFrm_address_1']")
    private WebElement addressInput;
    @FindBy(xpath = "//input[@id='AccountFrm_city']")
    private WebElement cityInput;
    @FindBy(xpath = "//select[@id='AccountFrm_zone_id']")
    private WebElement regionElement;
    private Select regionSelect;
    @FindBy(xpath = "//select[@id='AccountFrm_country_id']")
    private WebElement countryElement;
    private Select countrySelect;
    @FindBy(xpath = "//input[@id='AccountFrm_postcode']")
    private WebElement zipInput;
    @FindBy(xpath = "//input[@id='AccountFrm_loginname']")
    private WebElement loginNameInput;
    @FindBy(xpath = "//input[@id='AccountFrm_password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@id='AccountFrm_confirm']")
    private WebElement passwordConfirmInput;

    public AccountLoginPage clickContinue() {
        continueButton.click();
        return new AccountLoginPage(getDriver());
    }

    public AccountPage fillFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public AccountPage fillLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public AccountPage fillEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public AccountPage fillAddress(String address) {
        addressInput.sendKeys(address);
        return this;
    }

    public AccountPage fillCity(String city) {
        cityInput.sendKeys(city);
        return this;
    }

    public AccountPage selectRegion(String region) {
        new Select(regionElement).selectByVisibleText(region);
        return this;
    }

    public AccountPage checkCountryDropdown() {
        new Select(regionElement).getOptions();
        List<WebElement> options = new Select(countryElement).getOptions();
        List<WebElement> countries = new ArrayList<>();
        System.out.println("selected country is " + options.get(1).getText());
        Assert.assertEquals(options.get(1).getText(),"Afghanistan", "invalid value");
        Assert.assertFalse(options.isEmpty());
        for (WebElement option : options){
            if(!option.getText().equalsIgnoreCase("--- Please Select ---"))
                countries.add(option);
        }
        Assert.assertEquals(countries.size(), TestConstants.COUNTRIESAMOUNT, "check out results because one of option missing");
        return this;
    }


    public AccountPage fillZip(String zip) {
        zipInput.sendKeys(zip);
        return this;
    }

    public AccountPage selectCountry(String country) {
        new Select(countryElement).selectByVisibleText(country);
        return this;
    }

    public AccountPage fillLoginName(String loginName) {
        loginNameInput.sendKeys(loginName);
        return this;
    }

    public AccountPage fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public AccountPage fillPasswordConfirm(String passwordConfirm) {
        passwordConfirmInput.sendKeys(passwordConfirm);
        return this;
    }

    public AccountPage fillMandatoryRegistrationFields(String firstName, String lastName, String email, String address, String city, String zip, String country, Region region, String loginName, String password, String passwordConfirm) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillEmail(email);
        fillAddress(address);
        fillCity(city);
        fillZip(zip);
        selectCountry(country);
        selectRegion(region.getRegionName());
        fillLoginName(loginName);
        fillPassword(password);
        fillPasswordConfirm(passwordConfirm);
        return this;
    }

    public AccountPage fillAllMandatoryFields(String firstName, String lastName, String email, String address, String city, String zip, String country, String region, String loginName, String password, String passwordConfirm) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        zipInput.sendKeys(zip);
        countrySelect.selectByVisibleText(country);
        regionSelect.selectByVisibleText(region);
        loginNameInput.sendKeys(loginName);
        passwordInput.sendKeys(password);
        passwordConfirmInput.sendKeys(passwordConfirm);
        return this;
    }

    public AccountPage fillMandatoryRegistrationFields(RegistrationModel registrationModel) {
        return fillMandatoryRegistrationFields(
                registrationModel.getFirstName(),
                registrationModel.getLastName(),
                registrationModel.getEmail(),
                registrationModel.getAddress(),
                registrationModel.getCity(),
                registrationModel.getZip(),
                registrationModel.getCountry(),
                registrationModel.getRegion(),
                registrationModel.getLoginName(),
                registrationModel.getPassword(),
                registrationModel.getPasswordConfirm()
        );
    }
}

