/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import rompecocos.view.ConfiguratorPane;
import rompecocos.Game;
import rompecocos.model.GameMode;
import rompecocos.model.Rompecabezas;

/**
 *
 * @author Pedro
 */
public class ConfiguratorPaneController {
    
    private ConfiguratorPane view;
    
    public void setView(ConfiguratorPane view) {
        this.view = view;
    }
    
    public void newGame() {
        GameMode mode = (GameMode) view.getGameModeGroup().getSelectedToggle().getUserData();
        int n = 0;
        switch (mode) {
            case EIGHT_PUZZLE:
                n = 3;
                break;
            case FIFTEEN_PUZZLE:
                n = 4;
                break;
        }
        Rompecabezas r = new Rompecabezas(n);
        GameController controller = new GameController(r);
        Game game = new Game(controller);
        try {
            game.start(new Stage());
            view.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(ConfiguratorPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
