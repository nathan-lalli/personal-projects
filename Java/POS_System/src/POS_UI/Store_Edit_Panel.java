package POS_UI;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POS_PD.Store;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Store_Edit_Panel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Store_Edit_Panel(JFrame currentFrame, Store store) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Store Edit");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(187, 33, 75, 14);
		add(lblNewLabel);
		
		JLabel name_label = new JLabel("Name:");
		name_label.setForeground(new Color(255, 255, 255));
		name_label.setBounds(35, 114, 48, 14);
		add(name_label);
		
		textField = new JTextField(store.getName());
		textField.setBackground(new Color(192, 192, 192));
		textField.setBounds(97, 111, 211, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton save_button = new JButton("Save");
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setName(textField.getText());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POS_Home_Panel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		save_button.setBackground(new Color(192, 192, 192));
		save_button.setBounds(90, 245, 89, 23);
		add(save_button);
		
		JButton cancel_button = new JButton("Cancel");
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POS_Home_Panel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		cancel_button.setBackground(new Color(192, 192, 192));
		cancel_button.setBounds(269, 245, 89, 23);
		add(cancel_button);
	}
}