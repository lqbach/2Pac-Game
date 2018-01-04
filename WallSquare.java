/**
 * @(#)WallSquare.java
 * WallSquare is a Square. Characters, like enemies, 2Pac, and bullets, can not travel on walls.
 * This class helps keep all the Characters in the maze. 
 *
 * @author 
 * @version 1.00 2016/2/12
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class WallSquare extends Square{
	
	private ImageIcon image;

    public WallSquare() {
    	super();
    	try {
		  image = new ImageIcon("Updated Sprites/WallSquare.png");
		} catch (Exception e) {
			//e.printStackTrace();
		}
    	setMovable(false);
    }
    
    public void draw(){
    	setIcon(image);
    }
    
    
    
}