package eecs285.proj4.group;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yossier on 11/22/14.
 */
public class GamePlay implements Runnable{

  private JFrame GameFrame;
  private ImageBoard boardImage;
  private StatusPanel status;
  private JPanel boardImagePanel;

  private boolean running = false;

  public GamePlay()
  {
  	
    running = true;


    GameFrame = new JFrame();
    GameFrame.setLayout(new BorderLayout());
    GameFrame.setResizable(false);
    GameFrame.setTitle("Poseidon's Game");
    
    // add status panel and board image panel to frame
    status = new StatusPanel();
    // add ImageBoard Canvas to ImageBoard Panel
    boardImage = new ImageBoard();
    boardImagePanel = new JPanel(new FlowLayout());
    boardImagePanel.add(boardImage);
    
    GameFrame.add(status, BorderLayout.WEST);
    GameFrame.add(boardImagePanel, BorderLayout.EAST);

    GameFrame.pack();
    GameFrame.setVisible(true);
    GameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    GameFrame.setLocationRelativeTo(null);

    //run();
  }

  public void stop()
  {
    running = false;
  }


  public void run()
  {
    while (running)
    {
      boardImage.render();
    }

    //close game

  }

}
