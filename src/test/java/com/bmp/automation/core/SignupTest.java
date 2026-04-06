package com.bmp.automation.core;

import com.bmp.automation.pages.SignUpPage;
import org.testng.annotations.Test;


public class SignupTest extends BaseTest{

    @Test
    public void signupTest(){
        SignUpPage signupPage = new SignUpPage(driver);
        signupPage.userSignUp();
    }
}
