/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos.view;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import rompecocos.model.Point;

/**
 *
 * @author Pedro
 */
public class PiecePane extends BorderPane {
    
    private final Label label;
    private boolean active;
    private final Effect activeEffect;
    
    private PieceListener listener;
    private final Point coords;
    
    public PiecePane(String text, boolean active, Point p) {
        this.label = new Label(text);
        activeEffect = new Glow();
        setActive(active);
        setCenter(label);
        setPrefSize(75, 75);
        setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setTextFill(Color.WHITE);
        coords = p;
    }

    public Point getCoords() {
        return coords;
    }
    
    public void setLabel(String text) {
        label.setText(text);
    }
    
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            setEffect(activeEffect);
            setCursor(Cursor.CLOSED_HAND);
        } else {
            setEffect(null);
            setCursor(Cursor.DEFAULT);
        }
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setListener(PieceListener listener) {
        this.listener = listener;
        this.setOnMouseClicked((evt) -> this.listener.onPieceClicked(this));
    }
    
}
