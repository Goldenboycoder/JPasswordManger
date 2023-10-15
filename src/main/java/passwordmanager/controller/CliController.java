package src.main.java.passwordmanager.controller;

import java.io.File;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import src.main.java.passwordmanager.Logging.CliLogger;
import src.main.java.passwordmanager.Logging.ILogger;
import src.main.java.passwordmanager.Utils.CliHelper;
import src.main.java.passwordmanager.model.PasswordManagerSettings;

public class CliController implements IController {
    
    private PasswordManagerSettings settings;
    private ILogger logger;

    public CliController(){
        this.logger = new CliLogger();
        this.loadSettings();
    }


    private void loadSettings() {
        String settingsPath = "\\src\\main\\resources\\setting.json";
        try {
            JsonFactory factory = JsonFactory.builder().enable(JsonReadFeature.ALLOW_JAVA_COMMENTS).build();
            System.out.println(System.getProperty("user.dir")+settingsPath);
            //JsonParser jparse = factory.createParser(new File(System.getProperty("user.dir")+settingsPath));
            ObjectMapper mapper = new ObjectMapper();
            this.settings =  mapper.readValue(new File(System.getProperty("user.dir")+settingsPath), PasswordManagerSettings.class); 
        } catch (Exception e) {
            // TODO: handle exception
            logger.logError(e.getMessage());
        }
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        System.out.println(CliHelper.outputWelcome());
    }
}
