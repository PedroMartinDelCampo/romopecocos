/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rompecocos.model;

import java.util.Arrays;

/**
 *
 * @author Pedro
 * @param <T> Type of the contained objects
 */
public class Matrix<T> {
    
    private final int width;
    private final int height;
    private final Object[][] array;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        array = new Object[height][width];
    }
    
    public void set(Point p, T value) {
        array[p.getY()][p.getX()] = value;
    }
    
    public T get(Point p) {
        return (T) array[p.getY()][p.getX()];
    }

    @Override
    public String toString() {
        return "Matrix{" + "width=" + width + ", height=" + height + ", array=" + Arrays.deepToString(array) + '}';
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public boolean equals(Matrix<T> other) {
        if (!(width == other.getWidth() && height == other.getHeight())) return false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point p = new Point(i, j);
                T own = get(p);
                T another = other.get(p);
                if (!own.equals(another)) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
