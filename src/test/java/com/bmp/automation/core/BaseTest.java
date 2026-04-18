package com.bmp.automation.core;

import com.bmp.automation.base.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    // Utility to read config properties (like URL, credentials, etc.)
    protected static PropertiesUtil propertiesUtil;

    // ThreadLocal to maintain separate WebDriver instance per thread (parallel execution safe)
   // private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static WebDriver driver;

    // Getter method to access driver anywhere in framework
    public static WebDriver getDriver() {

        return driver; //.get();  // returns driver specific to current thread
    }

    // Setter method to assign driver to current thread
    public static void setDriver(WebDriver driverInstance) {

       // driver.set(driverInstance); // for future ref parallel execution
        driver = driverInstance;
    }

    // Removes driver from ThreadLocal (important to avoid memory leaks)
//    public static void unload() {
//
//        driver.remove();
//    }

    /**
     * This method runs BEFORE every test method
     * It initializes browser based on parameter and sets up environment
     */
    // ================================
    // BEFORE CLASS → E2E FLOW START
    // ================================
    @BeforeSuite(alwaysRun = true)
    @Parameters("browser")  // Browser value comes from TestNG XML
    public void setup(@Optional("chrome") String browser) {

        WebDriver driverInstance = null;
        // Set thread ID for logging
        ThreadContext.put("threadId", String.valueOf(Thread.currentThread().getId()));
        // ================================
        // Parallel Execution Logging
        // ================================
        System.out.println("======================================");
        System.out.println("Thread ID: " + Thread.currentThread().getId());
        System.out.println("Executing on Browser: " + browser);
        System.out.println("======================================");

        // Setup ChromeDriver automatically using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Configure browser options for stability and performance
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");  // Fix for Chrome 111+ issue
        options.addArguments("--disable-dev-shm-usage");   // Overcome limited resource problems
        options.addArguments("--no-sandbox");              // Required for Linux/Docker
        options.addArguments("--disable-notifications");   // Disable browser notifications
        options.addArguments("--disable-geolocation");
        options.addArguments("--disable-extensions");      // Disable extensions
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.geolocation", 2);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");               // Open in incognito mode

        // Read headless mode from system property (default = false)
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // If headless mode is enabled
        if (headless) {
            options.addArguments("--headless=new");        // Run browser without UI
            options.addArguments("--window-size=1920,1080"); // Set screen size
        }

        // ================================
        // Multi-Browser Handling
        // ================================
        if (browser.equalsIgnoreCase("chrome")) {
            driverInstance = new ChromeDriver(options);
        }

        // Future: Add Firefox / Edge here
        // else if (browser.equalsIgnoreCase("firefox")) { ... }

        // Store driver instance in ThreadLocal
        setDriver(driverInstance);

        // Maximize window (only if not headless)
        if (!headless) {
            getDriver().manage().window().maximize();
        }

        // Delete all cookies to start fresh session
        getDriver().manage().deleteAllCookies();

        // Load properties file only once
        if (propertiesUtil == null) {
            propertiesUtil = new PropertiesUtil();
        }

        // Launch application URL from config file
        getDriver().get(propertiesUtil.getConfig("user.login.url"));
    }

    /**
     * This method runs AFTER every test method
     * It closes browser and cleans ThreadLocal
     */

    @AfterSuite(alwaysRun = true)
    public void teardown() {

        // Log thread info during teardown
        //System.out.println("Closing Browser - Thread ID: " + Thread.currentThread().getId());

        // Quit browser if driver exists
//        if (getDriver() != null) {
//            //getDriver().quit();  // Close browser
//            unload();            // Remove driver from ThreadLocal
//        }
    }
}