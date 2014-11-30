package eecs285.proj4.group;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SetupWindow extends JFrame{

	private JButton optionA;
	private JButton optionB;
	private JButton optionC;
	private JButton accept;
	private JPanel acceptPanel;
	private JPanel topPanel;
	
	private OptionListener listener;
	
	boolean server;
	
	private GamePlay game;
	
	public SetupWindow(GamePlay gameIn, boolean serverIn){
		super("Setup");
	
		setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		acceptPanel = new JPanel();
		
		optionA = new JButton("A");
		optionB = new JButton("B");
		optionC = new JButton("C");
		accept = new JButton("Confirm Setup");
		accept.setEnabled(false);
		
		optionA.setPreferredSize(new Dimension(50,40));
		optionB.setPreferredSize(new Dimension(50,40));
		optionC.setPreferredSize(new Dimension(50,40));
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
		
		game = gameIn;
		server = serverIn;
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		pack();
		
	}

//---------------------------------------------------------------	
	public class OptionListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource() == optionA){
				// displays option A setup and sets players 
				// ships array with the locations of the ships
				game.displaySetupOptions('A', server);
				accept.setEnabled(true);
			}
			if(e.getSource() == optionB){
				// displays option B setup and sets players 
				// ships array with the locations of the ships
				game.displaySetupOptions('B', server);
				accept.setEnabled(true);
			}
			if(e.getSource() == optionC){
				// displays option C setup and sets players 
				// ships array with the locations of the ships
				game.displaySetupOptions('C', server);
				accept.setEnabled(true);
			}
			if(e.getSource() == accept){
				// needs to send the locations to other player
			  dispose();
				game.getNetwork().sendStartLocations(game.getPlayer().getBoard().getShips(), server);
				game.getNetwork().readMessage(game);
				game.run();		
			}
		}

	}
}
