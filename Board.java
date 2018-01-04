
/**
 * Write a description of class Board here.
 * The board is our entire program together. It brings all the classes and integrate them into one class. 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
public class Board extends JPanel
{
    private java.util.Timer powerUpTimer;
    private int pacLives = 3;
    private int pacPoints;
    private Coordinate pacCoord;
    private Coordinate gangsterCoord;
    private Coordinate policemanCoord;
    private Coordinate notoriousCoord;
    private Coordinate sugeCoord;
    private Coordinate bulletCoord;
    private Square[][] tiles = new Square[32][28]; //this is the real 2D array. It refers back to our board 2D array to get info.
    //this 2D array is our virtual board.
    private int[][] board = {{1,1,1,1,  1,1,1,1,    1,1,1,1,    1,1,1,1,    1,1,1,1,    1,1,1,1,    1,1,1,1},
                                 {1,2,0,0,  0,0,0,0,    0,0,0,0,    0,1,1,0,    0,0,0,0,    0,0,0,0,    0,0,2,1},
                                 {1,4,1,1,  1,1,0,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,0,1,1,    1,1,5,1},
                                 {1,0,1,1,  1,1,0,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,0,1,1,    1,1,0,1},
                                 {1,0,1,1,  1,1,0,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,0,1,1,    1,1,0,1},
                                 {1,0,0,0,  0,0,0,0,    0,0,0,0,    0,0,0,0,    0,0,0,0,    0,0,0,0,    0,0,0,1},
                                 {1,0,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,0,1},
                                 {1,0,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,0,1},
                                 {1,0,0,0,  0,0,0,1,    1,0,0,0,    0,1,1,0,    0,0,0,1,    1,0,0,0,    0,0,0,1},
                                 {1,1,1,1,  1,1,0,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,0,0,    0,0,0,0,    0,0,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  0,0,0,0,    0,0,1,1,    1,1,1,1,    1,1,0,0,    0,0,0,0,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,0,0,    0,0,3,0,    0,0,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,1,1,1,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    1,1,1,1},
                                 {1,0,0,0,  0,0,0,0,    0,0,0,0,    0,1,1,0,    0,0,0,0,    0,0,0,0,    0,0,0,1},
                                 {1,0,1,1,  1,1,0,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,0,1,1,    1,1,0,1},
                                 {1,0,1,1,  1,1,0,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,0,1,1,    1,1,0,1},
                                 {1,0,0,0,  1,1,0,0,    0,0,0,0,    0,0,0,0,    0,0,0,0,    0,0,1,1,    0,0,0,1},
                                 {1,1,1,0,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    0,1,1,1},
                                 {1,1,1,0,  1,1,0,1,    1,0,1,1,    1,1,1,1,    1,1,0,1,    1,0,1,1,    0,1,1,1},
                                 {1,0,0,0,  0,0,0,1,    1,0,0,0,    0,1,1,0,    0,0,0,1,    1,0,0,0,    0,0,0,1},
                                 {1,0,1,1,  1,1,1,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,1,1,1,    1,1,0,1},
                                 {1,6,1,1,  1,1,1,1,    1,1,1,1,    0,1,1,0,    1,1,1,1,    1,1,1,1,    1,1,7,1},
                                 {1,2,0,0,  0,0,0,0,    0,0,0,0,    0,0,0,0,    0,0,0,0,    0,0,0,0,    0,0,2,1},
                                 {1,1,1,1,  1,1,1,1,    1,1,1,1,    1,1,1,1,    1,1,1,1,    1,1,1,1,    1,1,1,1}
                                 };
                                 
    /* 0 = normal pill
     * 1 = wall square
     * 2 = power pill
     * 3 = Tupac
     * 4 = gangster
     * 5 = Notorious BIG
     * 6 = Policeman
     * 7 = Suge Knight
     *
     *
     *
     */
    public Board()
    {
        
        setLayout(new GridLayout(32, 28));
        //go through each integer in our board array and set different objects to each Square in our 2D array of Squares.
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[0].length; j++)
            {
                if (board[i][j] == 0)
                    tiles[i][j] = new MovableSquare("NP");
                if (board[i][j] == 1)
                    tiles [i][j] = new WallSquare();
                if (board[i][j] == 2)
                    tiles [i][j] = new MovableSquare("PP");
                if (board[i][j] == 3)
                {
                    tiles [i][j] = new MovableSquare("");
                    pacCoord = new Coordinate(i, j);
                    ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).addPac('w', 0, 5, false);
                }
                if (board[i][j] == 4)
                {
                    tiles [i][j] = new MovableSquare("NP");
                    gangsterCoord = new Coordinate(i, j);
                    ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).addEnemy("Gangster", 'w', false);
                }
                if (board[i][j] == 5)
                {
                    tiles [i][j] = new MovableSquare("NP");
                    notoriousCoord = new Coordinate(i, j);
                    ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).addEnemy("Notorious B.I.G.", 'w', false);
                }
                if (board[i][j] == 6)
                {
                    tiles [i][j] = new MovableSquare("NP");
                    policemanCoord = new Coordinate(i, j);
                    ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).addEnemy("Policeman", 'w', false);
                }
                if (board[i][j] == 7)
                {
                    tiles [i][j] = new MovableSquare("NP");
                    sugeCoord = new Coordinate(i, j);
                    ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).addEnemy("Suge Knight", 'w', false);
                }
            }
        }
        
        //((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).addPac('w', 0, 5, false);
        pacLives = ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().getLives();
        pacPoints = 0;
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[0].length; j++)
            {
                add(tiles[i][j]);
            }
        }
        drawBoard();
    }
    public void changeDirection (char d)
    {
        ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().setDirection(d);
    }
    public void move() //gets direction of pac-man and moves him in that direction
    {
        try
        {
            switch(((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().getDirection())
            {
                case 'a':
                    if (tiles[pacCoord.getX()][pacCoord.getY()-1].getMovable())
                    {
                        Pac tempPac =((Pac)((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).removePac());
                        ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()-1]).addPac(tempPac.getDirection(),tempPac.getPoints(),tempPac.getLives(),tempPac.ifCanShoot());
                        pacCoord.setY(pacCoord.getY()-1);
                        pacPoints = ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().getPoints();
                    } 
                    break;
                case 'w':
                    if (tiles[pacCoord.getX()-1][pacCoord.getY()].getMovable())
                    {
                        Pac tempPac =((Pac)((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).removePac());
                        ((MovableSquare)tiles[pacCoord.getX()-1][pacCoord.getY()]).addPac(tempPac.getDirection(),tempPac.getPoints(),tempPac.getLives(),tempPac.ifCanShoot());
                        pacCoord.setX(pacCoord.getX()-1);
                        pacPoints = ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().getPoints();
                    }
                    break;
                case 's':
                    if (tiles[pacCoord.getX()+1][pacCoord.getY()].getMovable())
                    {
                        Pac tempPac =((Pac)((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).removePac());
                        ((MovableSquare)tiles[pacCoord.getX()+1][pacCoord.getY()]).addPac(tempPac.getDirection(),tempPac.getPoints(),tempPac.getLives(),tempPac.ifCanShoot());
                        pacCoord.setX(pacCoord.getX()+1);
                        pacPoints = ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().getPoints();
                    }
                    break;
                case 'd':
                    if (tiles[pacCoord.getX()][pacCoord.getY()+1].getMovable())
                    {
                        Pac tempPac =((Pac)((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).removePac());
                        ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()+1]).addPac(tempPac.getDirection(),tempPac.getPoints(),tempPac.getLives(),tempPac.ifCanShoot());
                        pacCoord.setY(pacCoord.getY()+1);
                        pacPoints = ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().getPoints();
                    }
                    break;
            }
        }
        
        catch (Exception e) //This starts if pac man dies, or there is no pac man on the square
        {
            
            pacLives --;
            if (((MovableSquare)tiles[18][13]).getEnemy() == null)
            {
                ((MovableSquare)tiles[18][13]).addPac('w', pacPoints, pacLives,false);
                pacCoord.setY(13);
                pacCoord.setX(18);
            }
            else 
            {
                ((MovableSquare)tiles[1][1]).addPac('w', pacPoints, pacLives,false);
                pacCoord.setY(1);
                pacCoord.setX(1);
            }
             
            
            
        }
        //System.out.println (((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().ifCanShoot());
        
        drawBoard();
    }
    public void moveEnemies() //This method moves all the enemies
    {
        setCPUDirection(); //method chooses where enemy goes by altering its direction
        //All enemies will move into their respective squares
        
        switch(((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).getEnemy().getDirection())
        {
            case 'a':
                if (tiles[gangsterCoord.getX()][gangsterCoord.getY()-1].getMovable())
                {
                    if (((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()-1]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()-1]).addEnemy("Gangster", 'a',false);
                        gangsterCoord.setY(gangsterCoord.getY()-1);
                    }
                }
                
                break;
            case 'w':
                if (tiles[gangsterCoord.getX()-1][gangsterCoord.getY()].getMovable())
                {
                    if (((MovableSquare)tiles[gangsterCoord.getX()-1][gangsterCoord.getY()]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[gangsterCoord.getX()-1][gangsterCoord.getY()]).addEnemy("Gangster", 'w',false);
                        gangsterCoord.setX(gangsterCoord.getX()-1);
                    }
                }
                break;
            case 's':
                if (tiles[gangsterCoord.getX()+1][gangsterCoord.getY()].getMovable())
                {
                    if (((MovableSquare)tiles[gangsterCoord.getX()+1][gangsterCoord.getY()]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[gangsterCoord.getX()+1][gangsterCoord.getY()]).addEnemy("Gangster", 's',false);
                        gangsterCoord.setX(gangsterCoord.getX()+1);
                    }
                }
                break;
            case 'd':
                if (tiles[gangsterCoord.getX()][gangsterCoord.getY()+1].getMovable())
                {
                    if (((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()+1]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()+1]).addEnemy("Gangster", 'd',false);
                        gangsterCoord.setY(gangsterCoord.getY()+1);
                    }
                }
                break; 
        }
        switch(((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).getEnemy().getDirection())
        {
            case 'a':
                if (tiles[policemanCoord.getX()][policemanCoord.getY()-1].getMovable())
                {
                    if (((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()-1]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()-1]).addEnemy("Policeman", 'a',false);
                        policemanCoord.setY(policemanCoord.getY()-1);
                    }
                }
                break;
            case 'w':
                if (tiles[policemanCoord.getX()-1][policemanCoord.getY()].getMovable())
                {
                    if (((MovableSquare)tiles[policemanCoord.getX()-1][policemanCoord.getY()]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[policemanCoord.getX()-1][policemanCoord.getY()]).addEnemy("Policeman", 'w',false);
                        policemanCoord.setX(policemanCoord.getX()-1);
                    }
                }
                break;
            case 's':
                if (tiles[policemanCoord.getX()+1][policemanCoord.getY()].getMovable())
                {
                    if (((MovableSquare)tiles[policemanCoord.getX()+1][policemanCoord.getY()]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[policemanCoord.getX()+1][policemanCoord.getY()]).addEnemy("Policeman", 's',false);
                        policemanCoord.setX(policemanCoord.getX()+1);
                    }
                }
                break;
            case 'd':
                if (tiles[policemanCoord.getX()][policemanCoord.getY()+1].getMovable())
                {
                    if (((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()+1]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()+1]).addEnemy("Policeman", 'd',false);
                        policemanCoord.setY(policemanCoord.getY()+1);
                    }
                }
                break; 
        }
        switch(((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).getEnemy().getDirection())
        {
            case 'a':
                if (tiles[notoriousCoord.getX()][notoriousCoord.getY()-1].getMovable())
                {
                    if (((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()-1]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()-1]).addEnemy("Notorious B.I.G.", 'a',false);
                        notoriousCoord.setY(notoriousCoord.getY()-1);
                    }
                }
                break;
            case 'w':
                if (tiles[notoriousCoord.getX()-1][notoriousCoord.getY()].getMovable())
                {
                    if (((MovableSquare)tiles[notoriousCoord.getX()-1][notoriousCoord.getY()]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[notoriousCoord.getX()-1][notoriousCoord.getY()]).addEnemy("Notorious B.I.G.", 'w',false);
                        notoriousCoord.setX(notoriousCoord.getX()-1);
                    }
                }
                break;
            case 's':
                if (tiles[notoriousCoord.getX()+1][notoriousCoord.getY()].getMovable())
                {
                    if (((MovableSquare)tiles[notoriousCoord.getX()+1][notoriousCoord.getY()]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[notoriousCoord.getX()+1][notoriousCoord.getY()]).addEnemy("Notorious B.I.G.", 's',false);
                        notoriousCoord.setX(notoriousCoord.getX()+1);
                    }
                }
                break;
            case 'd':
                if (tiles[notoriousCoord.getX()][notoriousCoord.getY()+1].getMovable())
                {
                    if (((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()+1]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()+1]).addEnemy("Notorious B.I.G.", 'd',false);
                        notoriousCoord.setY(notoriousCoord.getY()+1);
                    }
                }
                break; 
        }
        switch(((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).getEnemy().getDirection())
        {
            case 'a':
                if (tiles[sugeCoord.getX()][sugeCoord.getY()-1].getMovable())
                {
                    if (((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()-1]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()-1]).addEnemy("Suge Knight", 'a',false);
                        sugeCoord.setY(sugeCoord.getY()-1);
                    }
                }
                break;
            case 'w':
                if (tiles[sugeCoord.getX()-1][sugeCoord.getY()].getMovable())
                {
                    if (((MovableSquare)tiles[sugeCoord.getX()-1][sugeCoord.getY()]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[sugeCoord.getX()-1][sugeCoord.getY()]).addEnemy("Suge Knight", 'w',false);
                        sugeCoord.setX(sugeCoord.getX()-1);
                    }
                }
                break;
            case 's':
                if (tiles[sugeCoord.getX()+1][sugeCoord.getY()].getMovable())
                {
                    if (((MovableSquare)tiles[sugeCoord.getX()+1][sugeCoord.getY()]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[sugeCoord.getX()+1][sugeCoord.getY()]).addEnemy("Suge Knight", 's',false);
                        sugeCoord.setX(sugeCoord.getX()+1);
                    }
                }
                break;
            case 'd':
                if (tiles[sugeCoord.getX()][sugeCoord.getY()+1].getMovable())
                {
                    if (((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()+1]).getEnemy() == null)
                    {
                        ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
                        ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()+1]).addEnemy("Suge Knight", 'd',false);
                        sugeCoord.setY(sugeCoord.getY()+1);
                    }
                }
                break; 
        }
    }
    //setCPUDirection is the AI to move the characters around
    public void setCPUDirection()
    {
        //if (name.equals("Policeman"))
            if (policemanCoord.getX() < pacCoord.getX() && tiles[policemanCoord.getX()+1][policemanCoord.getY()].getMovable())
            {
                //if (
                ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).addEnemy("Policeman",'s',false);
            }
            else if (policemanCoord.getX() > pacCoord.getX() && tiles[policemanCoord.getX()-1][policemanCoord.getY()].getMovable())
            {
                ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).addEnemy("Policeman",'w',false);
            }
            else if (policemanCoord.getY() < pacCoord.getY() && tiles[policemanCoord.getX()][policemanCoord.getY()+1].getMovable())
            {
                ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).addEnemy("Policeman",'d',false);
            }
            else if (policemanCoord.getY() > pacCoord.getY() && tiles[policemanCoord.getX()][policemanCoord.getY()-1].getMovable())
            {
                ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).addEnemy("Policeman",'a',false);
            }
            
        //else if (name.equals("Gangster"))
             if (gangsterCoord.getX() < pacCoord.getX() && tiles[gangsterCoord.getX()+1][gangsterCoord.getY()].getMovable())
            {
                ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).addEnemy("Gangster",'s',false);
            }
            else if (gangsterCoord.getX() > pacCoord.getX() && tiles[gangsterCoord.getX()-1][gangsterCoord.getY()].getMovable())
            {
                ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).addEnemy("Gangster",'w',false);
            }
            else if (gangsterCoord.getY() < pacCoord.getY() && tiles[gangsterCoord.getX()][gangsterCoord.getY()+1].getMovable())
            {
                ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).addEnemy("Gangster",'d',false);
            }
            else if (gangsterCoord.getY() > pacCoord.getY() && tiles[gangsterCoord.getX()][gangsterCoord.getY()-1].getMovable())
            {
                ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).addEnemy("Gangster",'a',false);
            }
        //else if (name.equals("Notorious B.I.G."))
             if (notoriousCoord.getX() < pacCoord.getX() && tiles[notoriousCoord.getX()+1][notoriousCoord.getY()].getMovable())
            {
                ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).addEnemy("Notorious B.I.G.",'s',false);
            }
            else if (notoriousCoord.getX() > pacCoord.getX() && tiles[notoriousCoord.getX()-1][notoriousCoord.getY()].getMovable())
            {
                ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).addEnemy("Notorious B.I.G.",'w',false);
            }
            else if (notoriousCoord.getY() < pacCoord.getY() && tiles[notoriousCoord.getX()][notoriousCoord.getY()+1].getMovable())
            {
                ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).addEnemy("Notorious B.I.G.",'d',false);
            }
            else if (notoriousCoord.getY() > pacCoord.getY() && tiles[notoriousCoord.getX()][notoriousCoord.getY()-1].getMovable())
            {
                ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).addEnemy("Notorious B.I.G.",'a',false);
            }
       // else if (name.equals("Suge Knight"))
             if (sugeCoord.getX() < pacCoord.getX() && tiles[sugeCoord.getX()+1][sugeCoord.getY()].getMovable())
            {
                ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).addEnemy("Suge Knight",'s',false);
            }
            else if (sugeCoord.getX() > pacCoord.getX() && tiles[sugeCoord.getX()-1][sugeCoord.getY()].getMovable())
            {
                ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).addEnemy("Suge Knight",'w',false);
            }
            else if (sugeCoord.getY() < pacCoord.getY() && tiles[sugeCoord.getX()][sugeCoord.getY()+1].getMovable())
            {
                ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).addEnemy("Suge Knight",'d',false);
            }
            else if (sugeCoord.getY() > pacCoord.getY() && tiles[sugeCoord.getX()][sugeCoord.getY()-1].getMovable())
            {
                ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
                ((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).addEnemy("Suge Knight",'a',false);
            }
    }
    
    public void bulletMove(){
    	switch(((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).getBullet().getDirection()){
    		case 'a':
    			if (tiles[bulletCoord.getX()][bulletCoord.getY()-1].getMovable()){
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).removeBullet();
    				tiles[bulletCoord.getX()][bulletCoord.getY()].draw();
    				bulletCoord.setY(bulletCoord.getY()-1);
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).addBullet('a');
    				//System.out.println("Bullet shot left");
    			}
    			else{
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).removeBullet();
                    bulletCoord = null;
    			}
    			break;
    		case 'w':
    			if (tiles[bulletCoord.getX()-1][bulletCoord.getY()].getMovable()){
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).removeBullet();
    				tiles[bulletCoord.getX()][bulletCoord.getY()].draw();
    				bulletCoord.setX(bulletCoord.getX()-1);
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).addBullet('w');
    				//System.out.println("Bullet shot up");
    			}
    			else{
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).removeBullet();
                    bulletCoord = null;
    			}
    			break;
    		case 's':
    			if (tiles[bulletCoord.getX()+1][bulletCoord.getY()-1].getMovable()){
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).removeBullet();
    				tiles[bulletCoord.getX()][bulletCoord.getY()].draw();
    				bulletCoord.setX(bulletCoord.getX()+1);
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).addBullet('s');
    				//System.out.println("Bullet shot down");
    			}
    			else{
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).removeBullet();
                    bulletCoord = null;
    			}
    			break;
    		case 'd':
    			if (tiles[bulletCoord.getX()][bulletCoord.getY()+1].getMovable()){
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).removeBullet();
    				tiles[bulletCoord.getX()][bulletCoord.getY()].draw();
    				bulletCoord.setY(bulletCoord.getY()+1);
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).addBullet('d');
    				//System.out.println("Bullet shot right");
    			}
    			else{
    				((MovableSquare)tiles[bulletCoord.getX()][bulletCoord.getY()]).removeBullet();
                    bulletCoord = null;
    			}
    			break;
                     
    	}
    }
    //this implements the bullet moving
    
   
    public void shootBullet()
    {
        if (bulletCoord == null)
            switch(((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac().getDirection())
            {
                case 'a':
                        if (tiles[pacCoord.getX()][pacCoord.getY()-1].getMovable())
                        {
                            ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()-1]).addBullet('a');
                            bulletCoord = new Coordinate(pacCoord.getX(), pacCoord.getY()-1);
                        }
                        break;
                    case 'w':
                        if (tiles[pacCoord.getX()-1][pacCoord.getY()].getMovable())
                        {
                            ((MovableSquare)tiles[pacCoord.getX()-1][pacCoord.getY()]).addBullet('w');
                            bulletCoord = new Coordinate(pacCoord.getX()-1, pacCoord.getY());
                        }
                        break;
                    case 's':
                        if (tiles[pacCoord.getX()+1][pacCoord.getY()].getMovable())
                        {
                            ((MovableSquare)tiles[pacCoord.getX()+1][pacCoord.getY()]).addBullet('s');
                            bulletCoord = new Coordinate(pacCoord.getX()+1, pacCoord.getY());
                        }
                        break;
                    case 'd':
                        if (tiles[pacCoord.getX()][pacCoord.getY()+1].getMovable())
                        {
                            ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()+1]).addBullet('d');
                            bulletCoord = new Coordinate(pacCoord.getX(), pacCoord.getY()+1);
                        }
                        break;
                }
        
        drawBoard();
    }
    public void checkDeaths(){
    	if(bulletCoord != null){
    		if(bulletCoord.getX() == gangsterCoord.getX() && bulletCoord.getY() == gangsterCoord.getY()){
    			((MovableSquare)tiles[gangsterCoord.getX()][gangsterCoord.getY()]).removeEnemy();
    			System.out.println("HIT");
    		}
    		else if(bulletCoord.getX() == notoriousCoord.getX() && bulletCoord.getY() == notoriousCoord.getY()){
    			((MovableSquare)tiles[notoriousCoord.getX()][notoriousCoord.getY()]).removeEnemy();
    			System.out.println("HIT");
    		}
    		else if(bulletCoord.getX() == sugeCoord.getX() && bulletCoord.getY() == sugeCoord.getY()){
    			((MovableSquare)tiles[sugeCoord.getX()][sugeCoord.getY()]).removeEnemy();
    			System.out.println("HIT");
    		}
    		else if(bulletCoord.getX() == policemanCoord.getX() && bulletCoord.getY() == policemanCoord.getY()){
    			((MovableSquare)tiles[policemanCoord.getX()][policemanCoord.getY()]).removeEnemy();
    			System.out.println("HIT");
    		}
    	}
    }
    public Coordinate getBulletCoord(){
    	return bulletCoord;
    }
    public Pac getPac()
    {
        return ((MovableSquare)tiles[pacCoord.getX()][pacCoord.getY()]).getPac();
    }
    public int getPacLives()
    {
        return pacLives;
    }
    public int getPacPoints()
    {
        return pacPoints;
    }
    public void drawBoard() //redraws board and tiles
    {
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[0].length; j++)
            {
                tiles[i][j].draw();
            }
        }
    }
    
}
