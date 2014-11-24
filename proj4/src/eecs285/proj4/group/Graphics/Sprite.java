package eecs285.proj4.group.Graphics;

import eecs285.proj4.group.Location;

/**
 * Created by yossier on 11/23/14.
 */
public class Sprite {
  private final int SPRITESIZE;
  private final int WIDTH;
  private final int HEIGHT;
  private Location loc;
  public int[] pixels;

  private ImageLoader spriteSheet;

  public static Sprite Ocean = new Sprite(16, 16, new Location(0,0), null, 16);


  public Sprite(int inWidth, int inHeight, Location inLoc, ImageLoader inSpriteSheet, int inSpriteSize)
  {
    SPRITESIZE = inSpriteSize;
    WIDTH = inWidth;
    HEIGHT = inHeight;

    pixels = new int[WIDTH * HEIGHT];

    loc = new Location(inLoc.getX() * SPRITESIZE, inLoc.getX() * SPRITESIZE);
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

}
