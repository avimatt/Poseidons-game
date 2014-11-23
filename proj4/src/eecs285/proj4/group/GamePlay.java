package eecs285.proj4.group;

import javax.swing.*;

/**
 * Created by yossier on 11/22/14.
 */
public class GamePlay {
  public static int width = 300;
  public static int height = width * (9/16);
  public static int scale = 3;

  private JFrame GameFrame;

  private boolean running = false;

  public GamePlay(){

  }

  public void startGame()
  {
    running = true;

    GameFrame = new JFrame("Poseidon's Game");
    GameFrame.pack();
    GameFrame.setVisible(true);
    GameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




    while (running)
    {

    }

    //close game

  }

}
