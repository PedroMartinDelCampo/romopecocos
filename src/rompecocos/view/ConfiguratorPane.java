/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos.view;

import rompecocos.controller.ConfiguratorPaneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rompecocos.model.GameMode;

/**
 *
 * @author Pedro
 */
public class ConfiguratorPane extends BorderPane {
    
    private ToggleGroup gameModeGroup;
    private RadioButton mode8Radio;
    private RadioButton mode15Radio;
    
    private VBox centerBox;
    
    private Button newGameButton;
    
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    
    private ConfiguratorPaneController controller;
    private Stage stage;
    
    public ConfiguratorPane(ConfiguratorPaneController controller, Stage stage) {
        buildUI();
        this.controller = controller;
        this.stage = stage;
        setActions();
        controller.setView(this);
    }
    
    private void buildUI() {
        gameModeGroup = new ToggleGroup();
        
        mode8Radio = new RadioButton("8 piezas");
        mode8Radio.setToggleGroup(gameModeGroup);
        mode8Radio.setSelected(true);
        mode8Radio.setUserData(GameMode.EIGHT_PUZZLE);
        
        mode15Radio = new RadioButton("15 piezas");
        mode15Radio.setToggleGroup(gameModeGroup);
        mode15Radio.setUserData(GameMode.FIFTEEN_PUZZLE);
        
        centerBox = new VBox(mode8Radio, mode15Radio);
        setCenter(centerBox);
        
        newGameButton = new Button("Nuevo juego");
        newGameButton.setDefaultButton(true);
        setBottom(newGameButton);
        
        setPrefSize(WIDTH, HEIGHT);
    }
    
    private void setActions() {
        newGameButton.setOnAction((ActionEvent event) -> controller.newGame());
    }
    
    public ToggleGroup getGameModeGroup() {
        return gameModeGroup;
    }
    
    public Stage getStage() {
        return stage;
    }
    
}
