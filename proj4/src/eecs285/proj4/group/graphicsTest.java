package eecs285.proj4.group;

import javax.swing.*;

/**
 * Created by yossier on 11/22/14.
 */
public class graphicsTest {

  public static void main(String[] args)
  {
	  GamePlay gamePlay = new GamePlay();
	  JFrame frame = new JFrame();
	  frame.setContentPane(gamePlay.getGame());
	  frame.validate();
    frame.pack();
	  frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	  gamePlay.run();
    //GamePlay gamePlay = new GamePlay();
   // gamePlay.start();

  }

}
