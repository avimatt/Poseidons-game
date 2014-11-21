package eecs285.proj4.group;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameGUI extends JFrame
{
  ImageBoard imageBoard;
  JPanel gameOptions;
  JButton startGameButton;
  JButton joinGameButton;
  JButton helpButton;
  JButton exitGameButton;
  GameListener gameListener;

  public GameGUI()
  {

    super("Start Menu");
    //creates new Jpanel to put buttons in
    //setSize(400,800);
    gameOptions = new JPanel();
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

    startGameButton = new StartMenuButton("Start Game");
    joinGameButton = new StartMenuButton("Join Game");
    helpButton = new StartMenuButton("Help");
    exitGameButton = new StartMenuButton("Exit Game");
    gameListener = new GameListener();
    startGameButton.addMouseListener(new GameListener());
    joinGameButton.addMouseListener(new GameListener());
    helpButton.addMouseListener(new GameListener());
    exitGameButton.addMouseListener(new GameListener());



    gameOptions.add(startGameButton);
    gameOptions.add(joinGameButton);
    gameOptions.add(helpButton);
    gameOptions.add(exitGameButton);
    //gameOptions.add(new JLabel(" "));

    backg.add(gameOptions,BorderLayout.SOUTH);


  }

  public class GameListener implements MouseListener
  {
    public void mouseClicked(MouseEvent e)
    {
      if (e.getSource() == startGameButton)
      {
        setContentPane(new ImageBoard());
      }
      if (e.getSource() == exitGameButton)
      {
        dispose();
      }
    }
    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    void saySomething(String eventDescription, MouseEvent e)
    {

    }
  }

}