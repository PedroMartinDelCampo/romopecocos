/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos;

import rompecocos.view.ConfiguratorPane;
import rompecocos.controller.ConfiguratorPaneController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Pedro
 */
public class App extends Application {
    
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    
    @Override
    public void start(Stage primaryStage) {
        ConfiguratorPane pane = new ConfiguratorPane(new ConfiguratorPaneController(), primaryStage);
        AnchorPane root = new AnchorPane(pane);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Rompecocos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
