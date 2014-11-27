package eecs285.proj4.group;

import eecs285.proj4.group.Graphics.Sprite;
import eecs285.proj4.group.Ships.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by yossier on 11/22/14.
 */
public class GamePlay implements Runnable{

  private JPanel GamePanel;
  private ImageBoard boardImage;
  private StatusPanel status;
  private JPanel boardImagePanel;
  public static boolean update = false;

  private ClientORServer network;
  private Player player;
  //private Board state;

  //private boolean running = false;

  public GamePlay(ClientORServer networkIn)
  {
  	
    //running = true;
    
    GamePanel = new JPanel();
    GamePanel.setLayout(new BorderLayout());
    
    // add status panel and board image panel to frame
    status = new StatusPanel();
    // add ImageBoard Canvas to ImageBoard Panel
    boardImage = new ImageBoard();
    boardImagePanel = new JPanel(new FlowLayout());

    BoardListener mouseTracker = new BoardListener();

    boardImage.addMouseListener(mouseTracker);
    boardImage.addMouseMotionListener(mouseTracker);

    boardImagePanel.add(boardImage);
    
    GamePanel.add(status, BorderLayout.WEST);
    GamePanel.add(boardImagePanel, BorderLayout.EAST);
    
    player = new Player();
    network = networkIn;
  }
  
//---------------------------------------------------------------
  // What is this for? Do we still need this variable
  /*public void stop()
  {
    running = false;
  }*/

//---------------------------------------------------------------
  public void run()
  {
    boardImage.updateBoard(player);
    boardImage.paintComponent(boardImage.getGraphics());
/*
    while (true)
    {
        boardImage.updateBoard();
        boardImage.paintComponent(boardImage.getGraphics());
      }
      */
  }
  
//---------------------------------------------------------------  
  public JPanel getGame(){
	  return GamePanel;
  }
  
  public ClientORServer getNetwork(){
	  return network;
  }
  
  public Player getPlayer(){
	  return player;
  }

  public void displaySetupOptions(char option, boolean server){
	  if(option == 'A'){
		  boardImage.getScreen().renderOptionA(player, server);
		  boardImage.paintComponent(boardImage.getGraphics());
	  }
	  if(option == 'B'){
		  boardImage.getScreen().renderOptionB(player, server);
		  boardImage.paintComponent(boardImage.getGraphics());
	  }
	  if(option == 'C'){
		  boardImage.getScreen().renderOptionC(player, server);
		  boardImage.paintComponent(boardImage.getGraphics());
	  }
  }
  
  public class BoardListener extends MouseAdapter
  {

    public void mouseMoved(MouseEvent e)
    {


      boardImage.updateBoard(player);
      //Converts x pixel coordinate to x tile coordinate
      int x = (int)Math.floor(e.getX() / (Sprite.getSPRITESIZE() * ImageBoard.scale));

      //Converts y pixel coordinate to y tile coordinate
      int y = (int)Math.floor(e.getY() / (Sprite.getSPRITESIZE() * ImageBoard.scale));


      Location clickLoc = new Location( x, y);
      System.out.println("Moved: X: " + clickLoc.getX() + " Y: " + clickLoc.getY());

      //GamePlay.update = true;

      Graphics g = boardImage.getScreen().getGraphics();

      g.setColor(Color.RED);
      g.drawRect(x * Sprite.getSPRITESIZE(), y * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
      boardImage.paintComponent(boardImage.getGraphics());

    }

    public void mouseClicked(MouseEvent e) {
      //Converts x pixel coordinate to x tile coordinate
      int x = (int)Math.floor(e.getX() / (Sprite.getSPRITESIZE() * ImageBoard.scale));

      //Converts y pixel coordinate to y tile coordinate
      int y = (int)Math.floor(e.getY() / (Sprite.getSPRITESIZE() * ImageBoard.scale));


      Location clickLoc = new Location( x, y);
      System.out.println("Clicked X: " + clickLoc.getX() + " Y: " + clickLoc.getY());
      Ship selectedShip;

      try
      {
         selectedShip = boardImage.identifyPlayerShip(clickLoc, player.getBoard());
         status.updateStatusPanel(selectedShip);
      }
      catch(Exception e1)
      {
        System.out.println("No Ship at Location: " + clickLoc.getX() + "," + clickLoc.getY());

      }



    }

  }

}
