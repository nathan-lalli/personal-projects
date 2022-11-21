package POS_UI;

import javax.swing.JPanel;



import POS_PD.*;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Cashier_Edit_Panel extends JPanel {
	private JTextField number_text;
	private JTextField address_text;
	private JTextField name_text;
	private JTextField city_text;
	private JTextField phone_text;
	private JTextField zip_text;
	private JTextField state_text;
	private JPasswordField password_Field;
	private JPasswordField SSN_Field;

	/**
	 * Create the panel.
	 * @param person 
	 */
	public Cashier_Edit_Panel(JFrame currentFrame, Cashier cashier, Store store, Boolean isAdd, Person person) {
		setBackground(new Color(128, 0, 0));
		
		
		JLabel lblNewLabel = new JLabel("Cashier Edit");
		lblNewLabel.setBounds(232, 38, 86, 14);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		JButton save_button = new JButton("Save");
		save_button.setBounds(124, 362, 89, 23);

		save_button.setBackground(new Color(192, 192, 192));
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				person.setName(name_text.getText());
				person.setAddress(address_text.getText());
				person.setCity(city_text.getText());
				person.setPhone(phone_text.getText());
				person.setState(state_text.getText());
				person.setZip(zip_text.getText());
				cashier.setNumber(number_text.getText());
				cashier.setPassword(new String (password_Field.getPassword()));
				person.setSSN(new String (SSN_Field.getPassword()));
				
				if(isAdd)
				{
					store.addCashier(cashier);
					cashier.setPerson(person);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Cashier_Selection_Panel(currentFrame, cashier, store, person));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		JButton cancel_button = new JButton("Cancel");
		cancel_button.setBounds(337, 362, 89, 23);
		cancel_button.setBackground(new Color(192, 192, 192));
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Cashier_Selection_Panel(currentFrame, cashier, store, person));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		JLabel number_label = new JLabel("Number:");
		number_label.setBounds(99, 91, 65, 14);
		number_label.setForeground(new Color(255, 255, 255));
		
		JLabel name_label = new JLabel("Name:");
		name_label.setBounds(99, 131, 65, 14);
		name_label.setForeground(new Color(255, 255, 255));
		
		JLabel address_label = new JLabel("Address:");
		address_label.setBounds(99, 171, 65, 14);
		address_label.setForeground(new Color(255, 255, 255));
		
		JLabel city_label = new JLabel("City:");
		city_label.setBounds(99, 211, 65, 14);
		city_label.setForeground(new Color(255, 255, 255));
		
		JLabel phone_label = new JLabel("Phone:");
		phone_label.setBounds(99, 251, 65, 14);
		phone_label.setForeground(new Color(255, 255, 255));
		
		JLabel password_label = new JLabel("Password:");
		password_label.setBounds(99, 291, 65, 14);
		password_label.setForeground(new Color(255, 255, 255));
		
		JLabel SSN_label = new JLabel("SSN:");
		SSN_label.setBounds(304, 91, 65, 14);
		SSN_label.setForeground(new Color(255, 255, 255));
		
		JLabel state_label = new JLabel("State:");
		state_label.setBounds(304, 168, 65, 14);
		state_label.setForeground(new Color(255, 255, 255));
		
		JLabel zip_label = new JLabel("Zip:");
		zip_label.setBounds(304, 245, 65, 14);
		zip_label.setForeground(new Color(255, 255, 255));
		
		if(isAdd)
		{
			cashier.setPerson(person);
		}
		
		String cashierNum = "";
		if (cashier.getNumber() != null)
		{
			cashierNum = cashier.getNumber();
		}
		number_text = new JTextField(cashierNum);
		number_text.setBounds(177, 85, 96, 20);
		number_text.setBackground(new Color(192, 192, 192));
		number_text.setColumns(10);
		
		String personAddress = "";
		if (cashier.getPerson().getAddress() != null)
		{
			personAddress = cashier.getPerson().getAddress();
		}
		address_text = new JTextField(personAddress);
		address_text.setBounds(177, 165, 96, 20);
		address_text.setBackground(new Color(192, 192, 192));
		address_text.setColumns(10);
		
		String personName = "";
		if (cashier.getPerson().getName() != null)
		{
			personName = cashier.getPerson().getName();
		}
		name_text = new JTextField(personName);
		name_text.setBounds(177, 125, 96, 20);
		name_text.setBackground(new Color(192, 192, 192));
		name_text.setColumns(10);
		
		String personCity = "";
		if (cashier.getPerson().getCity() != null)
		{
			personCity = cashier.getPerson().getCity();
		}
		city_text = new JTextField(personCity);
		city_text.setBounds(177, 205, 96, 20);
		city_text.setBackground(new Color(192, 192, 192));
		city_text.setColumns(10);
		
		String personPhone = "";
		if (cashier.getPerson().getPhone() != null)
		{
			personPhone = cashier.getPerson().getPhone();
		}
		phone_text = new JTextField(personPhone);
		phone_text.setBounds(177, 245, 96, 20);
		phone_text.setBackground(new Color(192, 192, 192));
		phone_text.setColumns(10);
		
		String cashierPassword = "";
		if (cashier.getPassword() != null)
		{
			cashierPassword = cashier.getPassword();
		}
		password_Field = new JPasswordField(cashierPassword);
		password_Field.setBounds(177, 288, 96, 20);
		password_Field.setEchoChar('$');
		password_Field.setBackground(new Color(192, 192, 192));
		
		String personZip = "";
		if (cashier.getPerson().getZip() != null)
		{
			personZip = cashier.getPerson().getZip();
		}
		zip_text = new JTextField(personZip);
		zip_text.setBounds(350, 245, 96, 20);
		zip_text.setBackground(new Color(192, 192, 192));
		zip_text.setColumns(10);
		
		String personState = "";
		if (cashier.getPerson().getState() != null)
		{
			personState = cashier.getPerson().getState();
		}
		state_text = new JTextField(personState);
		state_text.setBounds(350, 165, 96, 20);
		state_text.setBackground(new Color(192, 192, 192));
		state_text.setColumns(10);
		
		String personSSN = "";
		if (cashier.getPerson().getSSN() != null)
		{
			personSSN = cashier.getPerson().getSSN();
		}
		SSN_Field = new JPasswordField(personSSN);
		SSN_Field.setBounds(350, 88, 96, 20);
		SSN_Field.setEchoChar('$');
		SSN_Field.setBackground(new Color(192, 192, 192));
		setLayout(null);
		add(lblNewLabel);
		add(save_button);
		add(cancel_button);
		add(number_label);
		add(name_label);
		add(address_label);
		add(city_label);
		add(phone_label);
		add(password_label);
		add(SSN_label);
		add(state_label);
		add(zip_label);
		add(number_text);
		add(address_text);
		add(name_text);
		add(city_text);
		add(phone_text);
		add(password_Field);
		add(zip_text);
		add(state_text);
		add(SSN_Field);
		
		
	}
}
