package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POS_PD.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.ActionEvent;

public class End_Session_Panel extends JPanel {
	private JTextField numSales_Field;
	private JTextField total_Field;
	private JTextField cash_Field;
	int counter = 0;
	BigDecimal total = new BigDecimal("0.00");
	private JTextField cashDiff_Field;
	String cashDiff = "";
	JButton end_Session;

	/**
	 * Create the panel.
	 */
	public End_Session_Panel(JFrame currentFrame, Store store, Session session, String cashDiffGiven, String cashEntered) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		for (Sale s : session.getSales())
		{
			counter++;
			total = total.add(s.getTotal());
		}
		
		cashDiff = cashDiffGiven;
		
		JLabel lblNewLabel = new JLabel("Session Summary");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(209, 47, 119, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cashier:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(53, 86, 76, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Register:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(53, 124, 57, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Number Sales:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(53, 166, 106, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Total Sales:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(53, 206, 76, 14);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(" Enter Cash:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(53, 248, 76, 14);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Cash Count Diff:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(53, 290, 106, 14);
		add(lblNewLabel_6);
		
		JLabel cashier_Label = new JLabel(session.getCashier().toString2());
		cashier_Label.setForeground(new Color(255, 255, 255));
		cashier_Label.setBounds(167, 86, 86, 14);
		add(cashier_Label);
		
		JLabel register_Label = new JLabel(session.getRegister().toString());
		register_Label.setForeground(new Color(255, 255, 255));
		register_Label.setBounds(167, 124, 86, 14);
		add(register_Label);
		
		String numSales = Integer.toString(counter);
		numSales_Field = new JTextField(numSales);
		numSales_Field.setBackground(new Color(192, 192, 192));
		numSales_Field.setBounds(167, 163, 86, 20);
		add(numSales_Field);
		numSales_Field.setColumns(10);
		
		String totalString = total.setScale(2, RoundingMode.HALF_DOWN).toString();
		total_Field = new JTextField(totalString);
		total_Field.setBackground(new Color(192, 192, 192));
		total_Field.setBounds(167, 203, 86, 20);
		add(total_Field);
		total_Field.setColumns(10);
		
		cash_Field = new JTextField(cashEntered);
		cash_Field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.setEndingCash(new BigDecimal(cash_Field.getText()));
				cashDiff = session.calcCashCountDiff().setScale(2, RoundingMode.CEILING).toString();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new End_Session_Panel(currentFrame, store, session, cashDiff, cash_Field.getText()));
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		cash_Field.setBackground(new Color(192, 192, 192));
		cash_Field.setBounds(167, 245, 86, 20);
		add(cash_Field);
		cash_Field.setColumns(10);
		
		cashDiff_Field = new JTextField(cashDiff);
		cashDiff_Field.setBackground(new Color(192, 192, 192));
		cashDiff_Field.setBounds(167, 287, 86, 20);
		add(cashDiff_Field);
		cashDiff_Field.setColumns(10);
		
		end_Session = new JButton("End Session");
		end_Session.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.setCashDiff(new BigDecimal (cashDiff));
				store.addSession(session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POS_Home_Panel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		end_Session.setBackground(new Color(192, 192, 192));
		end_Session.setBounds(195, 339, 119, 23);
		end_Session.setEnabled(false);
		add(end_Session);
		
		if(cashDiff.contentEquals(""))
		{
			end_Session.setEnabled(false);
		}
		else
		{
			end_Session.setEnabled(true);
		}

	}

}
