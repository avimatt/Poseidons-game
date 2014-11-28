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
        getHelpframe();
      }
      if (e.getSource() == exitGameButton)
      {
        System.exit(0);
      }
      if(e.getSource() == okayButton)
      {
        helpFrame.dispose();
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
    private void getHelpframe()
    {
       helpFrame = new JDialog();
       helpFrame.setLayout(new BorderLayout());
       JPanel buttons = new JPanel();
       buttons.setLayout(new FlowLayout());
       JTextArea helpCon = new JTextArea();
       okayButton = new JButton("OK");
       okayButton.addMouseListener(new GameListener());
       helpFrame.setTitle("Help!");
       helpFrame.setVisible(true);
       helpFrame.setSize(600,400);
       helpFrame.setResizable(false);
       helpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       helpCon.setEditable(false);
       helpCon.setWrapStyleWord(true);
       helpCon.setLineWrap(true);
       
       
       ////////READS IN HELP DOCUMENT////////////////////////////////////////
       BufferedReader helpin;
       try
       {
         helpin = new BufferedReader(new FileReader(getClass()
                 .getClassLoader().getResource("HelpDoc/HelpDocument.txt").
                 getPath()));
         String getline;
         String saveline = "";
         //gets the info. from the HelpDocument
         saveline = new StringBuilder(helpin.readLine()).reverse().toString();
         while((getline = helpin.readLine()) != null)
         {
           getline = new StringBuilder(getline).reverse().toString();
           saveline = getline+"\n"+saveline;
         }
         helpin.close();
         //reverses the string that comes in to display the help file correctly
         helpCon.setText(new StringBuilder(saveline).reverse().toString());
         helpCon.setCaretPosition(0);
       }
       catch( IOException e )
       {
        //should never happen
        e.printStackTrace();
        System.out.println("error!");
       }
       ///////////////////////////////////////////////////////////////////////
       JScrollPane scrollhelp = new JScrollPane(helpCon);
       scrollhelp.setHorizontalScrollBarPolicy
       (JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       helpFrame.add(scrollhelp, BorderLayout.CENTER);
       buttons.add(okayButton);
       helpFrame.add(buttons, BorderLayout.SOUTH);
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