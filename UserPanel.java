/**
 * @(#)2PacGame.java
 *
 *
 * @author 
 * @version 1.00 2016/2/28
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.*;
import javax.swing.Timer;

public class UserPanel extends JPanel implements ArcadeFriendly, KeyListener {
	
	private boolean start;
	Timer pacTimer;
	Timer enemyTimer;
	Timer bulletTimer;
	Board board = new Board();
	private int level;
	private String highScore;
	

    public UserPanel(int x, int y) {
    	level = 1;
    	setSize(x, y);
    	this.setLayout(new GridLayout(1,1));
    	this.add(board);
    	this.addKeyListener(this);
    	board.addKeyListener(this);
        board.setFocusable(true);
        board.requestFocusInWindow();
    	pacTimer = new Timer (300, new pacListener());
    	enemyTimer = new Timer (400 - level * 10, new enemyListener());
    	bulletTimer = new Timer (200, new bulletListener());
    	highScore = "0";
    }
    
    public void nextLevel(){
    	level ++;
    }
    
    public void restartLevel(){
    	level = 0;
    }
    
    //public void actionPerformed(ActionEvent e){
    //}
    
    public void keyTyped(KeyEvent e){
    	
    }
    public void keyReleased(KeyEvent e){
    	
    }  
    
    public void keyPressed(KeyEvent e){
    	switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                //timer.start();
                //start = true;
                break;
            case KeyEvent.VK_W:
                //timer.start();
                board.changeDirection('w');
                
                break;
            case KeyEvent.VK_S:
                //timer.start();
                board.changeDirection('s');
                break;
            case KeyEvent.VK_D:
                //timer.start();
                board.changeDirection('d');
                break;
            case KeyEvent.VK_A:
                //timer.start();
                board.changeDirection('a');
                break;
            case KeyEvent.VK_SPACE:
                
                    if (board.getPac().ifCanShoot())
                    {
                    	bulletTimer.start();
                        board.shootBullet();
                    }
                
                break;
            default:
    	}
    }
    
    
    public boolean running(){
    	return start;
    }
    
    public void startGame(){
    	start = true;
    	pacTimer.start();
    	enemyTimer.start();
    	//bulletTimer.start();
    }
    
    public String getGameName(){
    	return "2Pac-Man";
    }
    
    public void pauseGame(){
    	start = false;
    	pacTimer.stop();
    	enemyTimer.stop();
    	bulletTimer.stop();
    }
    
    public String getInstructions(){
    	return "Do the pills. Dont die. Win the game.";
    }
    
    public String getCredits(){
    	return "TheRealQuan and Altaffy";
    }
    
    public String getHighScore(){
    	return highScore;
    }
    
    public void stopGame(){
    	board = new Board();
    	start = false;
    	pacTimer.stop();
    	enemyTimer.stop();
    	bulletTimer.stop();
    }
    
    public int getPoints(){
    	return board.getPacPoints();
    }
    
    private class pacListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		board.move();
    		if(board.getPacLives() <= 0){
    			stopGame();
    		}
    	}
    }
    
    private class enemyListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		board.moveEnemies();
    	}
    }
    
    private class bulletListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		board.bulletMove();
    		if(board.getBulletCoord() == null){
    			bulletTimer.stop();
    		}
    		board.checkDeaths();
    	}
    }
    
    
}