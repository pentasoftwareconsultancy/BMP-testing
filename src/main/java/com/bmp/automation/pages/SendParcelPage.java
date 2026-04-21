package com.bmp.automation.pages;

import com.bmp.automation.base.PropertiesUtil;
import com.bmp.automation.utility.ActionsUtil;
import com.bmp.automation.utility.Utils;
import jdk.jshell.execution.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void pickuplocation()
    {
        Utils.waitForElementToBeClickable(driver,sendParcelBtn,10);
        sendParcelBtn.click();
        Utils.waitForVisibilityOFElement(driver,senderNameField,10);
        senderNameField.sendKeys("Jayshri");
        pickupAddField.sendKeys("pimple-Saudagar");
        pickupCityField.sendKeys("Pune");
        pickupStateField.sendKeys("Maharashtra");
        pickupPincodeField.sendKeys("552211");
        pickupCountryField.sendKeys("India");
        pickupPhoneField.sendKeys("9922554477");
        pickupAltPhoneField.sendKeys("8899664455");
        pickupAadharField.sendKeys("123456789456");
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

    public void fillParcelDetails(String parcellenght, String parcelwidth, String parcelHeight) {


        //enterDimensions(parcellenght, parcelwidth, parcelHeight);
    }
    public void parcelphotoapp()
    {
        parcelcontent.sendKeys("parcelcontent");
        Utils.waitForElementToBeClickable(driver,parcelphoto,10);
        String photopath="C:\\Users\\OM\\BMP_Automation\\BMP-testing\\Screenshot";
        Utils.sendKeyUsingJS(driver,parcelphoto,photopath);

        enterparcelvalue.sendKeys("1000");
        enterparcelnote.sendKeys("Books");

    }
public void enterdeliverydetails()
{
    enterreceivername.sendKeys("Krishna");
    Utils.waitForElementToBeClickable(driver,enterdeliveryadd,5);
    enterdeliveryadd.sendKeys("Hadapsar");
   actionsUtil.ta
   Utils.waitForVisibilityOFElement(driver,enterdeliverycity,5);
    enterdeliverycity.sendKeys("Pune");
    enterrecpincode.sendKeys("415522");
    enterdelcountry.sendKeys("India");
    enterdelphone.sendKeys("9966335522");
    enteraltdelphone.sendKeys("8899664455");
    Utils.waitForElementToBeClickable(driver,nextselectpartner,10);
    nextselectpartner.click();

}
public void completemethodssendparcel()
{
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
