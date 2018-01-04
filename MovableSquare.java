
/**
 * Write a description of class MovableSquare here.
 * Movable square is a Square. This square can hold pictures of enemies, bullets, pills, and pac.
 * MovableSquare is a square in which characters can walk on. It is the path of the maze.
 * The image of the square can change. We have an ArrayList of images. The image can switch when the character is on a certain square.
 *
 * When a Character approaches onto the square, the square creates the character on the square, then removes it if the character walks off.
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
public class MovableSquare extends Square
{
    private Pill pill;
    private Enemy enemy;
    private Pac pac;
    private Bullet bullet;
    private ArrayList<ImageIcon> image = new ArrayList<ImageIcon>(); //this ArrayList creates memory for each image
    public MovableSquare(String s)
    {
        switch(s) //each movable square is full of PowerPills or NormalPills in which Pac has to collect.
        {
            case "PP":
                addPowerPill();
                break;
            case "NP":
                addNormalPill();
                break;
            default:
                break;
        }
        try{
            image.add(new ImageIcon("Updated Sprites/NewBullet.png")); //Bullet -- 1
            image.add(new ImageIcon("Updated Sprites/Gangster.png")); //Gangster -- 2
            image.add(new ImageIcon("Updated Sprites/NotoriousBIG.png")); //Notorious B.I.G. -- 3
            image.add(new ImageIcon("Updated Sprites/Pac.png")); //Tupac -- 4
            image.add(new ImageIcon("Updated Sprites/Pill.png")); //Pill -- 5
            image.add(new ImageIcon("Updated Sprites/Policeman.png")); //Policeman -- 6
            image.add(new ImageIcon("Updated Sprites/PowerPill.png")); //PowerPill -- 7
            image.add(new ImageIcon("Updated Sprites/SugeKnight.png")); //Suge Knight -- 8
            image.add(new ImageIcon("Updated Sprites/BlueBackground.png")); //Default Square -- 9
            image.add(new ImageIcon("Updated Sprites/Death.png")); //A dead picture -- 10
        }
        catch(Exception e)
        {
        }
        setMovable(true);
    }

    public Character addEnemy(String s, char d, boolean cS)
    {
        if (s.equalsIgnoreCase("Policeman"))
            enemy = new Policeman(d, cS);
        else if (s.equalsIgnoreCase("Gangster"))
            enemy = new Gangster(d, cS);
        else if (s.equalsIgnoreCase("Notorious B.I.G."))
            enemy = new NotoriousBIG(d, cS);
        else if (s.equalsIgnoreCase("Suge Knight"))
            enemy = new SugeKnight(d, cS);
        draw();
        return (conflict());
    }
    public Enemy removeEnemy()
    {
        Enemy tempEnemy = enemy;
        enemy = null;
        return tempEnemy;
    }
    public Enemy getEnemy()
    {
        return enemy;
    }
    public Character addBullet(char a)
    {
        bullet = new Bullet(a);
        draw();
        return (conflict());      
    }
    public void removeBullet()
    {
        bullet = null;
    }
    public Bullet getBullet()
    { 
        return bullet;
    }
    public void draw(){ //Checks to see what is inside in the square and draws it
        
        if (pac != null && (enemy == null && bullet == null)) //if pac is on the square and no enemies and bullets are on it
        {
            setIcon(image.get(3));
        }
        else if (enemy != null && bullet == null) //else if an enemy is on a square and no bullets are in it
        {
            if (enemy.toString().equals("Gangster"))
                setIcon(image.get(1));
            else if (enemy.toString().equals("Notorious B.I.G."))
                setIcon(image.get(2));
            else if (enemy.toString().equals("Policeman"))
                setIcon(image.get(5));
                else if (enemy.toString().equals("Suge Knight"))
                setIcon(image.get(7));
        }
        else if (enemy != null && bullet != null){ //enemy gets shot and dies
        	setIcon(image.get(9));
        }
        else if (bullet != null) //else if there is a bullet in the square
        {
            setIcon(image.get(0));
        }
        else if (pill != null) //else if there is a pill
        {
            if (pill.toString().equals("Power Pill"))
                setIcon(image.get(6));
            else if (pill.toString().equals("Normal Pill"))
                setIcon(image.get(4));
        }
        
        else //nothing on the square. Just returns an empty square
        
            setIcon(image.get(8));
    }
    
    
    public Character addPac(char d, int p, int l, boolean cS)
    {
        pac = new Pac(d, p, l, cS);
        if (pill != null)
        {	
            pac.addPoints(pill.getValue());
            if (pill.powerUp())
                pac.setCanShoot(true);
            removePill(); //takes pill away when Pac steps on a pill
        }
       
        draw();
        return (conflict());
    }
    public Character removePac()
    {
        Pac tempPac = pac;
        pac = null;
        return tempPac;
    }
    public Pac getPac(){
        return pac;
    }
    public void addNormalPill()
    {
        pill = new NormalPill();
    }
    public void addPowerPill()
    {
        pill = new PowerPill();
    }
    public void removePill()
    {
        pill = null;
    }
    
    //conflict method sees if the square has two Characters on it, or a collision of some kind
    public Character conflict()
    {
        
        if (enemy != null && pac != null)
        {
            Pac tempPac = new Pac(pac.getDirection(), pac.getPoints(), pac.getLives()-1, false);
            removePac();
            return tempPac;
        }
        else if (enemy != null && bullet != null)
        {
            Enemy tempEnemy = new Enemy(enemy.getDirection(), enemy.ifCanShoot());
            removeEnemy();
            return tempEnemy;
        }
        return null;
    }
}
