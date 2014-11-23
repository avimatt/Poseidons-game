package eecs285.proj4.group;

import eecs285.proj4.group.Ships.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by yossier on 11/19/14.
 */
public class ImageBoard extends Canvas{

  private static final long serialVersionUID = 1L;

  private BufferedImage boardImage;
  private MouseListener boardListener;
  private Graphics2D boardGraphics;


  public static int width = 300;
  public static int height = width * (9/16);
  public static int scale = 3;

  public ImageBoard()
  {

    Dimension size = new Dimension(width * scale, height * scale);
    setPreferredSize(size);

    BoardListener boardListner = new BoardListener();;

  }


  public void render()
  {
    BufferStrategy bufferStrategy= getBufferStrategy();
    if(bufferStrategy == null)
    {
      createBufferStrategy(3);
      return;
    }

    Graphics graphics = bufferStrategy.getDrawGraphics();

    graphics.setColor(Color.blue);
    graphics.fillRect(0, 0, getWidth(), getHeight());

    graphics.dispose();
    bufferStrategy.show();

  }


  /**
   * Redraws the board based on the state.
   */
  public void updateBoard(Board state)
  {

  }


  public void drawObject()
  {

  }



  public Ship identifyPlayerShip(Location clicked_Location, Board state)
  {
    //will throw a you don't have a ship there exception
    return state.getShip(clicked_Location);
  }

  public class BoardListener implements MouseListener
  {
    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
  }

}
