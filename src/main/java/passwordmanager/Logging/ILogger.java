package src.main.java.passwordmanager.Logging;

public interface ILogger {
    
    void logError(String message);
    void logInformation(String message);
    void logDebug(String message);
}
