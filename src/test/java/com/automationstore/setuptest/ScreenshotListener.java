package com.automationstore.setuptest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static utils.ScreenshotUtil.captureScreenshot;
import static utils.ScreenshotUtil.deleteOldScreenshots;

public class ScreenshotListener extends TestListenerAdapter {

    @Override
    public void onStart(ITestContext testContext) {
        System.out.println("ScreenshotListener is deleting old screenshots");
        deleteOldScreenshots();
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        System.out.println(testResult.getName() + " has failed");

//        ITestContext context = testResult.getTestContext();
//        System.out.println("Context je: " + context);
        Object currentClass = testResult.getInstance();
        System.out.println("Current class: " + currentClass);
        // Object currentClassName = testResult.getClass();
        WebDriver driver = ((SetupTest) currentClass).getDriver();
        // take screenshot
        captureScreenshot(driver, testResult.getName());
    }

}
