package eecs285.proj4.group;

import java.awt.*;

import javax.swing.*;

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
    startGameButton = new JButton("Start Game");
    Font buttonFont = new Font("Algerian", Font.BOLD, 24);
    startGameButton.setForeground(Color.ORANGE);
    startGameButton.setFont(buttonFont);
    startGameButton.setOpaque(false);
    startGameButton.setBorderPainted(false);
    startGameButton.setContentAreaFilled(false);
    
    joinGameButton = new JButton("Join Game");
    joinGameButton.setForeground(Color.ORANGE);
    joinGameButton.setFont(buttonFont);
    joinGameButton.setOpaque(false);
    joinGameButton.setBorderPainted(false);
    joinGameButton.setContentAreaFilled(false);
    
    helpButton = new JButton("Help");
    helpButton.setForeground(Color.ORANGE);
    helpButton.setFont(buttonFont);
    helpButton.setOpaque(false);
    helpButton.setBorderPainted(false);
    helpButton.setContentAreaFilled(false);
    
    exitGameButton = new JButton("Exit Game");
    exitGameButton.setForeground(Color.ORANGE);
    exitGameButton.setFont(buttonFont);
    exitGameButton.setOpaque(false);
    exitGameButton.setBorderPainted(false);
    exitGameButton.setContentAreaFilled(false);
    
    gameOptions.add(startGameButton);
    gameOptions.add(joinGameButton);
    gameOptions.add(helpButton);
    gameOptions.add(exitGameButton);
    //gameOptions.add(new JLabel(" "));

    backg.add(gameOptions,BorderLayout.SOUTH);
  
    
  }
  
  
  
  
  
  
  
  
}
