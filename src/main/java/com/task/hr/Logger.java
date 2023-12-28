package com.task.hr;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void logToFile(String logFilePath, String message) {
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());
            String logEntry = String.format("[%s] %s%n", timestamp, message);
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
}
