package eecs285.proj4.group.Graphics;

import java.util.Random;

/**
 * Created by yossier on 11/23/14.
 */
public class Screen {
  private int width, height;
  public int[] pixels;
  public int[] tiles;

  private Random random = new Random();
  
  public Screen(int inWidth, int inHeight)
  {
    width = inWidth;
    height =inHeight;
    pixels = new int[width * height];
    tiles = new int[64 * 64];

    for( int i = 0; i < tiles.length; ++i)
    {
      tiles[i] = random.nextInt(0xffffff);
    }

  }

  public void render()
  {
    for (int y = 0; y < height; ++y)
    {
      int yy = y;
//      if(yy < 0 || yy >= height) break;
      for (int x = 0; x < width; ++x)
      {
        int xx = x ;
//        if(xx < 0 || xx >= width) break;
        int tileIndex = ((xx >> 4)&63) + ((yy >> 4)&63) * 64;
        pixels[x + y * width] = Sprite.SUBMARINE.pixels[(x & 15) + (y & 15) * Sprite.SUBMARINE.getWIDTH()];
      }
    }
  }

  public void clear()
  {
    for (int i = 0; i < pixels.length; ++i)
    {
      pixels[i] = 0x000000;
    }
  }


}
