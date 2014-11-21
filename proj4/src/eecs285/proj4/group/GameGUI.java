package eecs285.proj4.group;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameGUI extends JFrame
{
  JButton startGameButton;
  JButton joinGameButton;
  JButton helpButton;
  JButton exitGameButton;
 
  public GameGUI()
  {
    
    super("Start Menu");
    //setSize(400,800);
    JPanel gameOptions = new JPanel();
    gameOptions.setLayout(new GridLayout(5,1));
    gameOptions.setOpaque(false);
    JLabel backg = new JLabel(new ImageIcon(getClass()
        .getClassLoader().getResource("images/battleship.jpg")));
    add(backg);
    backg.setLayout(new BorderLayout());
    
    startGameButton = new StartMenuButton("Start Game");
    joinGameButton = new StartMenuButton("Join Game");
    helpButton = new StartMenuButton("Help");
    exitGameButton = new StartMenuButton("Exit Game");
   
    gameOptions.add(startGameButton);
    gameOptions.add(joinGameButton);
    gameOptions.add(helpButton);
    gameOptions.add(exitGameButton);
    //gameOptions.add(new JLabel(" "));

    backg.add(gameOptions,BorderLayout.SOUTH);
  
    
  }
  
}
