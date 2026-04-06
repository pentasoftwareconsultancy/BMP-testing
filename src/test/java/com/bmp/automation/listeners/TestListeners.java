package com.bmp.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.bmp.automation.core.BaseTest;
import com.bmp.automation.utility.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListeners extends BaseTest implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + "/target/ExtentReports.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "NexusCRM.com");
        extent.setSystemInfo("Tester", "QA Team");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO, "Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");

        WebDriver driver = getDriver(result);

        String path = ScreenshotUtil.capturePassScreenshot(driver, result.getMethod().getMethodName());
        if (path != null) {
            extentTest.get().addScreenCaptureFromPath(path);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = getDriver(result);

        String path = ScreenshotUtil.captureFailScreenshot(driver, result.getMethod().getMethodName());
        if (path != null) {
            extentTest.get().addScreenCaptureFromPath(path);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Fetch driver safely from BaseTest
//    private WebDriver getDriver(ITestResult result) {
//        Object testClass = result.getInstance();
//        if (testClass instanceof BaseTest) {
//            return getDriver();
//        }
//        return null;
//    }
    private WebDriver getDriver(ITestResult result) {
        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {
            return ((BaseTest) testClass).getDriver();
        }

        return null;
    }

}
