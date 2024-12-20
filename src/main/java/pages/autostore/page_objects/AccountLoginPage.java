package pages.autostore.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.auto_store_page_components.MainHeader;

public class AccountLoginPage extends MainHeader {

    public AccountLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@type='submit' and @title='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@id='accountFrm_accountregister']")
    private WebElement radioRegisterAccount;

    @FindBy(xpath = "//div[@class=\"col-sm-6 returncustomer\"]/h2")
    private WebElement LoginPageTitle;


    @FindBy(xpath = "//div[@class='loginbox form-horizontal']/h4")
    private WebElement LoginPageText;

    @FindBy(xpath = "//input[@id='loginFrm_loginname']")
    private WebElement loginNameField;


    public ResetPasswordPage clickContinue() {
        continueButton.click();
        return new ResetPasswordPage(getDriver());
    }


    public AccountLoginPage checkRegisterAccount (){
        Boolean selected = radioRegisterAccount.isSelected();
        Assert.assertTrue(radioRegisterAccount.isDisplayed(),"Radio button does not display in page");
        Assert.assertTrue(selected,"radio " + radioRegisterAccount +"is selected");
        return this;
    }



    public AccountLoginPage checkLoginPageForm(){
        String title = LoginPageTitle.getText();
        Assert.assertEquals(title.toUpperCase(),"RETURNING CUSTOMER");
        title = LoginPageText.getText();
        Assert.assertEquals(title,"I am a returning customer.");
        System.out.println("text under title is: " + title);
        Assert.assertTrue(loginNameField.isDisplayed(),"missing field in page");
        return this;
    }

}
