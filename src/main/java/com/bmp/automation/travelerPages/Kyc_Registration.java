package com.bmp.automation.travelerPages;

import com.bmp.automation.pages.Login_Page;
import com.bmp.automation.utility.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kyc_Registration {

    private WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(Kyc_Registration.class);

    // ================================
    // Locators
    // ================================
    @FindBy(xpath = "//input[@placeholder='ABCDE1234F']")
    private WebElement panNumber;

    @FindBy(xpath = "//input[@placeholder='Enter your full name']")
    private WebElement fullNameField;

    @FindBy(xpath = "//input[@name='dob']")
    private WebElement dateofbirthField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement verifyPanDetails;

    @FindBy(xpath ="//*[@id=\"root\"]/div/div/div[3]/button")
    private WebElement continueButton;


    // ================================
    // Constructor
    // ================================
    public Kyc_Registration(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void setVerifyPanDetails(){
        Utils.waitForVisibilityOFElement(driver, panNumber,10);
        panNumber.sendKeys("ATNPT1052B");
        Utils.waitForInvisibilityOfElement(driver,fullNameField,10);
        fullNameField.sendKeys("Abhijeet Kulkarni");
        dateofbirthField.sendKeys("01/01/2000");
        Utils.waitForVisibilityOFElement(driver,verifyPanDetails,5);
        verifyPanDetails.sendKeys(Keys.ENTER);
        Utils.waitForElementToBeClickable(driver,continueButton,10);
        continueButton.click();
    }

}
