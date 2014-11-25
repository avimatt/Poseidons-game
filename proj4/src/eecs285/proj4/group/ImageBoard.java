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

  public void paintMouse(Graphics g, int x, int y)
  {

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


}
