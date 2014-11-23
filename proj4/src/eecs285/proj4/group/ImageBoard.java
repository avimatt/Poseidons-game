package eecs285.proj4.group;

import eecs285.proj4.group.Ships.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by yossier on 11/19/14.
 */
public class ImageBoard extends Canvas{
  private BufferedImage boardImage;
  private MouseListener boardListener;
  private Graphics2D boardGraphics;

  public ImageBoard()
  {
    boardImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
    boardGraphics = boardImage.createGraphics();
    BoardListener boardListner = new BoardListener();
    this.addMouseListener(boardListner);
    super.paint(boardGraphics);

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
