package eecs285.proj4.group;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class StartMenuButton extends JButton {
	public StartMenuButton(String buttonName){
		super(buttonName);
		Font buttonFont = new Font("Algerian", Font.BOLD, 24);
	    setForeground(Color.ORANGE);
	    setFont(buttonFont);
	    setOpaque(false);
	    setBorderPainted(false);
	    setContentAreaFilled(false);
	}
}
