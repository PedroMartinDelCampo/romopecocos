/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class Rompecabezas {
    
    private final int n;
    private Matrix<String> matrix;
    private Matrix<String> solution;
    private Point blank;

    public Point getBlank() {
        return blank;
    }
    
    public Rompecabezas(int n) {
        this.n = n;
        buildMatrix();
    }
    
    private void buildMatrix() {
        matrix = new Matrix<>(n, n);
        solution = new Matrix<>(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Point p = new Point(j, i);
                String value = "" + ( i*n + (j+1) );
                matrix.set(p, value);
                solution.set(p, value);
            }
        }
        blank = new Point(n-1, n-1);
        matrix.set(blank, "");
        solution.set(blank, "");
    }
    
    public Matrix<String> asMatrix() {
        return matrix;
    }
    
    @Override
    public String toString() {
        return "Rompecabezas{" + "n=" + n + ", matrix=" + matrix + '}';
    }
    
    public void move(Point p) {
        if (isValid(p)) {
            String value = matrix.get(p);
            matrix.set(p, "");
            matrix.set(blank.clone(), value);
            blank = p.clone();
        } else {
            System.out.println("Movimiento inválido: " + p);
        }
    }
    
    private boolean isValid(Point p) {
        return activePoints().contains(p);
    }
    
    public List<Point> activePoints() {
        List<Point> points = new ArrayList<>(4);
        if (blank.getX() > 0) {
            points.add(blank.add(new Point(-1, 0)));
        }
        if (blank.getX() < n-1) {
            points.add(blank.add(new Point(1, 0)));
        }
        if (blank.getY() > 0) {
            points.add(blank.add(new Point(0, -1)));
        }
        if (blank.getY() < n-1) {
            points.add(blank.add(new Point(0, 1)));
        }
        return points;
    }
    
    public boolean isSolved() {
        return matrix.equals(solution);
    }
    
    public void reset() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Point p = new Point(j, i);
                matrix.set(p, solution.get(p));
            }
        }
    }

    public Matrix<String> getSolution() {
        return solution;
    }
    
}
