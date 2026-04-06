package com.bmp.automation.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    protected String confFilePath;
    protected FileInputStream confFileInput;
    protected Properties configProp;

    protected String testDataFilePath;
    protected FileInputStream testFileInput;
    protected Properties testDataProp;

    public PropertiesUtil() {
        // Load configuration properties
        loadConfigProperties();

        // Load test data properties
        loadTestDataProperties();
    }
   // C:\Users\Microsoft\IdeaProjects\Bmp\src\main\resources\config.properties
    private void loadConfigProperties() {
        confFilePath = System.getProperty("user.dir") + "//src//main//resources//config.properties";
        try (FileInputStream confFileInput = new FileInputStream(confFilePath)) {
            configProp = new Properties();
            configProp.load(confFileInput);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private void loadTestDataProperties() {
        testDataFilePath = System.getProperty("user.dir") + "//src//main//resources//testData.properties";
        try (FileInputStream testFileInput = new FileInputStream(testDataFilePath)) {
            testDataProp = new Properties();
            testDataProp.load(testFileInput);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    // CONFIG PROPERTY ACCESS
    public String getConfig(String key) {

        return configProp.getProperty(key);
    }

    // TEST DATA ACCESS
    public String getTestData(String key) {

        return testDataProp.getProperty(key);
    }
}
