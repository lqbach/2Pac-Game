// Represents a display panel for a Craps table

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameStats extends JPanel
{
  private JTextField gameNameText, currentHighScorer, currentHighScore;
  private int yourScore;
  private JLabel yourScoreText;

  // Constructor
  public GameStats(ArcadeFriendly t)
  {
    super(new GridLayout(2, 4, 10, 0));
    setBorder(new EmptyBorder(0, 0, 5, 0));
    Font gameNameFont = new Font("Monospaced", Font.BOLD, 24);

    JLabel gName = new JLabel(" " + t.getGameName());
    //yourScoreText = new"" + 0;
    gName.setForeground(Color.red);
    gName.setFont(gameNameFont);
 	  add(gName);
    add(new JLabel(" Current High Score:   " + t.getHighScore()));
    //add(new JLabel("    Points:"));
    add(new JLabel(" "));
    yourScoreText = new JLabel(" Your Final Score: " + 0);
    //add(new JLabel("    Your Final Score:"));
    add(yourScoreText);
    Font displayFont = new Font("Monospaced", Font.BOLD, 16);

    /*yourScoreText = new JTextField("  0", 3);
    yourScoreText.setFont(displayFont);
    yourScoreText.setEditable(false);
    yourScoreText.setBackground(Color.WHITE);
    add(yourScoreText);
*/
     }

  // Updates this display, based on the result and
  // "point" in the previous roll
  public void update(int points)
  {
  	
  	yourScoreText.setText(" Your Final Score: " + points);
 // 	yourScoreText.setForeground(Color.BLUE);
  	if(points > 300){
  	yourScoreText.setForeground(Color.BLUE);
  	String s = (String)JOptionPane.showInputDialog(this, "You are the new high scorer. Congratulations!\n Enter your name: ", "High Score", JOptionPane.PLAIN_MESSAGE, null, null,"name");
  		System.out.println("you are the new high scorer");}
  	else
  		System.out.println("You did not beat the high score");
  
   /* if (result > 0)
    {
      wonCount++;
      wonText.setText("  " + wonCount);
      pointText.setText("");
      pointText.setBackground(Color.DARK_GRAY);
    }
    else if (result < 0)
    {
      lostCount++;
      lostText.setText("  " + lostCount);
      pointText.setText("");
      pointText.setBackground(Color.DARK_GRAY);
    }
    else
    {
      pointText.setText("  " + point);
      pointText.setBackground(Color.YELLOW);
    }*/
  }
}
