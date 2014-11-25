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

  //private Board state;

  //private boolean running = false;

  public GamePlay()
  {
  	
    //running = true;
    
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
  
//---------------------------------------------------------------
  // What is this for? Do we still need this variable
  /*public void stop()
  {
    running = false;
  }*/

//---------------------------------------------------------------
  public void run()
  {
    boardImage.paintComponent(boardImage.getGraphics());
  }
  
//---------------------------------------------------------------  
  public JPanel getGame(){
	  return GamePanel;
  }

}
