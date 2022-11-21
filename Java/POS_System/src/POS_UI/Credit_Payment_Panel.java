package POS_UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POS_PD.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Credit_Payment_Panel extends JPanel {
	private JTextField payment_Field;
	private JTextField amtTendered_Field;
	private JTextField amt_Field;
	private JTextField acctNum_Field;
	private JTextField expDate_Field;
	JButton complete_Button;
	Credit credit = new Credit();
	String[] cardType = new String[] {"MasterCard", "Visa", "Discover", "American Express"};
	
	/**
	 * Create the panel.
	 */
	public Credit_Payment_Panel(JFrame currentFrame, Store store, Sale sale, Session session, Boolean taxFree, Boolean error) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(213, 35, 66, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Payment Due:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(33, 56, 106, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount Tendered:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(33, 117, 135, 14);
		add(lblNewLabel_2);
		
		String due = "";
		if (sale.calcTotal().toString() != null)
		{
			due = sale.calcTotal().setScale(2, RoundingMode.CEILING).toString();
		}
		payment_Field = new JTextField(due);
		payment_Field.setBackground(new Color(192, 192, 192));
		payment_Field.setBounds(43, 86, 96, 20);
		add(payment_Field);
		payment_Field.setColumns(10);
		
		String amtTendered = "";
		if (sale.calcAmtTendered().toString() != null)
		{
			amtTendered = sale.calcAmtTendered().setScale(2, RoundingMode.CEILING).toString();
		}
		amtTendered_Field = new JTextField(amtTendered);
		amtTendered_Field.setBackground(new Color(192, 192, 192));
		amtTendered_Field.setBounds(43, 142, 96, 20);
		add(amtTendered_Field);
		amtTendered_Field.setColumns(10);
		
		JButton cash_Button = new JButton("Cash");
		cash_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Cash_Payment_Panel(currentFrame, store, sale, session, taxFree, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		cash_Button.setBackground(new Color(192, 192, 192));
		cash_Button.setBounds(50, 179, 89, 23);
		add(cash_Button);
		
		JButton check_Button = new JButton("Check");
		check_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Check_Payment_Panel(currentFrame, store, sale, session, taxFree, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		check_Button.setBackground(new Color(192, 192, 192));
		check_Button.setBounds(50, 213, 89, 23);
		add(check_Button);
		
		JButton credit_Button = new JButton("Credit");
		credit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Credit_Payment_Panel(currentFrame, store, sale, session, taxFree, false));
				currentFrame.getContentPane().revalidate();				
			}
		});
		credit_Button.setBackground(new Color(192, 192, 192));
		credit_Button.setBounds(50, 247, 89, 23);
		add(credit_Button);
		
		complete_Button = new JButton("Payment Complete");
		complete_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, sale, taxFree, false));
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		complete_Button.setBackground(new Color(192, 192, 192));
		complete_Button.setBounds(172, 299, 150, 23);
		complete_Button.setEnabled(false);
		add(complete_Button);
		
		for(Payment p : sale.getPayments())
		{
			complete_Button.setEnabled(true);
		}
		
		JLabel lblNewLabel_3 = new JLabel("Enter Credit Payment");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(220, 73, 150, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(178, 99, 99, 14);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Card Type:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(178, 137, 99, 14);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Account Number:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(178, 178, 125, 14);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Expire Date:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		lblNewLabel_7.setBounds(178, 215, 99, 14);
		add(lblNewLabel_7);
		
		amt_Field = new JTextField();
		amt_Field.setBackground(new Color(192, 192, 192));
		amt_Field.setBounds(306, 96, 89, 20);
		add(amt_Field);
		amt_Field.setColumns(10);
		
		acctNum_Field = new JTextField();
		acctNum_Field.setBackground(new Color(192, 192, 192));
		acctNum_Field.setBounds(306, 175, 89, 20);
		add(acctNum_Field);
		acctNum_Field.setColumns(10);
		
		expDate_Field = new JTextField();
		expDate_Field.setBackground(new Color(192, 192, 192));
		expDate_Field.setBounds(306, 214, 89, 20);
		add(expDate_Field);
		expDate_Field.setColumns(10);
		
		
		//JTextField cardType_Field = new JTextField();
		//cardType_Field.setBackground(new Color(192, 192, 192));
		//cardType_Field.setBounds(306, 121, 86, 20);
		//add(cardType_Field);
		//cardType_Field.setColumns(10);
		
		DefaultComboBoxModel<String> cardList = new DefaultComboBoxModel<String>();
		
		for (String s : cardType)
		{
			cardList.addElement(s);
		}
		
		JComboBox<String> cardType_Box = new JComboBox<String>(cardList);
		cardType_Box.setBackground(new Color(192, 192, 192));
		cardType_Box.setBounds(306, 134, 89, 20);
		add(cardType_Box);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credit = new Credit(cardType_Box.getSelectedItem().toString(), acctNum_Field.getText(), expDate_Field.getText(), new BigDecimal(amt_Field.getText()), amt_Field.getText());
				sale.addPayment(credit);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Payment_Panel(currentFrame, store, sale, session, taxFree, false, credit));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(178, 247, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Payment_Panel(currentFrame, store, sale, session, taxFree, false, new Payment()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setBounds(306, 247, 89, 23);
		add(btnNewButton_1);
		
		int cmp = sale.calcAmtTendered().compareTo(sale.calcTotal());
		
		if(cmp == 0)
		{
			complete_Button.setEnabled(true);
		}
		else if(cmp == 1)
		{
			complete_Button.setEnabled(true);
		}
		else if(cmp == -1)
		{
			complete_Button.setEnabled(false);
		}
	}
}
