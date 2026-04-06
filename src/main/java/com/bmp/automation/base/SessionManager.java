package com.bmp.automation.base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private static final Map<String, Map<String, String>> SESSION_STORE = new HashMap<>();

    public static void saveSession(WebDriver driver, String sessionKey) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, String> storage = new HashMap<>();

        storage.put("token", (String) js.executeScript(
                "return window.localStorage.getItem('token');"));

        SESSION_STORE.put(sessionKey, storage);
    }

    public static boolean restoreSession(WebDriver driver, String sessionKey) {
        if (!SESSION_STORE.containsKey(sessionKey)) {
            return false;
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, String> storage = SESSION_STORE.get(sessionKey);

        storage.forEach((key, value) ->
                js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);",
                        key, value)
        );

        return true;
    }
}
