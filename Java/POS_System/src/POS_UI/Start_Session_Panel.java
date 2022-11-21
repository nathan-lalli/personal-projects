package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POS_PD.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class Start_Session_Panel extends JPanel {
	private JTextField cash_Field;
	private JPasswordField password_Field;
	JButton login_Button;
	JLabel error_Message;

	/**
	 * Create the panel.
	 */
	public Start_Session_Panel(JFrame currentFrame, Store store) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(244, 55, 39, 14);
		add(lblNewLabel);
		
		JLabel cashier_Number = new JLabel("Cashier Number:");
		cashier_Number.setForeground(new Color(255, 255, 255));
		cashier_Number.setBounds(107, 117, 102, 14);
		add(cashier_Number);
		
		JLabel register_Number = new JLabel("Register Number:");
		register_Number.setForeground(new Color(255, 255, 255));
		register_Number.setBounds(107, 159, 134, 14);
		add(register_Number);
		
		JLabel starting_Cash = new JLabel("Starting Cash:");
		starting_Cash.setForeground(new Color(255, 255, 255));
		starting_Cash.setBounds(107, 202, 102, 14);
		add(starting_Cash);
		
		JLabel password_Label = new JLabel("Password:");
		password_Label.setForeground(new Color(255, 255, 255));
		password_Label.setBounds(107, 241, 102, 14);
		add(password_Label);
		
		error_Message = new JLabel("Invalid Password!");
		error_Message.setForeground(new Color(255, 0, 0));
		error_Message.setBounds(331, 247, 122, 14);
		error_Message.setVisible(false);
		add(error_Message);
		
		cash_Field = new JTextField();
		cash_Field.setBackground(new Color(192, 192, 192));
		cash_Field.setBounds(225, 202, 96, 20);
		add(cash_Field);
		cash_Field.setColumns(10);
		
		DefaultComboBoxModel<Cashier> cashierList = new DefaultComboBoxModel<Cashier>();
		
		for (Cashier c : store.getCashiers().values())
		{
			cashierList.addElement(c);
		}
		
		JComboBox<Cashier> cashier_Box = new JComboBox<Cashier>(cashierList);
		cashier_Box.setBackground(new Color(192, 192, 192));
		cashier_Box.setBounds(225, 117, 96, 20);
		add(cashier_Box);
		
		DefaultComboBoxModel<Register> registerList = new DefaultComboBoxModel<Register>();
		
		for (Register r : store.getRegisters().values())
		{
			registerList.addElement(r);
		}
		
		JComboBox<Register> register_Box = new JComboBox<Register>(registerList);
		register_Box.setBackground(new Color(192, 192, 192));
		register_Box.setBounds(225, 159, 96, 20);
		add(register_Box);
		
		password_Field = new JPasswordField();
		password_Field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = (Register) register_Box.getSelectedItem();
				Cashier cashier = (Cashier) cashier_Box.getSelectedItem();
				String password = new String(password_Field.getPassword());
				CashDrawer cashDrawer = new CashDrawer();
				
				if(password.equals(cashier.getPassword()))
				{
					cashDrawer.setCashAmount(new BigDecimal(cash_Field.getText()));
					cashDrawer.setStartingAmount(new BigDecimal(cash_Field.getText()));
					register.setCashDrawer(cashDrawer);
					Session session = new Session(cashier, register);
					
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, new Sale("N"), false, false));
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					error_Message.setVisible(true);
				}
			}
		});
		password_Field.setBackground(new Color(192, 192, 192));
		password_Field.setBounds(225, 244, 96, 20);
		add(password_Field);
		
		
		login_Button = new JButton("Login");
		login_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = (Register) register_Box.getSelectedItem();
				Cashier cashier = (Cashier) cashier_Box.getSelectedItem();
				String password = new String(password_Field.getPassword());
				CashDrawer cashDrawer = new CashDrawer();
				
				if(password.equals(cashier.getPassword()))
				{
					cashDrawer.setCashAmount(new BigDecimal(cash_Field.getText()));
					cashDrawer.setStartingAmount(new BigDecimal(cash_Field.getText()));
					register.setCashDrawer(cashDrawer);
					Session session = new Session(cashier, register);
					
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, new Sale("N"), false, false));
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					error_Message.setVisible(true);
				}
				
			}
		});
		login_Button.setBackground(new Color(192, 192, 192));
		login_Button.setBounds(116, 306, 89, 23);
		add(login_Button);
		
		
		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POS_Home_Panel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		cancel_Button.setBackground(new Color(192, 192, 192));
		cancel_Button.setBounds(321, 306, 89, 23);
		add(cancel_Button);

	}
}
