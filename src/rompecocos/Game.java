/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos;

import rompecocos.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rompecocos.view.RompecabezasPane;

/**
 *
 * @author Pedro
 */
public class Game extends Application {
    
    private final RompecabezasPane view;
    
    public Game(GameController controller) {
        view = new RompecabezasPane(controller);
        controller.setView(view);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        view.setPrefSize(300, 300);
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rompecocos");
        primaryStage.show();
    }
    
}
