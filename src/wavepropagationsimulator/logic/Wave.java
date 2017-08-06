/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wavepropagationsimulator.logic;

/**
 *
 * @author steven
 */
public class Wave {
    
    private int x;
    private int y;
    private int diameter;
    private boolean original;
    private boolean top;
    private boolean bottom;
    private boolean left;
    private boolean right;
    private double blue;
    private double green;
    private int rate;
    
    public Wave(int x, int y, int d, int rate) {
        this.x = x;
        this.y = y;
        this.diameter = d;
        this.rate = rate;
        
        this.original = true;
        this.blue = 255;
        this.green = 190;
    }
    
    public void move() {
        diameter += rate;
    }
    
    public void decreaseColors() {
        blue -= 1;
        green -= 1;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getDiameter() {
        return diameter;
    }
    
    public void setNotOriginal() {
        this.original = false;
    }
    
    public boolean original() {
        return original;
    }
    
    public void setTop() {
        this.top = true;
    }
    
    public void setBottom() {
        this.bottom = true;
    }
    
    public void setLeft() {
        this.left = true;
    }
    
    public void setRight() {
        this.right = true;
    }
    
    public boolean getTop() {
        return top;
    }
    
    public boolean getBottom() {
        return bottom;
    }
    
    public boolean getRight() {
        return right;
    }
    
    public boolean getLeft() {
        return left;
    }
    
    public int getGreen() {
        return (int)green;
    }
    
    public int getBlue() {
        return (int)blue;
    }
    
    public void setColors(int green, int blue) {
        this.green = green;
        this.blue = blue;
    }
    
    public int getRate() {
        return rate;
    }
}