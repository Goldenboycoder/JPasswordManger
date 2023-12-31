package src.main.java.passwordmanager.Threads;

import java.io.File;
import java.util.HashMap;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import src.main.java.passwordmanager.Utils.Encryption;
import src.main.java.passwordmanager.Utils.FileUtils;
import src.main.java.passwordmanager.controller.MainPageController;
import src.main.java.passwordmanager.model.PasswordManagerSettings;

public class MainPageLogicThread extends Thread {

    private MainPageController mainPageController;
    private HashMap<String, Boolean> drivesMap;
    private PasswordManagerSettings settings;

    public MainPageLogicThread(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
        drivesMap = new HashMap<String, Boolean>();
        this.loadSettings();
        try {
            // Generate test keys
            byte[][] privPubKeys = Encryption.generateKeyPair(settings.rsaKeySize);
            // write to disck test keys
            FileUtils.writeToFile("D:\\"+settings.privateKeyRelativePath,privPubKeys[0]);
            FileUtils.writeToFile("D:\\"+settings.publicKeyRelativePath, privPubKeys[1]);
        } catch (Exception e) {
            // TODO: handle exception
            this.mainPageController.setTextArea1(e.getMessage());

        }
        
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
            this.mainPageController.setTextArea1(e.getMessage());
        }
    }

    @Override
    public void run() {
        // code to execute in the home page
        try {
            String drivePath = "";
            while (drivePath.equals("")) {
                drivePath = this.detectDrives();

                Thread.sleep(10000);
            }
            mainPageController.setTextArea1(String.format("Located Keys in Drive: %s", drivePath));
            // check if the keys match by encrypting using private and decrypting using public
            /*String publicKey = FileUtils.readFileContent(drivePath+settings.publicKeyRelativePath);
            String privateKey = FileUtils.readFileContent(drivePath+settings.privateKeyRelativePath);
            String testString = "this is a test";
            String cipherText = Encryption.encryptWithPrivateKey(privateKey, testString);
            String result = Encryption.decryptWithPublicKey(publicKey, cipherText);*/
            
            byte[] publicKey = FileUtils.readFileBytes(settings.publicKeyPath);
            byte[] privateKey = FileUtils.readFileBytes(drivePath+settings.privateKeyRelativePath);
            String testString = "this is a test";
            String cipherText = Encryption.encryptWithPublicKey(publicKey, testString);
            //String cipherText = Encryption.encryptWithPrivateKey(privateKey, testString);
            String result = Encryption.decryptWithPrivateKey(privateKey, cipherText);

            if(result.equals(testString)){
                mainPageController.setTextArea1("Keys match");
            }


        } catch (Exception e) {
            // TODO: handle exception
            this.mainPageController.setTextArea1(e.getMessage());
        }
        
    }

    private boolean isDriveContainsKeys(File drive) {
        File privateKey = new File(drive.getAbsolutePath() + this.settings.privateKeyRelativePath);
        //File publicKey = new File(drive.getAbsolutePath() + this.settings.publicKeyRelativePath);
        return privateKey.exists() ;//&& publicKey.exists();

    }

    private String detectDrives() {
        // detect dirve letters
        mainPageController.setTextArea1("Scanning drives for keys");
        File[] drives = File.listRoots();
        String result = "";

        for (File drive : drives) {
            String drivePath = drive.getAbsolutePath();
            if (!drivesMap.containsKey(drivePath)) {
                mainPageController.setTextArea1(drivePath);
                drivesMap.put(drivePath, false);
            }

            // check if // necessary files exist
            boolean hasKeys = this.isDriveContainsKeys(drive);
            if (hasKeys) {
                drivesMap.clear();
                return drivePath;
            }

        }
        return result;
    }

}
