package src.main.java.passwordmanager.view;

import java.io.IOException;
import java.io.PrintWriter;

import src.main.java.passwordmanager.controller.CliController;
import src.main.java.passwordmanager.controller.IController;

public class CliInterface {
    

    public static void main(String[] args){
        
        try {
            IController controller = new CliController();
            controller.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
