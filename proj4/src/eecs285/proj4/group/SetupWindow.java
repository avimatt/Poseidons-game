package eecs285.proj4.group;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SetupWindow extends JFrame{

	private JButton optionA;
	private JButton optionB;
	private JButton optionC;
	private JButton accept;
	private JPanel acceptPanel;
	private JPanel topPanel;
	
	private OptionListener listener;
	
	public SetupWindow(){
		super("Setup");
	
		setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		acceptPanel = new JPanel();
		
		optionA = new JButton("A");
		optionB = new JButton("B");
		optionC = new JButton("C");
		accept = new JButton("Confirm Setup");
		
		optionA.setPreferredSize(new Dimension(40,40));
		optionB.setPreferredSize(new Dimension(40,40));
		optionC.setPreferredSize(new Dimension(40,40));
		accept.setPreferredSize(new Dimension(150, 30));
		
		listener = new OptionListener();
		
		optionA.addMouseListener(listener);
		optionB.addMouseListener(listener);
		optionC.addMouseListener(listener);
		accept.addMouseListener(listener);
		
		topPanel.add(optionA);
		topPanel.add(optionB);
		topPanel.add(optionC);
		
		acceptPanel.add(accept);
		
		add(topPanel, BorderLayout.NORTH);
		add(acceptPanel, BorderLayout.SOUTH);
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		pack();
		
	}
	
	public class OptionListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource() == optionA){
				// function for showing option A on the screen
			}
			if(e.getSource() == optionB){
				// function for showing option B on the screen
			}
			if(e.getSource() == optionC){
				// function for showing option C on the screen
			}
			if(e.getSource() == accept){
				// function for setting the locations in the 
				// array of ships in the Board class
				dispose();
			}
		}

	}
}
