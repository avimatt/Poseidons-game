package eecs285.proj4.group;

import eecs285.proj4.group.Graphics.Screen;
import eecs285.proj4.group.Graphics.Sprite;
import eecs285.proj4.group.Ships.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by yossier on 11/19/14.
 */
public class ImageBoard extends JPanel{

  private static final long serialVersionUID = 1L;
  public static int width = 400;
  public static int height = 320;
  public static int scale = 2;

  private Screen screen;

//  private Board board;

  public ImageBoard(/*Board inBoard*/)
  {

    Dimension size = new Dimension(width * scale, height * scale);
    setPreferredSize(size);

   // board = inBoard;

    screen= new Screen(width, height);

    addMouseListener(new BoardListener());

  }

//---------------------------------------------------------------
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    screen.clear ();
    screen.render();

    //will draw board
    g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);

  }
  
//---------------------------------------------------------------
  /**
   * Redraws the board based on the state.
   */
  public void updateBoard(Board state)
  {

  }

//---------------------------------------------------------------
  public void drawObject()
  {

  }

//---------------------------------------------------------------
  public Ship identifyPlayerShip(Location clicked_Location, Board state) throws Exception
  {
    //will throw a you don't have a ship there exception
    return state.getShip(clicked_Location);
  }

//---------------------------------------------------------------
  public class BoardListener extends MouseAdapter
  {
	  
    public void mouseClicked(MouseEvent e) {
      //Converts x pixel coordinate to x tile coordinate
      int x = (int)Math.floor(e.getX() / (Sprite.getSPRITESIZE() * scale));

      //Converts y pixel coordinate to y tile coordinate
      int y = (int)Math.floor(e.getY() / (Sprite.getSPRITESIZE() * scale));


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
