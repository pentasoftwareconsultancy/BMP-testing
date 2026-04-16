package com.bmp.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.bmp.automation.core.BaseTest;
import com.bmp.automation.utility.LogUtil;
import com.bmp.automation.utility.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListeners extends BaseTest implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Logger (SLF4J + Log4j2)
    private static final Logger log = LoggerFactory.getLogger(TestListeners.class);

    // ================================
    // SUITE START
    // ================================
    @Override
    public void onStart(ITestContext context) {

        String reportPath = System.getProperty("user.dir") + "/target/ExtentReports.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "BookMyParcel.com");
        extent.setSystemInfo("Tester", "QA Team");
        extent.setSystemInfo("Browser", "Chrome");

        log.info("===== Test Suite Started =====");
    }

    // ================================
    // TEST START
    // ================================
    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        log.info("STARTING TEST: {" +
                "}", testName);

        ExtentTest test = extent.createTest(
                testName,
                result.getMethod().getDescription()
        );

        extentTest.set(test);

        extentTest.get().info("Test Started");
        extentTest.get().info("Thread ID: " + Thread.currentThread().getId());
    }

    // ================================
    // TEST SUCCESS
    // ================================
    @Override
    public void onTestSuccess(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        log.info("TEST PASSED: {" +
                "}", testName);

        extentTest.get().pass("Test Passed");

        WebDriver driver = BaseTest.getDriver();

        // Screenshot
        if (driver != null) {
            String path = ScreenshotUtil.capturePassScreenshot(driver, testName);
            if (path != null) {
                extentTest.get().addScreenCaptureFromPath(path);
            }
        }

        // Attach logs to report
        attachLogs();
    }

    // ================================
    // TEST FAILURE
    // ================================
    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        log.error("TEST FAILED: {" +
                "}", testName);
        log.error("Exception: ", result.getThrowable());

        extentTest.get().fail(result.getThrowable());

        WebDriver driver = BaseTest.getDriver();

        // Screenshot
        if (driver != null) {
            String path = ScreenshotUtil.captureFailScreenshot(driver, testName);
            if (path != null) {
                extentTest.get().addScreenCaptureFromPath(path);
            }
        }

        // Attach logs
        attachLogs();
    }

    // ================================
    // TEST SKIPPED
    // ================================
    @Override
    public void onTestSkipped(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        log.warn("TEST SKIPPED: {" +
                "}", testName);

        extentTest.get().skip("Test Skipped");

        attachLogs();
    }

    // ================================
    // SUITE FINISH
    // ================================
    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        log.info("===== Test Suite Finished =====");
    }

    // ================================
    // DRIVER ACCESS (ThreadLocal)
    // ================================
    private WebDriver getDriver(ITestResult result) {
        return BaseTest.getDriver(); // Thread-safe driver
    }

    // ================================
    // Attach Logs to Extent Report
    // ================================
    private void attachLogs() {

        try {
            String threadId = String.valueOf(Thread.currentThread().getId());

            String logs = LogUtil.readLog(threadId);

            extentTest.get().info("<pre>" + logs + "</pre>");

        } catch (Exception e) {
            extentTest.get().warning("Unable to attach logs");
            log.error("Error attaching logs: ", e);
        }
    }

}
