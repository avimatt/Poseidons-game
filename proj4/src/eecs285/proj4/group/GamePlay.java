package eecs285.proj4.group;

import eecs285.proj4.group.Graphics.Screen;
import eecs285.proj4.group.Graphics.Sprite;
import eecs285.proj4.group.Ships.Ship;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePlay implements Runnable{

  private JPanel GamePanel;
  private ImageBoard boardImage;
  private StatusPanel status;
  private JPanel boardImagePanel;
  public static boolean update = false;
  
  @SuppressWarnings("unused")
  private boolean isServer;

  private ClientORServer network;
  private Player player;
  //private Board state;

  public GamePlay(ClientORServer networkIn)
  {
  	    
    GamePanel = new JPanel();
    GamePanel.setLayout(new BorderLayout());
    
    player = new Player();
    
    // add status panel and board image panel to frame
    status = new StatusPanel(this, player);
    // add ImageBoard Canvas to ImageBoard Panel
    boardImage = new ImageBoard();
    boardImagePanel = new JPanel(new FlowLayout());

    BoardListener mouseTracker = new BoardListener();

    boardImage.addMouseListener(mouseTracker);
    boardImage.addMouseMotionListener(mouseTracker);

    boardImagePanel.add(boardImage);
    
    GamePanel.add(status, BorderLayout.WEST);
    GamePanel.add(boardImagePanel, BorderLayout.EAST);
    
    network = networkIn;
  }

//---------------------------------------------------------------
  public void run()
  {
    boardImage.updateBoard(player);
    boardImage.paintComponent(boardImage.getGraphics());
  }
  
//---------------------------------------------------------------  
  public JPanel getGame(){
	  return GamePanel;
  }
  
//---------------------------------------------------------------  
  public ClientORServer getNetwork(){
	  return network;
  }

//---------------------------------------------------------------  
  public Player getPlayer(){
	  return player;
  }

//---------------------------------------------------------------  
  public StatusPanel getStatusPanel(){
	  return status;
  }

//---------------------------------------------------------------  
  public void setIsServer(boolean server){
	  isServer = server;
  }
  
//---------------------------------------------------------------  
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

//---------------------------------------------------------------  
  public ImageBoard getBoardImage()
  {
    return boardImage;
  }
  
  public class BoardListener extends MouseAdapter
  {

	//---------------------------------------------------------------
    public void mouseMoved(MouseEvent e)
    {


      boardImage.updateBoard(player);
      //Converts x pixel coordinate to x tile coordinate
      int x = (int)Math.floor(e.getX() / (Sprite.getSPRITESIZE() * ImageBoard.scale));

      //Converts y pixel coordinate to y tile coordinate
      int y = (int)Math.floor(e.getY() / (Sprite.getSPRITESIZE() * ImageBoard.scale));


	  //Location movedLoc = new Location( x, y);
      //System.out.println("Moved: X: " + movedLoc.getX() + " Y: " + movedLoc.getY());

      //GamePlay.update = true;

      Graphics g = boardImage.getScreen().getGraphics();

      g.setColor(Color.RED);
      g.drawRect(x * Sprite.getSPRITESIZE(), y * Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE(), Sprite.getSPRITESIZE());
      boardImage.paintComponent(boardImage.getGraphics());

    }

    //---------------------------------------------------------------    
    public void mouseClicked(MouseEvent e) {
      //Converts x pixel coordinate to x tile coordinate
      int x = (int)Math.floor(e.getX() / (Sprite.getSPRITESIZE() * ImageBoard.scale));

      //Converts y pixel coordinate to y tile coordinate
      int y = (int)Math.floor(e.getY() / (Sprite.getSPRITESIZE() * ImageBoard.scale));


      Location clickLoc = new Location( x, y);
      System.out.println("Clicked X: " + clickLoc.getX() + " Y: " + clickLoc.getY());
      Ship selectedShip;

      Screen screen = boardImage.getScreen();

      // if the move button has been pressed
      if(screen.getMoveSelected())
      {
        if(player.getBoard().moveShip(screen.getPanelSelecetedShip(), clickLoc))
        {
          screen.setMove(false);
        }

      }

      // if the attack button has been pressed
      else if(screen.getAttackSelected())
      {

      }

      else
      {
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

}
