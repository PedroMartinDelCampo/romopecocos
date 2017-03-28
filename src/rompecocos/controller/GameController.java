/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos.controller;

import java.util.List;
import java.util.Stack;
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
        System.out.println(count);
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
    
}
