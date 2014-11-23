package eecs285.proj4.group;
import java.awt.*;
import javax.swing.*;

import eecs285.proj4.group.GameGUI;

public class MainFile
{
  public static GameGUI window;

  public static void main( String[] args)
  {
    window = new GameGUI();
    //window.setMinimumSize(new Dimension(400,400));
    window.pack();
    window.setVisible(true);
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


  }
}