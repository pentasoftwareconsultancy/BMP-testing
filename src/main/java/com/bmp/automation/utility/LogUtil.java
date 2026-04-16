package com.bmp.automation.utility;


import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    public static String readLog(String threadId) {
        try {
            String path = "logs/thread-" + threadId + ".log";
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            return "Unable to read log file";
        }
    }
}
