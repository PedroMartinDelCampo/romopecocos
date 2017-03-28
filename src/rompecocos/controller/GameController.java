/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos.controller;

import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.stage.Stage;
import rompecocos.App;
import rompecocos.model.Point;
import rompecocos.model.Rompecabezas;
import rompecocos.view.RompecabezasPane;

/**
 *
 * @author Pedro
 */
public class GameController {
    
    private RompecabezasPane view;
    private Rompecabezas rompecabezas;
    private final int minMoves;
    private final int maxMoves;
    private final Stack<Point> moves;
    private Stage stage;

    public GameController(Rompecabezas r) {
        rompecabezas = r;
        int n = rompecabezas.asMatrix().getWidth();
        minMoves = (int) ((n*n)/2);
        maxMoves = (int) ((n*n)/1.5);
        moves = new Stack<>();
        init();
    }
    
    private void init() {
        while (!moves.empty())
            moves.pop();
        int count = (int) Math.round(Math.random()*(maxMoves - minMoves) + minMoves);
        moves.push(rompecabezas.getBlank());
        for (int i = 0; i < count; i++) {
            List<Point> available = rompecabezas.activePoints();
            int length = available.size();
            int pos = (int) Math.floor(Math.random()*length);
            Point p = available.get(pos);
            rompecabezas.move(p);
            moves.push(p);
        }
    }
    
    protected RompecabezasPane getView() {
        return view;
    }

    public void setView(RompecabezasPane view) {
        this.view = view;
    }

    public Rompecabezas getRompecabezas() {
        return rompecabezas;
    }
    
    public void resetGame() {
        rompecabezas = new Rompecabezas(rompecabezas.asMatrix().getWidth());
        init();
        view.setRompecabezas(rompecabezas);
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void newGame(){
        new App().start(stage);
    }
    
    public void move(Point p){
        moves.push(p);
    }
    
    public void solve() {
        moves.pop();
        while(!moves.empty()) {
            Point p = moves.pop();
            rompecabezas.move(p);
            view.setRompecabezas(rompecabezas);
        }
    }
   
    
}
