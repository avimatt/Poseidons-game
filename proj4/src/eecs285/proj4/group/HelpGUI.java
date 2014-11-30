package eecs285.proj4.group;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class HelpGUI
{
  JDialog helpFrame;
  public HelpGUI()
  {
     helpFrame = new JDialog();
     helpFrame.setLayout(new BorderLayout());
     JPanel buttons = new JPanel();
     buttons.setLayout(new FlowLayout());
     JTextArea helpCon = new JTextArea();
     JButton okayButton = new JButton("OK");
     okayButton.addActionListener(new GameListener());
     helpFrame.setTitle("Help!");
     helpFrame.setVisible(true);
     helpFrame.setSize(600,400);
     helpFrame.setResizable(false);
     helpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
     helpCon.setEditable(false);
     helpCon.setWrapStyleWord(true);
     helpCon.setLineWrap(true);
     
     
     ////////READS IN HELP DOCUMENT////////////////////////////////////////
     BufferedReader helpin;
     try
     {
       helpin = new BufferedReader(new FileReader(getClass()
               .getClassLoader().getResource("HelpDoc/HelpDocument.txt").
               getPath()));
       String getline;
       String saveline = "";
       //gets the info. from the HelpDocument
       saveline = new StringBuilder(helpin.readLine()).reverse().toString();
       while((getline = helpin.readLine()) != null)
       {
         getline = new StringBuilder(getline).reverse().toString();
         saveline = getline+"\n"+saveline;
       }
       helpin.close();
       //reverses the string that comes in to display the help file correctly
       helpCon.setText(new StringBuilder(saveline).reverse().toString());
       helpCon.setCaretPosition(0);
     }
     catch( IOException e )
     {
      //should never happen
      e.printStackTrace();
      System.out.println("error!");
     }
     ///////////////////////////////////////////////////////////////////////
     JScrollPane scrollhelp = new JScrollPane(helpCon);
     scrollhelp.setHorizontalScrollBarPolicy
     (JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
     helpFrame.add(scrollhelp, BorderLayout.CENTER);
     buttons.add(okayButton);
     helpFrame.add(buttons, BorderLayout.SOUTH);
  }
  
  class GameListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e) 
    {
       helpFrame.dispose();
    }
  }

}
