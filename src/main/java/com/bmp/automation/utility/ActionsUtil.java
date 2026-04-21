package com.bmp.automation.utility;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {
    /*
     * Declaring WebDriver, Action ref
     */
    private WebDriver driver;
    private final Actions actions;

    public ActionsUtil(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void hoverOverElement(WebElement element) {

        actions.moveToElement(element).perform();
    }

    public void clickOnElement(WebElement element) {

        actions.click(element).build().perform();
    }

    public void sendKeysToElement(WebElement element, String input) {
        actions.sendKeys(element, input).build().perform();
    }

    public void selectAndCutText(WebElement element) {
        actions.click(element)
                .keyDown(Keys.CONTROL).sendKeys("a", "x").keyUp(Keys.CONTROL)
                .build().perform();
    }

    public void pressEnter(WebElement element) {

        actions.sendKeys(element, Keys.ENTER).build().perform();
    }

    public void pressEnter() {

        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void pressEscape(WebElement element) {
        actions.sendKeys(element, Keys.ESCAPE).perform();
    }

    public void pressTab(WebElement element) {
        actions.sendKeys(element, Keys.TAB).perform();
    }

    public void arrowDownNTimes(WebElement element, int times) {
        for (int i = 0; i < times; i++) {
            actions.sendKeys(element, Keys.ARROW_DOWN);
        }
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void openNewTab() {
        actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
    }

    public void backspace() {
        actions.sendKeys(Keys.BACK_SPACE).build().perform();
    }

    public void selectDropdownValue(WebElement element, String value, String fieldName) {

        try {
            // Click dropdown to focus
            element.click();
            /*
             * Assumption:
             * Dropdown options are ordered
             * 'value' represents the option position (index starting from 1)
             */

            int downCount = Integer.parseInt(value);

            for (int i = 0; i < downCount; i++) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
            }

            actions.sendKeys(Keys.ENTER).perform();

            System.out.println(fieldName + " selected using keyboard actions");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to select value for field: " + fieldName, e
            );
        }
    }
    /*
    Browser-level Zoom (MOST ACCURATE)
    This simulates CTRL + – (real user behavior)
     */
    public void zoomOutUsingKeys(WebDriver driver, int times) {
        try {
            //Actions actions = new Actions(driver);
            for (int i = 0; i < times; i++) {
                actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
            }
        } catch (Exception e) {
            System.out.println("Keyboard zoom out failed: " + e.getMessage());
        }
    }

    // 🔍 Zoom IN using keyboard (CTRL + +)
    public void zoomInUsingKeys(int times) {
        try {
            //Actions actions = new Actions(driver);

            for (int i = 0; i < times; i++) {
                actions.keyDown(Keys.CONTROL)
                        .sendKeys(Keys.ADD)
                        .keyUp(Keys.CONTROL)
                        .perform();
            }

        } catch (Exception e) {
            System.out.println("Zoom in failed: " + e.getMessage());
        }
    }

    // 🔍 Optional: Reset zoom to 100%
    public void resetZoom() {
        try {

            actions.keyDown(Keys.CONTROL)
                    .sendKeys("0")
                    .keyUp(Keys.CONTROL)
                    .perform();
        } catch (Exception e) {
            System.out.println("Reset zoom failed: " + e.getMessage());
        }
    }



}



