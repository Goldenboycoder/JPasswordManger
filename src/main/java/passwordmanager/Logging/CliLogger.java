package src.main.java.passwordmanager.Logging;

import java.util.Date;

public class CliLogger implements ILogger {

    private static final String errorTag = "[ERROR]";
    private static final String infoTag = "[INFO]";
    private static final String debugTag = "[DEBUG]";


    @Override
    public void logError(String message) {
        System.console().printf(
            "%1$t %2$s %3$s",
            new Date().toString(),
            errorTag,
            message
        );
    }

    @Override
    public void logInformation(String message) {
        System.console().printf(
            "%1$t %2$s %3$s",
            new Date().toString(),
            infoTag,
            message
        );
    }

    @Override
    public void logDebug(String message) {
        System.console().printf(
            "%1$t %2$s %3$s",
            new Date().toString(),
            debugTag,
            message
        );
    }
    
}
