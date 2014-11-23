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
    tiles = new int[32 * 32];

    for( int i = 0; i < tiles.length; ++i)
    {
      tiles[i] = random.nextInt(0xffffff);
    }

  }

  public void render()
  {
    for (int y = 0; y < height; ++y)
    {
      if(y >= height) break;
      for (int x = 0; x < width; ++x)
      {
        if(x >= width) break;
        int tileIndex = (x >> 4) + (y >> 4) * 64;
        pixels[x + y * width] = tiles[tileIndex];
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
