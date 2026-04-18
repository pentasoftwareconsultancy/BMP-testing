package com.bmp.automation.travelerPages;

import com.bmp.automation.pages.Login_Page;
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
    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@name='dob']")
    private WebElement dateofbirthField;

    @FindBy(xpath = "//select[@name='gender']")
    private WebElement selectGender;

    @FindBy(xpath = "//input[@placeholder='Enter address (optional)']")
    private WebElement addressField;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@placeholder='Aadhar card number']")
    private WebElement adharcardnumberField;

    @FindBy(xpath = "//input[@placeholder='Pan card number']")
    private WebElement pancardnumberField;

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


}
