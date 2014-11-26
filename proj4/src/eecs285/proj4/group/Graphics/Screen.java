package eecs285.proj4.group.Graphics;

import eecs285.proj4.group.Location;
import eecs285.proj4.group.Ships.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

/**
 * Created by yossier on 11/23/14.
 */
public class Screen extends BufferedImage{
  private int width, height;
  public int[] pixels;
  public int[] tiles;

  private Random random = new Random();
  private Graphics g;

//---------------------------------------------------------------  
  public Screen(int inWidth, int inHeight)
  {
    super(inWidth, inHeight, BufferedImage.TYPE_INT_RGB);
    width = inWidth;
    height =inHeight;
    pixels = ((DataBufferInt)this.getRaster().getDataBuffer()).getData();

    g = getGraphics();

    tiles = new int[64 * 64];

    for( int i = 0; i < tiles.length; ++i)
    {
      tiles[i] = random.nextInt(0xffffff);
    }

  }

//---------------------------------------------------------------  
  public void render()
  {
    for (int y = 0; y < height; y += Sprite.FOGTILE.getHEIGHT())
    {

      for (int x = 0; x < width; x += Sprite.FOGTILE.getWIDTH())
      {
        g.drawImage(Sprite.FOGTILE, x, y, Sprite.FOGTILE.getWIDTH(), Sprite.FOGTILE.getHEIGHT(), null);
      }
    }

      Ship test = new Submarine();
      test.setCurrentLoaction(new Location(3,2));

      render(test);
  }



  public void render(Ship ship){
    Location shipLoc = ship.getCurrentLocation();
    Sprite shipSprite = ship.getSprite();


    int xStart = shipLoc.getX() * Sprite.getSPRITESIZE();
    int yStart = shipLoc.getY() * Sprite.getSPRITESIZE();

    int xWidth = shipSprite.getWIDTH();
    int yHeight = shipSprite.getHEIGHT();

    g.drawImage(shipSprite, xStart, yStart , xWidth, yHeight, null);

  }

//---------------------------------------------------------------
  public void clear()
  {
    for (int i = 0; i < pixels.length; ++i)
    {
      pixels[i] = 0x000000;
    }
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }


}
