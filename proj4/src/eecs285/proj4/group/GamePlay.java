package eecs285.proj4.group;

import javax.swing.*;

/**
 * Created by yossier on 11/22/14.
 */
public class GamePlay {

  private JFrame GameFrame;
  private ImageBoard boardImage;

  private boolean running = false;

  public GamePlay(){

  }

  public void startGame()
  {
    running = true;

    boardImage = new ImageBoard();

    GameFrame = new JFrame();

    GameFrame.setResizable(false);
    GameFrame.setTitle("Poseidon's Game");

    GameFrame.add(boardImage);

    GameFrame.pack();
    GameFrame.setVisible(true);
    GameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    GameFrame.setLocation(null);


  }


  public void run()
  {
    while (running)
    {

    }

    //close game

  }

}
