package com.bmp.automation.pages;

import com.bmp.automation.base.PropertiesUtil;

import com.bmp.automation.utility.ActionsUtil;
import com.bmp.automation.utility.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SignUpPage extends PropertiesUtil {

    private WebDriver driver;
    private ActionsUtil actionsUtil;
    private PropertiesUtil testDataProp;
    // Logger
    private static final Logger log = LoggerFactory.getLogger(SignUpPage.class);

    // ================================
    // Constructor
    // ================================
    public SignUpPage(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }

        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.actionsUtil = new ActionsUtil(driver);
        this.testDataProp = new PropertiesUtil();
    }

    // ================================
    // Locators
    // ================================

    @FindBy(xpath = "//a[text()='Log in / Sign up']")
    private WebElement signUpBtn_Home;

    @FindBy(xpath = "//a[text()='Sign up']")
    private WebElement signupLink;

    @FindBy(id = "name")
    private WebElement full_nameField;

    @FindBy(name = "phone_number")
    private WebElement phone_numberField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "city")
    private WebElement cityField;

    @FindBy(name = "state")
    private WebElement stateField;

    @FindBy(name = "password")
    private WebElement createPassField;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPassField;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement i_agree_Checkbox;

    @FindBy(xpath = "//button[text()='Create Account']")
    private WebElement signUpButton;

    // Example success message (update locator based on your app)
    @FindBy(xpath = "//div[contains(text(),'successfully')]")
    private WebElement successMessage;

    // ================================
    // Actions
    // ================================

    public void clickSignupFromHome() {
        log.info("Clicking on 'Login / Sign Up' button");
        Utils.waitForElementToBeClickable(driver, signUpBtn_Home, 10);
        signUpBtn_Home.click();
    }

    public void clickSignupLink() {
        log.info("Clicking on Signup link");
        Utils.waitForElementToBeClickable(driver, signupLink, 10);
        signupLink.click();
    }

    public void enterFullName(String name) {
        log.info("Entering Full Name: {}", name);
        Utils.waitForVisibilityOFElement(driver, full_nameField, 10);
        full_nameField.clear();
        full_nameField.sendKeys(name);
    }

    public void enterPhone(String phone) {
        log.info("Entering Phone: {}", phone);
        phone_numberField.clear();
        phone_numberField.sendKeys(phone);
    }

    public void enterEmail(String email) {
        log.info("Entering Email: {}", email);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterCity(String city) {
        log.info("Entering City: {}", city);
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void enterState(String state) {
        log.info("Entering State: {" +
                "}", state);
        stateField.clear();
        stateField.sendKeys(state);
    }

    public void enterPassword(String pass) {
        log.info("Entering Password");
        createPassField.clear();
        createPassField.sendKeys(pass);
    }

    public void enterConfirmPassword(String pass) {
        log.info("Entering Confirm Password");
        confirmPassField.clear();
        confirmPassField.sendKeys(pass);
    }

    public void acceptTerms() {
        log.info("Clicking Terms & Conditions checkbox");
        if (!i_agree_Checkbox.isSelected()) {
            i_agree_Checkbox.click();
        }
    }

    public void clickSignupButton() {
        log.info("Clicking Signup button");
        signUpButton.click();
    }

    // ================================
    // Business Flow (IMPORTANT )
    // ================================

    public boolean completeSignup() {

        log.info("===== Starting Signup Flow =====");

        String name = testDataProp.getTestData("signup.name");
        //String phone = testDataProp.getTestData("signup.phone");
        //String email = testDataProp.getTestData("signup.email");
        String city = testDataProp.getTestData("signup.city");
        String state = testDataProp.getTestData("signup.state");
        String pass = testDataProp.getTestData("signup.password");

        Utils.waitForElementToBeClickable(driver, signUpBtn_Home, 10);
        signUpBtn_Home.click();
        Utils.waitForElementToBeClickable(driver, signupLink, 10);
        signupLink.click();

        Utils.waitForVisibilityOFElement(driver, full_nameField, 10);
        full_nameField.sendKeys(name);

        phone_numberField.sendKeys(Utils.generateMobileNumber());
        emailField.sendKeys(Utils.randomEmailToReg());
        cityField.sendKeys(city);
        stateField.sendKeys(state);

        createPassField.sendKeys(pass);
        confirmPassField.sendKeys(pass);

        if (!i_agree_Checkbox.isSelected()) {
            i_agree_Checkbox.click();
        }

        signUpButton.click();

        log.info("Signup submitted, checking for alert...");

        // ================================
        // ALERT HANDLING
        // ================================
        String alertText = Utils.handleAlertIfPresent(driver, 5);

        if (alertText != null) {
            log.info("Alert detected: {" +
                    "}", alertText);

            if (alertText.toLowerCase().contains("success")) {
                log.info("Signup SUCCESS via alert");
                return true;
            } else {
                log.error("Signup FAILED via alert: {" +
                        "}", alertText);
                return false;
            }
        }
            // ================================
            // FALLBACK UI VALIDATION
            // ================================
            try {
                boolean isSuccess = Utils.waitForVisibilityOFElement(driver, successMessage, 10);

                if (isSuccess) {
                    log.info("Signup SUCCESS via UI message");
                    return true;
                } else {
                    log.error("Signup FAILED - No alert or success message");
                    return false;
                }

            } catch (Exception ex) {
                log.error("Validation failed due to exception", ex);
                return false;
            }
        }
    }



