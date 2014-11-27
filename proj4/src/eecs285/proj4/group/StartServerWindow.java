package eecs285.proj4.group;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class StartServerWindow extends JFrame {
	
	private JPanel topPanel;
	private JPanel botPanel;
	private JLabel label;
	private JButton okButton;
	
	public StartServerWindow(final GamePlay gameIn){
		super("Start Game");
		setLayout(new BorderLayout());
		
		String message = "Have your friend join a game with ip: " 
				+ ClientORServer.getIpAddress();
		label = new JLabel(message);
		okButton = new JButton("ok");
		
		okButton.addActionListener(new ActionListener(){

			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SetupWindow sw = new SetupWindow(gameIn, true);
				dispose();
			}
			
		});
		
		topPanel = new JPanel();
		botPanel = new JPanel();
		
		topPanel.add(label);
		botPanel.add(okButton);
		
		add(topPanel, BorderLayout.NORTH);
		add(botPanel, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		
	}
}
