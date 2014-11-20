package eecs285.proj4.group;

import eecs285.proj4.group.Ships.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by yossier on 11/19/14.
 */
public class ImageBoard extends Canvas{
  private BufferedImage boardImage;
  private MouseListener canvasListener;
  private Graphics2D boardGraphics;

  public ImageBoard()
  {
    super();
//    boardImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);

  }

  public void updateBoard(Board state)
  {

  }

  public void moveBoat()
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



}
