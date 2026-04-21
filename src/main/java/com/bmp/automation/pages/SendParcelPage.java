package com.bmp.automation.pages;

import com.bmp.automation.base.PropertiesUtil;
import com.bmp.automation.utility.ActionsUtil;
import com.bmp.automation.utility.Utils;
import jdk.jshell.execution.Util;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class SendParcelPage extends PropertiesUtil {


    private WebDriver driver;
    private ActionsUtil actionsUtil;
    private PropertiesUtil testDataProp;


    // ================================
    // Locators for Pickup Location
    // ================================

    @FindBy(xpath = "//div[contains(@class,'bmp-group')]//div//button[contains(text(),'Send Parcel')]")

    private WebElement sendParcelBtn;

    @FindBy(xpath = "//input[@id='senderName']")
    private WebElement senderNameField;

    @FindBy(xpath = "//input[@placeholder='Enter pickup address']")
    private WebElement pickupAddField;


    @FindBy(xpath = "//input[@id='pickupCity']")
    private WebElement pickupCityField;

    @FindBy(xpath = "//input[@id='pickupState']")
    private WebElement pickupStateField;

    @FindBy(xpath = "//input[@id='pickupPincode']")
    private WebElement pickupPincodeField;

    @FindBy(xpath = "//input[@id='pickupCountry']")
    private WebElement pickupCountryField;


    @FindBy(xpath = "//input[@id='pickupPhone']")
    private WebElement pickupPhoneField;

    @FindBy(xpath = "//input[@id='pickupAltPhone']")
    private WebElement pickupAltPhoneField;

    @FindBy(xpath = "//input[@id='pickupAadhaar']")
    private WebElement pickupAadharField;

    // ================================
    // Locators for Package Details/Package Size
    // ================================

   @FindBy(xpath = "//p[normalize-space()='Documents, letters']")
   private WebElement smallsize;
   @FindBy(xpath="//p[normalize-space()='1 - 5 kg']")
   private WebElement mediumsize;
   @FindBy(xpath = "//*[text()='Electronics, shoes']")
   private WebElement largesize;
   @FindBy(xpath = "(//*[@type='button'])[4]")
   private WebElement extralargesize;

// ================================
// Locators for Package Details/Parcel Details
// ================================
@FindBy(xpath = "//*[@id='parcelLength']")
private WebElement parcellenght;
@FindBy(xpath = "//*[@id='parcelWidth']")
private WebElement parcelwidth;
@FindBy(xpath = "//*[@id='parcelHeight']")
private WebElement parcelHeight;

@FindBy(xpath = "//*[@id='parcelContents']")
private WebElement parcelcontent;
@FindBy(xpath = "//span[normalize-space()='Parcel photo 1']")
private WebElement parcelphoto;
@FindBy(xpath = "//input[@id='parcelValue']")
private WebElement enterparcelvalue;

    @FindBy(xpath = "//input[@id='parcelType']")
    private WebElement enterparceltype;

    @FindBy(xpath = "//input[@id='parcelNotes']")
    private WebElement enterparcelnote;
// ================================
// Locators for Delivery Details
// ================================

    @FindBy(xpath = "//input[@id='receiverName']")
    private WebElement enterreceivername;
    @FindBy(xpath = "//input[@placeholder='Enter delivery address']")
    private WebElement enterdeliveryadd;
    @FindBy(xpath = "//input[@id='deliveryCity']")
    private WebElement enterdeliverycity;

    @FindBy(xpath = "//input[@id='deliveryState']")
    private WebElement enterrecdeliverystate;

    @FindBy(xpath = "//input[@id='deliveryPincode']")
    private WebElement enterrecpincode;
    @FindBy(xpath = "//input[@id='deliveryCountry']")
    private WebElement enterdelcountry;
    @FindBy(xpath = "//input[@id='deliveryPhNo']")
    private WebElement enterdelphone;
    @FindBy(xpath = "//input[@id='deliveryAlternatePhNo']")
    private WebElement enteraltdelphone;

    @FindBy(xpath = "//button[@type='submit']")
    private  WebElement nextselectpartner;

    public void pickuplocation() {

        try {
            // Click Send Parcel button
            Utils.waitForElementToBeClickable(driver, sendParcelBtn, 10);
            sendParcelBtn.click();

            // Sender Name
            Utils.waitForVisibilityOFElement(driver, senderNameField, 10);
            senderNameField.clear();
            senderNameField.sendKeys("Jayshri");

            // Address
            Utils.waitForVisibilityOFElement(driver, pickupAddField, 10);
            pickupAddField.clear();
            pickupAddField.sendKeys("Pimple Saudagar");

            // City (auto-suggestion handling)
            Utils.waitForVisibilityOFElement(driver, pickupCityField, 10);
            pickupCityField.clear();
            pickupCityField.sendKeys("Pune");
            Thread.sleep(1000);
            pickupCityField.sendKeys(Keys.ARROW_DOWN);
            pickupCityField.sendKeys(Keys.ENTER);

            // State (auto-suggestion handling)
            Utils.waitForVisibilityOFElement(driver, pickupStateField, 10);
            pickupStateField.clear();
            pickupStateField.sendKeys("Maharashtra");
            Thread.sleep(1000);
            pickupStateField.sendKeys(Keys.ARROW_DOWN);
            pickupStateField.sendKeys(Keys.ENTER);

            // Pincode (slow typing to avoid clearing issue)
            Utils.waitForVisibilityOFElement(driver, pickupPincodeField, 10);
            pickupPincodeField.clear();

            String pincode = "411027";
            for (char c : pincode.toCharArray()) {
                pickupPincodeField.sendKeys(String.valueOf(c));
                Thread.sleep(200);
            }

            // Trigger blur event
            pickupPincodeField.sendKeys(Keys.TAB);

            // Country
            Utils.waitForVisibilityOFElement(driver, pickupCountryField, 5);
            pickupCountryField.clear();
            pickupCountryField.sendKeys("India");

            // Phone Numbers
            pickupPhoneField.clear();
            pickupPhoneField.sendKeys("9922554477");

            pickupAltPhoneField.clear();
            pickupAltPhoneField.sendKeys("8899664455");

            // Aadhaar
            pickupAadharField.clear();
            pickupAadharField.sendKeys("123456789456");

            System.out.println("Pickup location filled successfully");

        } catch (Exception e) {

            System.out.println("Error in pickup location: " + e.getMessage());
            e.printStackTrace();

            throw new RuntimeException("Pickup location failed", e);
        }
    }
    public void selectPackage(String size) {

        switch (size.toLowerCase()) {

            case "small":
                smallsize.click();
                System.out.println("Selected Small package (0-1 kg)");
                break;

            case "medium":
               mediumsize.click();
                System.out.println("Selected Medium package (1-5 kg)");
                break;

            case "large":
                largesize.click();
                System.out.println("Selected Large package (5-10 kg)");
                break;

            case "extra large":
                extralargesize.click();
                System.out.println("Selected Extra Large package (10-20 kg)");
                break;

            default:
                System.out.println("Invalid package size");
        }


    }

    public void fillParcelDetails(String parcelLengthValue,
                                  String parcelWidthValue,
                                  String parcelHeightValue) {

        try {
            // Wait for Length field and enter value
            Utils.waitForVisibilityOFElement(driver, parcellenght, 10);
            parcellenght.clear(); // clear existing value
            parcellenght.sendKeys(parcelLengthValue);

            // Wait for Width field and enter value
            Utils.waitForVisibilityOFElement(driver, parcelwidth, 10);
            parcelwidth.clear();
            parcelwidth.sendKeys(parcelWidthValue);

            // Wait for Height field and enter value
            Utils.waitForVisibilityOFElement(driver, parcelHeight, 10);
            parcelHeight.clear();
            parcelHeight.sendKeys(parcelHeightValue);

            // Optional: Trigger blur (in case UI needs validation)
            parcelHeight.sendKeys(Keys.TAB);

            // Log success
            System.out.println("Parcel dimensions entered successfully");

        } catch (Exception e) {

            // Log error
            System.out.println("Error while entering parcel details: " + e.getMessage());
            e.printStackTrace();

            // Fail test explicitly
            throw new RuntimeException("Parcel details entry failed", e);
        }
    }
    public void parcelphotoapp()
    {
        Utils.waitForVisibilityOFElement(driver,parcelcontent,5);
        parcelcontent.sendKeys("parcelcontent");

        Utils.waitForElementToBeClickable(driver,parcelphoto,10);
        parcelphoto.click();
        //String photopath="C:\\Users\\OM\\BMP_Automation\\BMP-testing\\photos\\download.jpg";
        Utils.uploadFile("C:\\Users\\OM\\BMP_Automation\\BMP-testing\\photos\\download.jpg");

        enterparcelvalue.sendKeys("1000");
        enterparceltype.sendKeys("package");
        enterparcelnote.sendKeys("Books");

    }
    public void enterdeliverydetails() {

        try {
            // Receiver Name
            Utils.waitForVisibilityOFElement(driver, enterreceivername, 10);
            enterreceivername.clear();
            enterreceivername.sendKeys("Krishna");

            // Delivery Address (auto-suggestion handling)
            Utils.waitForElementToBeClickable(driver, enterdeliveryadd, 10);
            enterdeliveryadd.clear();
            enterdeliveryadd.sendKeys("Hadapsar");

            Thread.sleep(1500); // wait for suggestions
            enterdeliveryadd.sendKeys(Keys.ARROW_DOWN);
            enterdeliveryadd.sendKeys(Keys.ENTER);

            // City
            Utils.waitForVisibilityOFElement(driver, enterdeliverycity, 10);
            enterdeliverycity.clear();
            enterdeliverycity.sendKeys("Pune");

            Thread.sleep(1000);
            enterdeliverycity.sendKeys(Keys.ARROW_DOWN);
            enterdeliverycity.sendKeys(Keys.ENTER);

            // Pincode (slow typing to avoid clearing)
            Utils.waitForVisibilityOFElement(driver, enterrecpincode, 10);
            enterrecpincode.clear();

            String pincode = "415522";
            for (char c : pincode.toCharArray()) {
                enterrecpincode.sendKeys(String.valueOf(c));
                Thread.sleep(200);
            }

            // Trigger blur event
            enterrecpincode.sendKeys(Keys.TAB);

            // Country
            Utils.waitForVisibilityOFElement(driver, enterdelcountry, 5);
            enterdelcountry.clear();
            enterdelcountry.sendKeys("India");

            // Phone numbers
            enterdelphone.clear();
            enterdelphone.sendKeys("9966335522");

            enteraltdelphone.clear();
            enteraltdelphone.sendKeys("8899664455");

            // Next button
            Utils.waitForElementToBeClickable(driver, nextselectpartner, 10);
            nextselectpartner.click();

            System.out.println("Delivery details entered successfully");

        } catch (Exception e) {

            System.out.println("Error in delivery details: " + e.getMessage());
            e.printStackTrace();

            throw new RuntimeException("Delivery details failed", e);
        }
    }
public void completemethodssendparcel() throws InterruptedException {
    pickuplocation();
    selectPackage("medium");
    fillParcelDetails("10","20","30");
    parcelphotoapp();
    enterdeliverydetails();
    }

    public SendParcelPage(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }

        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.actionsUtil = new ActionsUtil(driver);
        this.testDataProp = new PropertiesUtil();
    }


}
