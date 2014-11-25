package eecs285.proj4.group;

import eecs285.proj4.group.Graphics.Sprite;

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

  //private Board state;

  //private boolean running = false;

  public GamePlay()
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
    boardImage.paintComponent(boardImage.getGraphics());
  }
  
//---------------------------------------------------------------  
  public JPanel getGame(){
	  return GamePanel;
  }

  public class BoardListener extends MouseAdapter
  {

    public void mouseMoved(MouseEvent e)
    {

      //Converts x pixel coordinate to x tile coordinate
      int x = (int)Math.floor(e.getX() / (Sprite.getSPRITESIZE() * ImageBoard.scale));

      //Converts y pixel coordinate to y tile coordinate
      int y = (int)Math.floor(e.getY() / (Sprite.getSPRITESIZE() * ImageBoard.scale));


      Location clickLoc = new Location( x, y);
      System.out.println("X: " + clickLoc.getX() + " Y: " + clickLoc.getY());

      Graphics g = boardImage.getGraphics();

      g.setColor(Color.RED);
     // g.drawRect(x * Sprite.getSPRITESIZE() * ImageBoard.scale, y * Sprite.getSPRITESIZE() * ImageBoard.scale, Sprite.getSPRITESIZE() * ImageBoard.scale, Sprite.getSPRITESIZE() * ImageBoard.scale);
    }

    public void mouseClicked(MouseEvent e) {
      //Converts x pixel coordinate to x tile coordinate
      int x = (int)Math.floor(e.getX() / (Sprite.getSPRITESIZE() * ImageBoard.scale));

      //Converts y pixel coordinate to y tile coordinate
      int y = (int)Math.floor(e.getY() / (Sprite.getSPRITESIZE() * ImageBoard.scale));


      Location clickLoc = new Location( x, y);
      System.out.println("X: " + clickLoc.getX() + " Y: " + clickLoc.getY());
      //Ship selectedShip;

      try
      {
        // selectedShip = identifyPlayerShip(clickLoc, board);

      }
      catch(Exception e1)
      {
        System.out.println("No Ship at Location: " + clickLoc.getX() + "," + clickLoc.getY());

      }



    }

  }

}
