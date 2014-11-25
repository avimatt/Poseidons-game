package eecs285.proj4.group.Graphics;

import eecs285.proj4.group.Location;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by yossier on 11/23/14.
 */
public class Sprite extends BufferedImage{
  private final static int SPRITESIZE = 16;
  private final int WIDTH;
  private final int HEIGHT;
  private Location loc;
  public int[] pixels;

  private ImageLoader spriteSheet;



  public static Sprite OCEANTILE = new Sprite(16, 16, new Location(0,0), ImageLoader.spriteSheet);
  public static Sprite FOGTILE = new Sprite(16, 16, new Location(1,0), ImageLoader.spriteSheet);
  public static Sprite PATROLBOAT = new Sprite(16, 16, new Location(0,2), ImageLoader.spriteSheet);
  public static Sprite DESTROYER = new Sprite(32, 16, new Location(4,2), ImageLoader.spriteSheet);
  public static Sprite BATTLESHIP = new Sprite(32, 16, new Location(2, 2),ImageLoader.spriteSheet);
  public static Sprite SUBMARINE = new Sprite(16, 16, new Location(1,2), ImageLoader.spriteSheet);
  public static Sprite AIRCRAFTCARRIER = new Sprite(48, 16, new Location(6,2), ImageLoader.spriteSheet);


  public Sprite(int inWidth, int inHeight, Location inLoc, ImageLoader inSpriteSheet)
  {
    super(inWidth, inHeight, BufferedImage.TYPE_INT_RGB);

    WIDTH = inWidth;
    HEIGHT = inHeight;


    pixels = ((DataBufferInt)this.getRaster().getDataBuffer()).getData();

    loc = new Location(inLoc.getX() * SPRITESIZE, inLoc.getY() * SPRITESIZE);
    spriteSheet = inSpriteSheet;

    load();
  }

  private void load()
  {
    for(int y = 0; y < HEIGHT; ++y)
    {
      for(int x = 0; x < WIDTH; ++x)
      {
        pixels[x + y * WIDTH] = spriteSheet.pixels[ (x + loc.getX()) + (y + loc.getY()) * spriteSheet.getIMGWIDTH()];
      }

    }
  }

  public int getWIDTH()
  {
    return WIDTH;
  }

  public int getHEIGHT()
  {
    return HEIGHT;
  }

  public static int getSPRITESIZE()
  {
    return SPRITESIZE;
  }

}
