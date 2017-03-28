/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rompecocos.controller.GameController;
import rompecocos.model.Matrix;
import rompecocos.model.Point;
import rompecocos.model.Rompecabezas;

/**
 *
 * @author Pedro
 */
public class RompecabezasPane extends GridPane implements PieceListener {
    
    private Rompecabezas rompecabezas;
    private Matrix<PiecePane> piezas;
    private final int width;
    private final int height;
    private Matrix<String> matrix;
    private final GameController controller;
    private int moveCount = 0;
    private Stage app;

    public Stage getApp() {
        return app;
    }

    public void setApp(Stage app) {
        this.app = app;
    }
    
    public RompecabezasPane(GameController controller) {
        this.controller = controller;
        rompecabezas = controller.getRompecabezas();
        matrix = rompecabezas.asMatrix();
        width = matrix.getWidth();
        height = matrix.getHeight();
        buildUI();
    }
    
    private void buildUI() {
        setPrefSize(300, 300);
        setUpMenu();
        piezas = new Matrix<>(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point p = new Point(i, j);
                PiecePane pane = new PiecePane("", false, p);
                pane.setListener(this);
                setConstraints(pane, i, j+1);
                getChildren().add(pane);
                piezas.set(p, pane);
            }
        }
        updatePieces();
        updateActivePoints();
    }
    
    private void setUpMenu() {
        if (controller == null) return;
        MenuItem back = new MenuItem("Volver");
        MenuItem close = new MenuItem("Cerrar");
        
        MenuItem resetGame = new MenuItem("Reiniciar juego");
        resetGame.setOnAction((evt) -> controller.resetGame());
        Menu game = new Menu("Juego", null, resetGame);
        
        MenuBar menuBar = new MenuBar(game);
        VBox box = new VBox(menuBar);
        setConstraints(box, 0, 0);
        getChildren().add(box);
    }
    
    private void updatePieces() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point p = new Point(j, i);
                PiecePane pane = piezas.get(p);
                pane.setActive(false);
                pane.setLabel(matrix.get(p));
            }
        }
        Point blank = rompecabezas.getBlank();
        PiecePane blankPane = piezas.get(blank);
        blankPane.setLabel("");
    } 
    
    private void updateActivePoints() {
        rompecabezas.activePoints().forEach((p) -> {
            piezas.get(p).setActive(true);
        });
    }
    
    @Override
    public void onPieceClicked(PiecePane view) {
        if (view.isActive()) {
            Point p = view.getCoords();
            rompecabezas.move(p);
            updatePieces();
            updateActivePoints();
            moveCount++;
            if (rompecabezas.isSolved()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ganaste en " + moveCount + " movimientos");
                alert.show();
            }
        }
    }

    public void setRompecabezas(Rompecabezas rompecabezas) {
        this.rompecabezas = rompecabezas;
        matrix = rompecabezas.asMatrix();
        updatePieces();
        updateActivePoints();
    }
    
}
