package eecs285.proj4.group;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yossier on 11/22/14.
 */
public class GamePlay implements Runnable{

  private JPanel GamePanel;
  private ImageBoard boardImage;
  private StatusPanel status;
  private JPanel boardImagePanel;

  private boolean running = false;

  public GamePlay()
  {
  	
    running = true;


    GamePanel = new JPanel();
    GamePanel.setLayout(new BorderLayout());
    
    // add status panel and board image panel to frame
    status = new StatusPanel();
    // add ImageBoard Canvas to ImageBoard Panel
    boardImage = new ImageBoard();
    boardImagePanel = new JPanel(new FlowLayout());
    boardImagePanel.add(boardImage);
    
    GamePanel.add(status, BorderLayout.WEST);
    GamePanel.add(boardImagePanel, BorderLayout.EAST);
  }

  public void stop()
  {
    running = false;
  }


  public void run()
  {
    long lastTime = System.nanoTime();
    final double ns = 1000000000.0 / 60.0;
    double delta = 0;
    long now;

    int x = 0;

    while (running)
    {
      /*now = System.nanoTime();

      delta = (now - lastTime) / ns;

      lastTime = now;
      if(delta >= 1)
      {
      */
      //if(x++ <= 1000)
        boardImage.render();
        //delta = 0;
      //}
    }

    //close game

  }
  
  public JPanel getGame(){
	  return GamePanel;
  }

}
