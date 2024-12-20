package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.auto_store_page_components.MainHeader;

import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


    public class HomePage extends MainHeader {

        public HomePage(WebDriver driver) {
            super(driver);
        }

        @FindBy(xpath = TestConstants.ContactUsTitle)
        private WebElement ContactUs;

        @FindBy(xpath = TestConstants.EMAILXPATH)
        private WebElement EmailValue;

        @FindBy(xpath = TestConstants.PHONEXPATH)
        private WebElement Phone;

        @FindBy(xpath ="//input[@id='filter_keyword']")
        private WebElement searchFilter;

        @FindBy(xpath = "//div[@class='input-group']//input[@id='keyword']")
        private WebElement searchInput;

        @FindBy(xpath = "//select[@id='category_id']")
        private WebElement categoryElement;
        private Select categorySelect;

        @FindBy(xpath = "//ul[@id='search-category']")
        private WebElement CategoryDrodpown;




        public pages.HomePage checkContactUsTitle(String locator){
            assertTrue(ContactUs.isDisplayed(),"contactUs is not displayed at webpage");
            assertEquals(ContactUs.getText().toUpperCase(), TestConstants.CONTACTUSTITLE, "invalid contact us title");
            return this;
        }

        public pages.HomePage checkContactUsEmail(){
            String actualMail = EmailValue.getText();
            System.out.println("email is:" + actualMail);
            assertEquals(actualMail.trim(),TestConstants.EMAIL);
            return this;
        }

        public pages.HomePage checkPhone(){
            String phone = Phone.getText();
            System.out.println("phone is " + phone);
            assertEquals(phone.trim(), TestConstants.PHONE);
            return this;
        }

        public pages.HomePage checkAboutUs(){
            return this;
        }

        public pages.HomePage checkSearch() throws InterruptedException {
            searchFilter.click();
            List<WebElement> options = CategoryDrodpown.findElements(By.tagName("li"));
            options.get(TestConstants.APPARELINDEX).click();
            searchFilter.sendKeys(TestConstants.SEARCHEDWORD);
            //use KEYS in selenium
            searchFilter.sendKeys(Keys.ENTER);
            String searchWord = searchInput.getAttribute("value");
            assertEquals(searchWord,TestConstants.SEARCHEDWORD);
            String selectedValue = new Select(categoryElement).getFirstSelectedOption().getText();
            System.out.println("selected value is " + selectedValue.trim());
            assertEquals(selectedValue.trim(),"Apparel & accessories");
            return this;
        }

    }

