package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import POS_PD.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class UPC_Edit_Panel extends JPanel {
	private JTextField UPC_Text;

	/**
	 * Create the panel.
	 */
	public UPC_Edit_Panel(JFrame currentFrame, JPanel currentPanel, Store store, Item item, UPC upc, Boolean isAdd) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UPC Edit");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(197, 37, 55, 14);
		add(lblNewLabel);
		
		JButton save_Button = new JButton("Save");
		save_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upc.setUPC(UPC_Text.getText());
				upc.setItem(item);

				if (isAdd)
				{
					item.addUPC(upc);
				}
				
				store.addUPC(upc);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		save_Button.setBackground(new Color(192, 192, 192));
		save_Button.setBounds(94, 240, 89, 23);
		add(save_Button);
		
		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		cancel_Button.setBackground(new Color(192, 192, 192));
		cancel_Button.setBounds(260, 240, 89, 23);
		add(cancel_Button);
		
		JLabel lblNewLabel_1 = new JLabel("UPC Code:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(86, 96, 83, 14);
		add(lblNewLabel_1);
		
		String upcCode = "";
		if (upc.getUPC() != null)
		{
			upcCode = upc.getUPC();
		}
		UPC_Text = new JTextField(upcCode);
		UPC_Text.setBackground(new Color(192, 192, 192));
		UPC_Text.setBounds(206, 93, 86, 20);
		add(UPC_Text);
		UPC_Text.setColumns(10);

	}

}
