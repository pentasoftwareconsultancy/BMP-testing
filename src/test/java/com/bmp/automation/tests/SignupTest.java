package com.bmp.automation.tests;

import com.aventstack.extentreports.model.Log;
import com.bmp.automation.core.BaseTest;
import com.bmp.automation.pages.SignUpPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SignupTest extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(SignupTest.class);

    @Test(description = "Verify signup flow")
    public void verifySignup() {

        log.info("Starting Signup Test");

        SoftAssert softAssert = new SoftAssert();  // Initialize

        SignUpPage page = new SignUpPage(getDriver());

        boolean result = page.completeSignup();

        log.info("Signup action completed");

        // ================================
        // Soft Assertions
        // ================================
        softAssert.assertTrue(result, "Signup flow failed");

        log.info("Signup validations completed");

        // ================================
        // VERY IMPORTANT
        // ================================
        softAssert.assertAll();  // Without this, test will always PASS

        log.info("Signup Test Completed Successfully");
    }
}
