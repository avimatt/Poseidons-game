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
    //creates new Jpanel to put buttons in
    JPanel gameOptions = new JPanel();
    gameOptions.setLayout(new GridLayout(5,1));
    //makes the Jpanel see-thru so you can see the picture
    gameOptions.setOpaque(false);
    //adds the picture to the Jframe
    //source of photo: https://i1.creativecow.net/u/1027/
    //gravity-battleship-1.jpg
    JLabel backg = new JLabel(new ImageIcon(getClass()
        .getClassLoader().getResource("images/battleship.jpg")));
    add(backg);
    backg.setLayout(new BorderLayout());
    
    //adds buttons to the Jpanel
    startGameButton = new StartMenuButton("Start Game");
    joinGameButton = new StartMenuButton("Join Game");
    helpButton = new StartMenuButton("Help");
    exitGameButton = new StartMenuButton("Exit Game");
   
    gameOptions.add(startGameButton);
    gameOptions.add(joinGameButton);
    gameOptions.add(helpButton);
    gameOptions.add(exitGameButton);
   
    backg.add(gameOptions,BorderLayout.SOUTH);
  
    
  }
  
}
