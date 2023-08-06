package src.main.java.passwordmanager.view;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import src.main.java.passwordmanager.Threads.MainPageLogicThread;
import src.main.java.passwordmanager.controller.MainPageController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;



public class PasswordManagerAppGui extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        

        loader.setLocation(new URL("file:///"+new File(System.getProperty("user.dir")+"/src/main/resources/mainPage-layout.fxml").getAbsolutePath()));
        //MainPageController mainPageController = loader.getController();
        //loader.setController(mainPageController);
        VBox vbox = loader.<VBox>load();
        MainPageController mainPageController = loader.getController();

        // Create Button
        //Button button = new Button("Click me");

        // Create the layout and add the button to it
        //StackPane layout = new StackPane();
        //layout.getChildren().add(button);
        
        //Create the Scene with the layout
        //Scene scene = new Scene(layout, 300, 200);
        Scene scene = new Scene(vbox);
        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Set the title of the stage
        primaryStage.setTitle("Password Manager");

        // show the window
        primaryStage.show();
        this.startLogic(mainPageController);
    }

    private void startLogic(MainPageController controller){
        MainPageLogicThread mainPageThread = new MainPageLogicThread(controller);
        mainPageThread.start(); 
    }

    

    public static void main(String[] args) {
        launch(args);
    }

    
}
