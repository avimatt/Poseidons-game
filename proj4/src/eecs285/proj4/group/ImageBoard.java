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
public class ImageBoard extends JPanel{
  private BufferedImage boardImage;
  private MouseListener boardListener;
  private Graphics2D boardGraphics;

  public ImageBoard()
  {
    super(new FlowLayout());
    boardImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_ARGB);
    boardGraphics = boardImage.createGraphics();
    BoardListener boardListner = new BoardListener();

    super.paint(boardGraphics);

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

  public class BoardListener implements MouseListener
  {
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
  }

}
