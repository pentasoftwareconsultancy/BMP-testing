package com.bmp.automation.tests;

import com.bmp.automation.core.BaseTest;
import com.bmp.automation.pages.Login_Page;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
//import org.slf4j.LoggerFactory;
//import org.slf4j.LoggerFactory;
//import java.lang.System.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginTest  extends BaseTest {
   // private static final System.Logger log = LoggerFactory.getLogger(LoginTest.class);
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);


    @Test(description="Verify login flow")
    public void verifyloginflow() throws InterruptedException {
        Login_Page lp=new Login_Page(getDriver());
        // lp.completelogin();
        lp.completeLogin();
    }

    // ================================
    //  Assertions
    // ================================

    //Assert.assertEquals()

}