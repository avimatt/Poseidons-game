package eecs285.proj4.group;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class GameGUI
{
  JFrame gameOptionsFrame;
  JFrame gameScreen;
  GamePlay game;
  JPanel gameOptions;
  JButton startGameButton;
  JButton joinGameButton;
  JButton helpButton;
  JButton exitGameButton;
  JButton okayButton;
  JDialog helpFrame;
  GameListener gameListener;
  ClientORServer network;
//---------------------------------------------------------------
  public GameGUI()
  {

    gameOptionsFrame = new JFrame("Start Menu");
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
    gameOptionsFrame.add(backg);
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

    gameOptionsFrame.setLocationRelativeTo(null);
    backg.add(gameOptions,BorderLayout.SOUTH);
    gameOptionsFrame.pack();
    gameOptionsFrame.setVisible(true);
    gameOptionsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


  }
//---------------------------------------------------------------
  public class GameListener extends MouseAdapter
  {
	//---------------------------------------------------------------
    public void mouseClicked(MouseEvent e)
    {
      if (e.getSource() == startGameButton)
      {
	      // I think we can always use port 8080 and it should be fine	      
	      // Start Server (which blocks until other player connects)
	      network = new ClientORServer(ClientORServer.getIpAddress(), 8000);
	      ServerThread thread = new ServerThread();
	      thread.start();
	      
	      //passing in the server/client and letting it know
	      //whether its the client or the server
	      createAndDisplayGame();
	      @SuppressWarnings("unused")
		StartServerWindow ssw = new StartServerWindow(game);
      }
      if(e.getSource() == joinGameButton)
      {
        String ipAddress = JOptionPane.showInputDialog(null, "Enter your "
            + "friends IP address: ", 
            "Join Game", JOptionPane.QUESTION_MESSAGE);
        // maybe do some error checking on the ipAddress passed in
        
        // Start Client 
        network = new ClientORServer(ipAddress, 8000);
        network.startClient();
        
        // passing in the server/client and letting it know whether its the 
        //client or the server 
        createAndDisplayGame();
        @SuppressWarnings("unused")
  	  	SetupWindow sw = new SetupWindow(game, false);
      }
      if(e.getSource() == helpButton)
      {
        new HelpGUI();
      }
      if (e.getSource() == exitGameButton)
      {
        System.exit(0);
      }
      
    }
  //---------------------------------------------------------------
    private void createAndDisplayGame()
    {
      gameScreen = new JFrame("Poseidon's Game");
      game = new GamePlay(network);
      gameScreen.add(game.getGame());
      gameScreen.pack();
      gameOptionsFrame.setVisible(false);
      gameScreen.setVisible(true);
      gameScreen.setResizable(false);
      gameScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      game.run();
    }
  //---------------------------------------------------------------
  
    void saySomething(String eventDescription, MouseEvent e)
    {

    }
  //---------------------------------------------------------------
    class ServerThread extends Thread {
    	public void run(){
    		network.startServer();
    	}
    }
  }
}