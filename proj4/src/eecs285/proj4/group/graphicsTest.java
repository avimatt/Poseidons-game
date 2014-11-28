package eecs285.proj4.group;

import eecs285.proj4.group.Ships.*;


import javax.swing.*;

/**
 * Created by yossier on 11/22/14.
 */
public class graphicsTest {

  public static void main(String[] args)
  {  
	  GamePlay gamePlay = new GamePlay(new ClientORServer(ClientORServer.getIpAddress(), 8080));
	  JFrame frame = new JFrame();

    Board board = gamePlay.getPlayer().getBoard();
    Ship p1 = new PatrolBoat();
    board.addShip(p1, new Location(7,7));




	  frame.setContentPane(gamePlay.getGame());
	  frame.validate();
      frame.pack();
	  frame.setVisible(true);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	  gamePlay.run();

  }

}
