package POS_UI;

import javax.swing.JPanel;

import POS_PD.Store;

import javax.swing.JLabel;

import java.awt.Color;

public class POS_Home_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	public POS_Home_Panel(Store store) 
	{
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to the POS System");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(163, 74, 174, 53);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(store.getName());
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(183, 201, 154, 23);
		add(lblNewLabel_1);

	}
}