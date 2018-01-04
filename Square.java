/**
 * @(#)Square.java
 * This abstract class is very important for the integration of other classes.
 * Square is a JLabel. The square will be able to hold images as ImageIcons. 
 * Board class will hold an array of squares. Each square will have a different image for Pac-Man.
 * The square will act as a pixel in creating the bigger image for our game. 
 * @author 
 * @version 1.00 2016/2/11
 */

import javax.swing.*;
public abstract class Square extends JLabel {
	private boolean movable; //this sees if Characters, like 2Pac, enemies, or bullets, will be able to travel on the square

    public Square(){

    }
    
    public abstract void draw();
    public void setMovable(boolean b){
    	movable = b;
    }
    public boolean getMovable(){
    	return movable;
    }
    
    
    
}