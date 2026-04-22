package com.bmp.automation.tests;

import com.bmp.automation.core.BaseTest;
import com.bmp.automation.travelerPages.Kyc_Registration;
import org.testng.annotations.Test;

public class KycTest extends BaseTest {

    @Test(description="Verify Kyc flow")
    public void KycTest() {
        Kyc_Registration kyc = new Kyc_Registration(getDriver());

        kyc.setVerifyPanDetails();
    }
}
