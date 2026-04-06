package com.bmp.automation.core;


import com.bmp.automation.base.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
;

public class BaseTest {

    @Getter
    protected static WebDriver driver;   //  MUST be static
    protected static PropertiesUtil propertiesUtil;

    @BeforeSuite(alwaysRun = true)
    public void setupDriver() {

        if (driver != null) {
            return; //  Prevent re-initialization
        }

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // ===== Common & Stability Arguments =====
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--incognito");

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);

        if (!headless) {
            driver.manage().window().maximize();
        }

        driver.manage().deleteAllCookies();
    }

    @BeforeClass
    protected void launchApp() {
        if (propertiesUtil == null) {
            propertiesUtil = new PropertiesUtil();
        }
        driver.get(propertiesUtil.getConfig("user.login.url"));
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            //driver.quit();   //  MUST quit
            // driver = null;
        }
    }


}
