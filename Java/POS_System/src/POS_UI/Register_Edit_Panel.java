package POS_UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POS_PD.Register;
import POS_PD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Register_Edit_Panel extends JPanel {
	private JTextField number_text;

	/**
	 * Create the panel.
	 * @param  
	 * @param store 
	 * @param register 
	 * @param currentFrame 
	 */
	public Register_Edit_Panel(JFrame currentFrame, Register register, Store store, Boolean isAdd) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register Edit");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(211, 50, 88, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Register Number:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(134, 114, 110, 14);
		add(lblNewLabel_1);
		
		
		
		if (!isAdd)
		{
			number_text = new JTextField(register.getNumber());
		}
		else number_text = new JTextField();
		number_text.setBackground(new Color(192, 192, 192));
		number_text.setBounds(256, 111, 96, 20);
		add(number_text);
		number_text.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(110, 263, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.setNumber(number_text.getText());
				
				if(isAdd)
				{
					store.addRegister(register);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Register_Selection_Panel(currentFrame, register, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setBounds(309, 263, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Register_Selection_Panel(currentFrame, register, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnNewButton_1);

	}
}
