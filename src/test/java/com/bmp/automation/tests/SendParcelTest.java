package com.bmp.automation.tests;

import com.bmp.automation.core.BaseTest;
import com.bmp.automation.pages.SendParcelPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class SendParcelTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(SendParcelTest.class);

    @Test(description="Verify send parcel")
    public void verifysendparcel()

    {
        SendParcelPage sparcel=new SendParcelPage(getDriver());
        sparcel.completemethodssendparcel();
    }

}
