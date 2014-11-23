package eecs285.proj4.group.Graphics;

/**
 * Created by yossier on 11/23/14.
 */
public class Screen {
  private int width, height;
  public int[] pixels;

  private int counter = 0;
  private int min = 0;
  
  public Screen(int inWidth, int inHeight)
  {
    width = inWidth;
    height =inHeight;
    pixels = new int[width * height];

  }

  public void render()
  {
    if(((counter++) % 150) == 0)
      min++;

    for (int y = 0; y < height; ++y)
    {
      for (int x = 0; x < width; ++x)
      {
        pixels[min%width + min%height * width] = 0xff00ff;
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
