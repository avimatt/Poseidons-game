package eecs285.proj4.group;

import javax.swing.JFrame;

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
	frame.setVisible(true);
	frame.setSize(1000, 550);
	gamePlay.run();
    //GamePlay gamePlay = new GamePlay();
   // gamePlay.start();

  }

}
