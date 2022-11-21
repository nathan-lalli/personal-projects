package POS_UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POS_PD.*;

public class Cash_Payment_Panel extends JPanel {
	private JTextField payment_Field;
	private JTextField amtTendered_Field;
	private JTextField given_Field;
	JButton complete_Button;
	Cash cashPayment = new Cash();
	
	/**
	 * Create the panel.
	 */
	public Cash_Payment_Panel(JFrame currentFrame, Store store, Sale sale, Session session, Boolean taxFree, Boolean error) {
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
		lblNewLabel_2.setBounds(33, 117, 124, 14);
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
		
		JLabel lblNewLabel_3 = new JLabel("Enter Cash Payment");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(272, 56, 141, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount Tendered:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(213, 89, 141, 14);
		add(lblNewLabel_4);
		
		given_Field = new JTextField();
		given_Field.setBackground(new Color(192, 192, 192));
		given_Field.setBounds(327, 86, 86, 20);
		add(given_Field);
		given_Field.setColumns(10);
		
		JButton save_Button = new JButton("Save");
		save_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashPayment = new Cash(given_Field.getText(), new BigDecimal(given_Field.getText()));
				session.getRegister().getCashDrawer().addCash(cashPayment.getAmount());
				sale.addPayment(cashPayment);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Payment_Panel(currentFrame, store, sale, session, taxFree, false, cashPayment));
				currentFrame.getContentPane().revalidate();
			}
		});
		save_Button.setBackground(new Color(192, 192, 192));
		save_Button.setBounds(213, 141, 89, 23);
		add(save_Button);
		
		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Payment_Panel(currentFrame, store, sale, session, taxFree, false, new Payment()));
				currentFrame.getContentPane().revalidate();
			}
		});
		cancel_Button.setBackground(new Color(192, 192, 192));
		cancel_Button.setBounds(327, 141, 89, 23);
		add(cancel_Button);
		
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
