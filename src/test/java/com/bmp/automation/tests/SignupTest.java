package com.bmp.automation.tests;

import com.bmp.automation.core.BaseTest;import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SignupTest extends BaseTest{

    WebDriver driver;

    @BeforeMethod
    public void init() {
        driver = BaseTest.getDriver();
    }

    @Test
    public void testSignup() {

        System.out.println("testSignup");
    }
}
