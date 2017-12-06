/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.io.Serializable;

/**
 * Położenie Ludzi i Pizzerii.
 * @author Marcin
 */
public class Polozenie implements Serializable{
    private int x;
    private int y;

    public Polozenie(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
