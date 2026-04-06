package com.bmp.automation.pages;

import com.bmp.automation.base.PropertiesUtil;
import com.bmp.automation.utility.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends PropertiesUtil {

    private WebDriver driver;

    @FindBy(xpath = "//a[text()='Log in / Sign up']")
    private WebElement signUpBtn_Home;

    @FindBy(xpath = "//a[text()='Sign up']")
    private WebElement signupLink;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement full_nameField;

    @FindBy(xpath = "//input[@name='phone_number']")
    private WebElement phone_numberField;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@name='state']")
    private WebElement stateField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement createPassField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPassField;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement i_agree_Checkbox;

    @FindBy(xpath = "//button[text()='Sign Up']")
    private WebElement signUpButton;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void userSignUp(){

        Utils.waitForElementToBeClickable(driver,signUpBtn_Home,10);
        signUpBtn_Home.click();
    }
   /* public boolean userLoginToApp() {
        try {

            String adminEmail = configProp.getProperty("adminEmail");
            String adminPassword = configProp.getProperty("adminPassword");

            // Validation: credentials should not be null or empty
            if (adminEmail == null || adminEmail.isEmpty()
                    || adminPassword == null || adminPassword.isEmpty()) {
                System.out.println("Admin credentials are missing in properties file");
                return false;
            }

            // Wait & enter email
            Utils.waitForVisibilityOFElement(driver,emailField,10);
            emailField.clear();
            emailField.sendKeys(adminEmail);

            // Wait & enter password
            Utils.waitForVisibilityOFElement(driver,passwordField,10);
            passwordField.clear();
            passwordField.sendKeys(adminPassword);

            // Click login button
            Utils.waitForElementToBeClickable(driver,loginButton, 10);
            loginButton.click();
            System.out.println("Clicked on Admin Login button");
            // Utils.waitForUrlContains(driver,"login", 10);
            // Optional: wait for successful login indicator
            // Example: Dashboard heading / logout button
            //Utils.waitForVisibilityOFElement(driver,adminDashboard,5);
            boolean isDashboardPresent = Utils.waitForPresenceOfElementLocated(driver, By.xpath("//a[.//span[normalize-space()='Dashboard'] and not(@aria-hidden='true')]"),
                    15);

            System.out.println("Navigate On Dashboard: " +isDashboardPresent);

            return true;

        } catch (TimeoutException e) {
            System.out.println("Login elements not visible within timeout");
        } catch (NoSuchElementException e) {
            System.out.println("Login element not found: " + e.getMessage());
        } catch (ElementClickInterceptedException e) {
            System.out.println("Login button click intercepted");
        } catch (StaleElementReferenceException e) {
            System.out.println("DOM refreshed during login, retry may be required");
        } catch (Exception e) {
            System.out.println("Unexpected error during admin login: " + e.getMessage());
        }

        return true;
    }*/
}


