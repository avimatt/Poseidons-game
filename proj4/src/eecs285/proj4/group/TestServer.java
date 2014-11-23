package eecs285.proj4.group;

import javax.swing.*;
import java.awt.*;

public class TestServer {

	public static void main(String[] args) {
		ClientORServer server = new ClientORServer(ClientORServer.getIpAddress(), 8080);
		
		System.out.println(ClientORServer.getIpAddress());
		
		server.startServer();		
		server.readMessage();
	
	}

  @SuppressWarnings("serial")
  public static class StatusPanel extends JPanel
  {
    JPanel mainPan;
    JPanel totalHealthPan;
    JLabel totalHealthLabel;
    JTextField totalHealthField;
    JLabel currentShipLabel;

    public StatusPanel()
    {
      mainPan = new JPanel (new GridLayout(7,1));

      totalHealthPan = new JPanel (new FlowLayout());
      totalHealthLabel = new JLabel ("Total Fleet Health: ");
      //need a function getTotalFleetHealth that returns the total health points
      totalHealthField= new JTextField(10);
      totalHealthField.setText("PutHealthHere" + "/100");
      getHealthColor(totalHealthField);
      totalHealthField.setEditable(false);

      //need a getShipType function that returns the ship string
      currentShipLabel = new JLabel ("Current Ship" + "GetShipType");





    }

    public static void getHealthColor(JTextField healthField)
    {
      /*  if (getTotalHealth() >= 70)
      {
        helthField.setBackground(Color.GREEN);
      }
      else if (getTotalHealth() < 70 && getTotalHealth() >= 30 )
      {
        healthField.setBackground(Color.YELLOW);
      }
      else
      {
        healthField.setBackground(Color.RED);
      }
  */
    }
  }
}
