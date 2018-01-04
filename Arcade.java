

import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class Arcade extends JFrame
{
  public Arcade()
  {
    super("AP Java Arcade");

    ArcadeFriendly game = new UserPanel(600, 450);
    GameStats display = new GameStats(game);

    ControlPanel controls = new ControlPanel(game, display);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 5, 0, 5));
    panel.add(display, BorderLayout.NORTH);
    panel.add((JPanel)game, BorderLayout.CENTER);
    panel.add(controls, BorderLayout.SOUTH);

    Container c = getContentPane();
    c.add(panel, BorderLayout.CENTER);
  }

  public static void main(String[] args)
  {
    Arcade window = new Arcade();
    window.setBounds(100, 100, 600, 600);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setVisible(true);
  }
}

