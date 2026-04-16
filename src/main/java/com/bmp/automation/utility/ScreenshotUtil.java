package com.bmp.automation.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtil {

    private static String getScreenshotPath(String folder, String testName) {

        String basePath = System.getProperty("user.dir")
                + "/test-output/screenshots/" + folder;

        // Create folder if not exists
        File directory = new File(basePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        return basePath + "/"
                + testName + "_"
                + System.currentTimeMillis() + ".png";
    }

    public static String capturePassScreenshot(WebDriver driver, String testName) {
        return captureScreenshot(driver, testName, "pass");
    }

    public static String captureFailScreenshot(WebDriver driver, String testName) {
        return captureScreenshot(driver, testName, "fail");
    }

    private static String captureScreenshot(WebDriver driver, String testName, String folder) {

        if (driver == null) {
            System.out.println("Driver is NULL. Screenshot not captured.");
            return null;
        }

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = getScreenshotPath(folder, testName);
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
