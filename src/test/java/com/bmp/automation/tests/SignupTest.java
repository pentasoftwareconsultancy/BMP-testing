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

        page.completeSignup("abhi", "8668217696","abc@gmail.com", "Pune", "Maharashtra","Abhi@003");

        log.info("Signup action completed");

        // ================================
        // Soft Assertions
        // ================================

        // Validate signup success
        boolean isSuccess = page.isSignupSuccessful();
        softAssert.assertTrue(
                isSuccess,
                "Signup failed - Success message not displayed"
        );

        // Validate success message text
        String actualMsg = page.getSuccessMessage();
        String expectedMsg = "Signup successful";

        softAssert.assertEquals(
                actualMsg,
                expectedMsg,
                "Signup success message mismatch"
        );

        log.info("Signup validations completed");

        // ================================
        // VERY IMPORTANT
        // ================================
        softAssert.assertAll();  // Without this, test will always PASS

        log.info("Signup Test Completed Successfully");
    }
}
