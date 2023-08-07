package src.main.java.passwordmanager.EventListeners;

import src.main.java.passwordmanager.controller.MainPageController;

public class MainPageTextArea1Listener implements IEventListener{

    private MainPageController mainPageController;

    public MainPageTextArea1Listener(MainPageController controller){
        this.mainPageController = controller;
    }


    @Override
    public void update(String message) {
        // TODO Auto-generated method stub
        this.mainPageController.setTextArea1(message);
    }
    
}