package com.automationstore.test;

import com.automationstore.setuptest.SetupTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.TestConstants;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SmokeTest extends SetupTest {
    private String expectedTitle = "A place to practice your automation skills!";

    @Test(priority = 1)
    void validTitleTest() throws IOException {


        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle); // assert projde bez problemu


        // vytvori se screenshot pri zavolani metody
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./Screenshots/image.png"));
    }

    //group contact us
    @Test (priority = 1)
    void validContactUsTitle(){
        String url = driver.getCurrentUrl();
        System.out.println("url is " + url);
        Boolean displayed = driver.findElement(By.xpath("//div[@id='block_frame_html_block_1776']/h2")).isDisplayed();
        String actual = driver.findElement(By.xpath("//div[@id='block_frame_html_block_1776']/h2")).getText();
        assertTrue(displayed,"The element is not displayed at page");
        assertEquals(actual.toUpperCase(), TestConstants.CONTACTUSTITLE);
    }


    @Test (priority = 1)
    void validContactUsTypeTwo(String locator){
        WebElement contactUs = driver.findElement(By.xpath(locator));
        assertTrue(contactUs.isDisplayed(),"contactUs is not displayed at webpage");
        assertEquals(contactUs.getText().toUpperCase(), TestConstants.CONTACTUSTITLE, "invalid contact us title");
    }

    @Test (priority = 1)
    void checkContactUs(){
        validContactUsTypeTwo(TestConstants.ContactUsTitle);
        validEmail(TestConstants.EMAILXPATH);
        validNUmber(TestConstants.PHONEXPATH);
    }

    @Test
    public void checkContactUsValues(){
        new HomePage(driver).checkContactUsTitle(TestConstants.ContactUsTitle)
                .checkContactUsEmail()
                .checkPhone();
    }
    //first task
    @Test
    public void checkAboutUs(){

    }

    @Test
    public void checkFilterValues() throws InterruptedException {
        new HomePage(driver).checkSearch();

    }

    @Test
    public void registration() {
        log.info("TEST LOGGER");
        new HomePage(driver).clickLoginRegister()
                .fillFirstName("Bruce")
                .fillLastName("Wayne")
                .fillEmail("bruce@wayne.com")
                .fillAddress("1007 Mountain Drive")
                .fillCity("Gotham")
                .fillZip("10000")
                .selectCountry("United States")
                .selectRegion("New York")
                .fillLoginName("Batman")
                .fillPassword("Joker1234")
                .fillPasswordConfirm("Joker1234")
                //here is missing step: check agree with privacy policy
                .clickContinue();
    }

    @Test
    public void verifyValuesInDm(){
        new HomePage(driver).clickLoginRegister()
                .checkCountryDropdown();

    }
    @Test
    public void verifyAccountLoginPage(){
        new HomePage(driver).clickLoginRegister().clickContinue()
                .checkRegisterAccount()
                .checkLoginPageForm();

    }


    //tohle už by nemělo být v test třidě just call method !!
    @Test (priority = 1)
    void validEmail(String locator){
        String actualMail = driver.findElement(By.xpath(locator)).getText();
        System.out.println("email is:" + actualMail);
        assertEquals(actualMail.trim(),TestConstants.EMAIL);
    }

    @Test (priority = 1)
    void validNUmber(String locator){
        String phone = driver.findElement(By.xpath(locator)).getText();
        System.out.println("phone is " + phone);
        assertEquals(phone.trim(), TestConstants.PHONE);
    }

    @Test (priority = 2)
    void invalidTitleTest(){
        driver.get("https://automationteststore.com");
        String actualTitle = driver.getTitle();
        // nasledujici assert selze a vypise se chyba
        assertEquals(actualTitle, "A place to practice your automation skills");
        // tato message nebude v logu protoze assert o radek vyse selhal a test se ukoncil.
        System.out.println("Hard Assert invalidTitleTest message");
    }
}
