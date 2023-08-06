package src.main.java.passwordmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MainPageController {
   
    
    @FXML
    public TextArea maingPage_textArea1 ;

    @FXML
    public Label mainpage_inserUsb_Label;

    public MainPageController(){
        
    }

    public void setTextArea1(String text) {
        maingPage_textArea1.setText(maingPage_textArea1.getText() + "\n" + text);
    }

    public void setMainLabel(String text){
        mainpage_inserUsb_Label.setText(text);
    }
}
