package eecs285.proj4.group;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
  JButton okayButton;
  JDialog helpFrame;
  GameListener gameListener;
  ClientORServer network;

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
    	// I think we can always use port 8080 and it should be fine
    	String message = "Have your friend join a game with ip: " + ClientORServer.getIpAddress();
    	JOptionPane.showMessageDialog(null, message, "Start Game", JOptionPane.PLAIN_MESSAGE);

    	// Start Server (which blocks until other player connects)
    	network = new ClientORServer(ClientORServer.getIpAddress(), 8080);
    	network.startServer();
    	
    	// I feel like we should have a GamePlay class and right here call GamePlay.startGame()
    	// passing in the server/client and letting it know whether its the client or server
        remove(gameOptions);
        setTitle("Poseidon's Game");
        setContentPane(new ImageBoard());
        validate();
      }
      if(e.getSource() == joinGameButton)
      {
        String ipAddress = JOptionPane.showInputDialog(null, "Enter your friends IP address: ", 
        		"Join Game", JOptionPane.QUESTION_MESSAGE);
        // maybe do some error checking on the ipAddress passed in
        
        // Start Client 
        network = new ClientORServer(ipAddress, 8080);
    	network.startClient();
    	
    	// I feel like we should have a GamePlay class and right here call GamePlay.startGame()
    	// passing in the server/client and letting it know whether its the client or server
      }
      if(e.getSource() == helpButton)
      {
        getHelpframe();
      }
      if (e.getSource() == exitGameButton)
      {
    	// why do we use dispose vs System.exit()
        dispose();
      }
      if(e.getSource() == okayButton)
      {
        helpFrame.dispose();
      }
    }
    
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
       helpFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       helpCon.setEditable(false);
       helpCon.setWrapStyleWord(true);
       helpCon.setLineWrap(true);
       
       
       ////////READS IN HELP DOCUMENT////////////////////////////////////////
       BufferedReader helpin;
       try
       {
         helpin = new BufferedReader(new FileReader(getClass()
                 .getClassLoader().getResource("HelpDoc/HelpDocument.txt").getPath()));
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
       ///////////////////////////////////////////////////////////////////////////
       JScrollPane scrollhelp = new JScrollPane(helpCon);
       scrollhelp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       helpFrame.add(scrollhelp, BorderLayout.CENTER);
       buttons.add(okayButton);
       helpFrame.add(buttons, BorderLayout.SOUTH);
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