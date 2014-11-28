package eecs285.proj4.group.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader
{
  private String imgPath;
  private final int IMGWIDTH;
  private final int IMGHEIGHT;
  public int[] pixels;

  public static ImageLoader spriteSheet = new ImageLoader("/img/gimpFiles/spritesheet.png", 256, 256);

//---------------------------------------------------------------
  public ImageLoader(String inPath, int inWidth, int inHeight)
  {
    imgPath = inPath;
    IMGWIDTH = inWidth;
    IMGHEIGHT = inHeight;

    pixels = new int[IMGWIDTH * IMGHEIGHT];

    loadImg();
  }

//---------------------------------------------------------------  
  private void loadImg()
  {
    try{
      BufferedImage img = ImageIO.read(ImageLoader.class.getResource(imgPath));
      int width = img.getWidth();
      int height = img.getHeight();
      img.getRGB(0, 0, width, height, pixels, 0, width);
    }

    catch( IOException e )
    {
      System.out.println("Image Error: " + e);
    }
  }

//---------------------------------------------------------------  
  public int getIMGWIDTH()
  {
    return IMGWIDTH;
  }

//---------------------------------------------------------------  
  public int getIMGHEIGHT()
  {
    return IMGHEIGHT;
  }

}
