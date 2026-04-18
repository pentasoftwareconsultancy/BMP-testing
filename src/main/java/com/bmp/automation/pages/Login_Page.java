package com.bmp.automation.pages;

import com.bmp.automation.base.PropertiesUtil;
import com.bmp.automation.utility.ActionsUtil;
import com.bmp.automation.utility.Utils;
import jdk.jshell.execution.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login_Page extends PropertiesUtil {


    private WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(Login_Page.class);


    // ================================
    // Locators
    // ================================
    @FindBy(xpath = "//*[text()='Log in / Sign up']")
    private WebElement loginpagetab;
    @FindBy(xpath = "//*[@id='email']")
    private WebElement email_Id;
    @FindBy(xpath = "//*[@type='password']")
    private WebElement password;
    @FindBy(xpath = "//*[@type='checkbox']")
    private WebElement remember_Checkbox;
    @FindBy(xpath = "//*[text()='Login']")
    private WebElement login_Button;

    public Login_Page(WebDriver driver){
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enter_email()
    {
        Utils.waitForElementToBeClickable(driver,loginpagetab,10);
        loginpagetab.click();
        Utils.waitForVisibilityOFElement(driver,email_Id,10);
        email_Id.sendKeys("jayvbankar@gmail.com");
    }

    public void enter_password()
    {
        password.sendKeys("Maya@11477");
    }
    public void click_rememberme() {
        remember_Checkbox.click();
    }

    public void clickOnLogin(){
        login_Button.click();
    }

    public void completeLogin() throws InterruptedException {

        enter_email();
        enter_password();
        click_rememberme();
        clickOnLogin();
        log.info("Login successfully");
        log.info("Login");
        Thread.sleep(1000);
    }

}
