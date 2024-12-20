package com.automationstore.setuptest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static java.lang.String.format;

public class SetupTest {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static FileInputStream fis;

    public WebDriver getDriver() { return driver; }

    protected static Logger log = LogManager.getLogger(SetupTest.class.getName());

    @BeforeSuite
    protected void setupSuite(){
        System.out.println("Running Before Suite");
    }

    @BeforeMethod
    protected void setupTest(){
        System.out.println("Running test setup");
        try {
            fis = new FileInputStream
                    (System.getProperty("user.dir") + "\\src\\test\\resources\\Config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(config.getProperty("browser"));

        if(config.getProperty("browser").equals("chrome")){
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
            System.out.println("Chrome set");
        }

        if(config.getProperty("browser").equals("edge")){
            WebDriverManager.edgedriver().setup();

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--remote-allow-origins=*");

            driver = new EdgeDriver(edgeOptions);
            System.out.println("Edge set");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
        driver.manage().window().maximize();
        driver.get(config.getProperty("url"));
        System.out.println(config.getProperty("browser"));

    }

    @AfterSuite
    protected void tearDownAfterSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    protected void tearDownAfterMethod(ITestResult result){
//  vytvareni screenshotu lze udelat i tak, ze po kazdem testu se kontroluje testresult
//  a pokud nebyl uspesny stav, provede se screenshot
//        if(ITestResult.FAILURE == result.getStatus()){
//            ScreenshotUtil.captureScreenshot(driver, result.getTestName());
//        }

        if (driver != null) {
            driver.close();
        }
    }

    protected void logStep(String stepDescription) {
        log.info(format("     STEP: " + stepDescription));
    }

}