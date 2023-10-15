package src.main.java.passwordmanager.controller;

import java.util.HashMap;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import src.main.java.passwordmanager.EventListeners.IEventListener;

public class MainPageController {

    @FXML
    public TextArea maingPage_textArea1;

    @FXML
    public Label mainpage_inserUsb_Label;

    private HashMap<String, List<IEventListener>> listeners = new HashMap<String, List<IEventListener>>();

    public MainPageController() {

    }

    public void setTextArea1(String text) {
        Platform.runLater(() -> maingPage_textArea1.appendText("\n" + text));//.setText(maingPage_textArea1.getText() + "\n" + text));
    }

    public void setMainLabel(String text) {
        Platform.runLater(() -> mainpage_inserUsb_Label.setText(text));
    }
}
