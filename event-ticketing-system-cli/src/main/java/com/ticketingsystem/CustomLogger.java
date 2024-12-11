package com.ticketingsystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class CustomLogger {
    private static final Logger logger = Logger.getLogger(CustomLogger.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            // Get the next available log file name
            int fileIndex = getNextFileIndex();
            String logFileName = "logs/simulation_logging_" + fileIndex + ".log";

            // Create the logs directory if it doesn't exist
            Files.createDirectories(Paths.get("logs"));

            // Set up file handler
            fileHandler = new FileHandler(logFileName, true);
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    String timestamp = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    return String.format("[%s] %s: %s%n", timestamp, record.getLevel(), record.getMessage());
                }
            });
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Error setting up logger: " + e.getMessage());
        }
    }

    // Method to log information
    public static void logInfo(String message) {
        logger.info(message);
    }

    // Method to log errors
    public static void logError(String message, Throwable e) {
        logger.severe(message + " | Exception: " + e.getMessage());
    }

    // Method to get the next available file index for log file naming
    private static int getNextFileIndex() {
        int index = 0;
        while (Files.exists(Paths.get("logs/simulation_logging_" + index + ".log"))) {
            index++;
        }
        return index;
    }
}
