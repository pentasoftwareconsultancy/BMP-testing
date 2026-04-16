package com.bmp.automation.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utils {

    //decode any sensitive details
    public static String decodeString(String str) {
        String decodedString = "";
        Base64.Decoder decoder = Base64.getDecoder();
        decodedString = new String(decoder.decode(str));
        return decodedString;

    }
    //to get unique number and time
    public static String epochTime(Long epoch) {
        Date date = new Date(epoch);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+5:30'");
        return format.format(date);
    }

    //check file exist at given location
    public static boolean checkFileExistAtPath(String filePath) {
        return new File(filePath).exists();
    }

    //take screenshot
    public static String captureFailScreenShot(WebDriver driver, String filename) throws IOException {
        TakesScreenshot scrshot	= (TakesScreenshot)driver;
        File source = scrshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/FailedTestScreenshots/"+filename+".png";
        File destination = new File(path);
        FileHandler.copy(source, destination);
        return path;
    }

    public static String screenShotOfPassTest(WebDriver driver, String filename) throws IOException {
        TakesScreenshot	scrshot	= (TakesScreenshot)driver;
        File source = scrshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/PassTestScreenshots/"+filename+".png";
        File destination = new File(path);
        FileHandler.copy(source, destination);
        return path;
    }

    //JS scroll to the bottom of the page
    public static void jsScrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    //JS scroll to the bottom of the page
    public static void jsScrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //JS send keys
    public static void sendKeyUsingJS(WebDriver driver, WebElement element, String input) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", element, input);
    }
    //alternate Sendkeys using Js
    public static void sendKeyValueUsingJS(WebDriver driver, WebElement element, String input) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1]", element, input);
    }
    //JS click
    public static void clickUsingJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    //executor.
    public static void clickMEventUsingJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click'))", element);
    }

    //JS Highlight WebElement
    public static void highLightELement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;')", element);
    }
/*
    Methods to zoom in and out pages
 */

    // Zoom Out (e.g. 80%)
    public static void zoomOut(WebDriver driver, int percentage) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + percentage + "%'");
    }

    // Zoom In (e.g. 120%)
    public static void zoomIn(WebDriver driver, int percentage) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + percentage + "%'");
    }

    // Reset Zoom to default (100%)
    public static void resetZoom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='100%'");
    }
    //***********************************************************************
    //Select drop-down value by VisibleText
    public static void selectByVisibleText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    //Select drop-down value by Index
    public static void selectByIndex(WebElement element, int value) {
        Select select = new Select(element);
        select.selectByIndex(value);
    }

    //Select drop-down value by Value
    public static void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }


    //switch to child window
    public static WebDriver switchToChildWindow(WebDriver driver) {
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String childWindow : windows) {
            if (!parentWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);

            }
        }
        return driver;
    }
    //switch to Parent window
    public static WebDriver switchToParentWindow(WebDriver driver) {
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        return driver;
    }

    public static void waitUrlToBe(WebDriver driver, String url, Integer waitSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        try{
            wait.until(ExpectedConditions.urlToBe(url));
        }
        catch(Exception e) {
            System.out.println("URL is not verified : "+url);
        }

    }

    //explict wait to check URL contains
    public static boolean waitForUrlContains(WebDriver driver, String string, Integer waitSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        try{
            wait.until(ExpectedConditions.urlContains(string));
        }
        catch(Exception e) {
            System.out.println("URL is not verified : "+string);
        }

        return true;
    }
    public static boolean waitForUrlContains1(WebDriver driver,
                                             String expectedUrlPart,
                                             Integer waitSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));

        try {
            wait.until(ExpectedConditions.urlContains(expectedUrlPart));
            return true;   // URL matched
        }
        catch (TimeoutException e) {
            System.out.println("URL is not verified. Expected to contain: "
                    + expectedUrlPart + " | Actual URL: " + driver.getCurrentUrl());
            return false;  // URL not matched
        }
    }

    //explicit wait to be clickable
    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, Integer waitSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch(Exception e) {
            System.out.println("Element is not clickable : "+element);
        }

    }

    //Explicit wait for Element visibility

    public static boolean waitForPresenceOfElementLocated(WebDriver driver, By locator, int waitSeconds) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;

        } catch (TimeoutException e) {
            System.out.println("Element not present within timeout: " + locator);
        } catch (Exception e) {
            System.out.println("Unexpected error while waiting for element: " + e.getMessage());
        }

        return false;
    }


    //Explicit wait for Element visibility
    public static void waitForVisibilityOFElement(WebDriver driver, WebElement element, Integer waitSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch(Exception e) {
            System.out.println("Element is not Visible : "+element);
        }
    }
    /**
     * Waits until the element is visible on the page
     *
     * @param driver WebDriver instance
     * @param element WebElement to wait for
     * @param timeoutInSeconds max wait time
     * @return true if element is visible, false otherwise
     */
    public static boolean waitForVisibilityOFElement(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));

            return element.isDisplayed();

        } catch (Exception e) {
            System.out.println("Element not visible within " + timeoutInSeconds + " seconds");
            return false;
        }
    }
    //Optionally, you can wait for the overlay to disappear
    public static void waitForInvisibilityOfElement(WebDriver driver, WebElement element, Integer waitSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        try{
            wait.until(ExpectedConditions.invisibilityOf(element));
        }
        catch(Exception e) {
            System.out.println("Element is not clickable : "+element);
        }

    }
    //Explicit wait for Element is Selected
    public static void waitForElementToBeSelected(WebDriver driver, WebElement element, Integer waitSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        try{
            wait.until(ExpectedConditions.elementSelectionStateToBe(element,true));
        }
        catch(Exception e) {
            System.out.println("Element is not Selected : "+element);
        }
    }
    // method for date
    public static String genrateSystemDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String timestamp = sdf.format(new Date());
        //String newFileName = "payment_file_" + timestamp + ".xlsx";
        return timestamp;
    }
    // Method to generate a random number
    public static int generateRandomNumber(int maxRange) {
        Random random = new Random();
        return random.nextInt(maxRange);
    }

    // Method to generate a random email using the random number
    public static String randomEmailLogin() {
        String randomNumber = genrateSystemDate(); // You can adjust the range as needed
        return "test6011+admin" + randomNumber + "@gmail.com";
    }

    public static String randomEmailToReg() {
        String timestamp  = genrateSystemDate(); // You can adjust the range as needed
        return "test+user" + timestamp + "@gmail.com";
    }
    public static String generateMobileNumber() {
        String prefix = "9"; // ensures 10-digit Indian number starting with 9
        long randomNumber = (long)(Math.random() * 1_000_000_000L);

        return prefix + String.format("%09d", randomNumber);
    }

    /**
     * Introduces a delay (sleep) in milliseconds.
     */
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Handle or log the exception as needed
        }
    }

    public static void uploadFile(String path) {

//		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
//		        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        // Wait for the file chooser dialog to appear
        try {
            Thread.sleep(2000); // Adjust as necessary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Copy the file path to clipboard
        copyFilePathToClipboard(path);

        // Paste the file path from clipboard using Robot class
        pasteFilePathWithRobot();
    }

    private static void copyFilePathToClipboard(String filePath) {
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
    private static void pasteFilePathWithRobot() {
        try {
            Robot robot = new Robot();

            // Press and release CTRL+V to paste the file path from clipboard
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Press and release ENTER to confirm the file selection
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public static void performEscape() {
        try {
            Robot robot = new Robot();

            // Press and release the Escape key
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    //close browsers current window
    public static void closeWindow(WebDriver driver) {
        if(driver != null) {
            driver.close();
        }
    }
    //click on browsers back button
    public static void clickOnBack(WebDriver driver) {
        if(driver != null) {
            driver.navigate().back();
        }
    }

    //method to Delete content or file from Directory
    public static void deleteingFiles() {
        // Specify the directory path
        String directoryPath = System.getProperty("user.dir") + "\\paymentFiles";

        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Get a list of files and subdirectories within the directory
        File[] files = directory.listFiles();

        if(files != null) {
            // Loop through each file and delete it
            for(File file : files) {
                if(file.isFile()) {
                    file.delete(); // Delete the file
                }
            }
        }
    }
// Some Common Methods for WEB APP
public static void selectByVisibleTextT(WebElement element, String value, String fieldName,WebDriver driver, Integer waitSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        try {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Select(element).selectByVisibleText(value);
    } catch (Exception e) {
        throw new RuntimeException(
                "Failed to select value '" + value + "' for field: " + fieldName, e);
    }
}
// Method for path access
    public static String getFilePath(String relativePath) {
        return System.getProperty("user.dir") + File.separator + relativePath;
   }

    //Method for Random Mobile number:

    public static String generateRandomMobileNumber() {

        Random random = new Random();

        // First digit between 6–9
        int firstDigit = 6 + random.nextInt(4);

        StringBuilder mobile = new StringBuilder();
        mobile.append(firstDigit);

        // Remaining 9 digits
        for (int i = 0; i < 9; i++) {
            mobile.append(random.nextInt(10));
        }

        return mobile.toString();
    }

    public static String generateDummyMobileNumber() {
        try {
            Random random = new Random();

            // Reserved dummy prefix (non-real)
            String[] dummyPrefixes = { "60000", "44444", "33333" };
            String prefix = dummyPrefixes[random.nextInt(dummyPrefixes.length)];

            StringBuilder mobile = new StringBuilder(prefix);

            // Generate remaining digits to make 10 digits
            int remainingDigits = 10 - prefix.length();
            for (int i = 0; i < remainingDigits; i++) {
                mobile.append(random.nextInt(10));
            }

            return mobile.toString();

        } catch (Exception e) {
            System.out.println("Error generating dummy mobile number: " + e.getMessage());
            return "9000000000"; // Safe fallback
        }
    }

    //Method for Handling Alert
    public static String handleAlertIfPresent(WebDriver driver, int timeout) {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.alertIsPresent());

            String text = alert.getText();
            alert.accept();

            return text;

        } catch (TimeoutException e) {
            return null;
        }
    }

  /*  public static boolean isIncognitoMode(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            Object result = js.executeScript(
                    "return new Promise(resolve => {" +
                            "  let fs = window.RequestFileSystem || window.webkitRequestFileSystem;" +
                            "  if (!fs) resolve(false);" +
                            "  fs(window.TEMPORARY, 100, () => resolve(false), () => resolve(true));" +
                            "});"
            );

            return Boolean.parseBoolean(result.toString());

        } catch (Exception e) {
            return false;
        }
    }*/

    /*
    Zoom Using CDP
    zoomUsingCDP(driver, 0.8); // 80%
    zoomUsingCDP(driver, 1.0); // reset
     */
    public static void zoomUsingCDP(WebDriver driver, double scaleFactor) {
        try {
            if (!(driver instanceof ChromeDriver)) {
                System.out.println("CDP works only with ChromeDriver");
                return;
            }

            ChromeDriver chromeDriver = (ChromeDriver) driver;
            DevTools devTools = chromeDriver.getDevTools();
            devTools.createSession();

            devTools.send(Emulation.setDeviceMetricsOverride(
                            1366,
                            768,
                            scaleFactor,
                            false,
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty()
                    )
            );

            System.out.println("Zoom applied using CDP");

        } catch (Exception e) {
            System.out.println("CDP zoom failed: " + e.getMessage());
        }
    }

   /* public static void smartZoomOut(WebDriver driver, double scale) {
        try {
            if (isIncognitoMode(driver)) {
                // Incognito → use CDP
                ChromeDriver chromeDriver = (ChromeDriver) driver;

                Map<String, Object> params = new HashMap<>();
                params.put("mobile", false);
                params.put("width", 1366);
                params.put("height", 768);
                params.put("deviceScaleFactor", scale);

                chromeDriver.executeCdpCommand(
                        "Emulation.setDeviceMetricsOverride", params
                );

                System.out.println("Zoom applied using CDP (Incognito)");

            } else {
                // Normal mode → CSS fallback
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                        "document.body.style.transform='scale(" + scale + ")';" +
                                "document.body.style.transformOrigin='0 0';"
                );

                System.out.println("Zoom applied using CSS transform");
            }

        } catch (Exception e) {
            System.out.println("Smart zoom failed: " + e.getMessage());
        }
    }*/

    public static void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }

    /**
     * Opens a new browser tab and switches driver control to it.
     *
     * @param driver WebDriver instance
     * @return String window handle of the parent window
     */
    public static String openNewTabAndSwitch(WebDriver driver) {

        // Store parent window handle
        String parentWindow = driver.getWindowHandle();

        // Open new tab and switch
        driver.switchTo().newWindow(WindowType.TAB);

        return parentWindow;
    }


    public static String openNewTabAndSwitchUsingJs(WebDriver driver) {

        String parentWindow = driver.getWindowHandle();

        // Open new tab using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open();");

        // Switch to newly opened tab
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        return parentWindow;
    }

}
