package com.bmp.automation.utility;


import java.nio.file.Files;
import java.nio.file.Paths;

public class LogUtil {
    public static String readLog(String threadId) {
        try {
            String path = "logs/thread-" + threadId + ".log";
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            return "Unable to read log file";
        }
    }
}
