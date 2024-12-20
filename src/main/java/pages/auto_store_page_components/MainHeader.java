package pages.auto_store_page_components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.AbstractPage;
import pages.autostore.page_objects.AccountPage;


public class MainHeader extends AbstractPage {

    public MainHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//div[@id='customernav']//a[contains(@href,'account/login')]")
    private WebElement loginRegisterButton;

    public AccountPage clickLoginRegister() {
        loginRegisterButton.click();
        return new AccountPage(getDriver());
    }
}
